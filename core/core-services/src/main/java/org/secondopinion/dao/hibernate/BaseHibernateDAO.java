package org.secondopinion.dao.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.secondopinion.common.dto.DAOResult;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.dao.hibernate.transformer.ResultToOrderedMapTransformer;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.PerfHelper;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

public abstract class BaseHibernateDAO<OBJECT_TYPE extends BaseDomainObject<KEY_TYPE>, KEY_TYPE extends Serializable>
		implements IHbernateDAO<OBJECT_TYPE, KEY_TYPE> {

	private static final int MAX_ROWS = 100;

	private static Logger log = LoggerFactory.getLogger(BaseHibernateDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OBJECT_TYPE> findAll() {
		List<OBJECT_TYPE> types = getSession().createQuery("from " + getTableClassName()).setMaxResults(200).list();

		return types;
	}

	@Transactional(readOnly = true)
	public OBJECT_TYPE findById(final KEY_TYPE id) {

		try {
			PerfHelper perfHelper = new PerfHelper();

			Session session = getSession();
			OBJECT_TYPE object = (OBJECT_TYPE) session.get(getTableClass(), id);

			log.debug("Time to get entity by key. entity: {} , key: {}, id, entityFound:{} ", getTableClassName(), id,
					(object != null) ? perfHelper.getTimeTakenStr() : null);

			if (object != null) {
				object.setFromDB(true);

			}

			return object;
		} catch (Exception excp) {
			log.error(excp.getMessage(), excp);
			throw new DataAccessException(
					"Exception getting the entity. entityClass: " + getTableClassName() + ", primaryKey: " + id, excp);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = true)
	public List<OBJECT_TYPE> findByPropertyValues(String propertyName, List propertyValue) {
		Criterion criterion = Restrictions.in(propertyName, propertyValue);
		return findByCrieria(criterion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OBJECT_TYPE> findByProperty(String propertyName, Object propertyValue) {
		Criterion criterion = Restrictions.eq(propertyName, propertyValue);
		return findByCrieria(criterion);
	}

	@Override
	@Transactional(readOnly = true)
	public OBJECT_TYPE findOneByProperty(String propertyName, Object propertyValue) {
		List<OBJECT_TYPE> objs = findByProperty(propertyName, propertyValue);

		if (objs != null && objs.size() > 0) {
			return objs.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void save(OBJECT_TYPE object) throws DataAccessException {
		PerfHelper perfHelper = new PerfHelper();
		try {
			validateTypeFields(object);

			Session session = getSession();
			if (object != null) {
				object.setAudit(AppThreadLocal.getUserName());

				if (object.isKeyNull()) {
					object.setAuditForCreate(AppThreadLocal.getUserName());
				}
				session.saveOrUpdate(object);
				session.flush();
			}
		} catch (Exception excp) {
			log.error("Error saving the entity. entity: " + StringUtil.toString(object) + ", error message : "
					+ excp.getMessage(), excp);
			throw new DataAccessException("Error saving the entity. entity: " + StringUtil.toString(object)
					+ ", error message : " + excp.getMessage(), excp);
		} finally {
			//
			log.debug("Time to save (" + ObjectUtil.getClassName(object) + "): " + perfHelper.getTimeTakenStr());
		}
	}

	@Transactional
	public void save(Collection<OBJECT_TYPE> objects) {

		try {
			save(objects, true);
		} finally {
		}
	}

	@Override
	public DAOResult<OBJECT_TYPE, KEY_TYPE> saveWithRetry(Collection<OBJECT_TYPE> types) {
		DAOResult<OBJECT_TYPE, KEY_TYPE> daoResult = new DAOResult<OBJECT_TYPE, KEY_TYPE>();
		try {

			save(types);
			daoResult.addSuccesses(types);
		} catch (Exception ex) {

			log.warn("Error saving collection... Tryig with retry option");
			for (OBJECT_TYPE object : types) {
				try {
					save(object);
					daoResult.addSuccess(object);
					getSession().getTransaction().commit();
				} catch (Exception e) {
					getSession().getTransaction().rollback();
					daoResult.addFilure(object.getId(), object, e.getMessage());
				}
			}
		}
		return daoResult;
	}

	/**
	 * Does insert or update
	 * 
	 * @param objects
	 * @param flush
	 * @throws DataAccessException
	 */
	protected void save(Collection<OBJECT_TYPE> objects, boolean flush) throws DataAccessException {
		if (CollectionUtils.isEmpty(objects)) {
			return;
		}

		PerfHelper perfHelper = new PerfHelper();
		try {

			Session session = getSession();
			for (OBJECT_TYPE type : objects) {
				if (type != null) {
					validateTypeFields(type);
					String userName = AppThreadLocal.getUserName();
					type.setAudit(StringUtil.isNullOrEmpty(userName) ? "SYSTEM" : userName);
					if (type.isKeyNull()) {
						type.setAuditForCreate(userName);
					}
					session.saveOrUpdate(type);
				}
			}
			if (flush) {
				session.flush();
			}
		} catch (Exception excp) {
			log.error(excp.getMessage(), excp);
			throw new DataAccessException("Error saving the entity. entity: " + StringUtil.toString(objects), excp);
		} finally {
			if (flush) {
				log.debug("Time to save (" + getTableClassName() + "): " + perfHelper.getTimeTakenStr());
			}
		}

	}

	private void validateTypeFields(OBJECT_TYPE type) {
		boolean validatePk = true;
		if (Objects.isNull(type.getId())) {
			validatePk = false;
		}
		type.validate(validatePk);
		Collection<ValidationMessage> validationMsgs = type.getValidationMessages();
		if (!CollectionUtils.isEmpty(validationMsgs)) {
			throw new IllegalArgumentException(validationMsgs.stream().map(vm -> vm.getMessage())
					.collect(Collectors.joining(System.lineSeparator())));
		}
	}

	@Transactional
	public void delete(OBJECT_TYPE object) {
		delete(object, true);
	}

	@Transactional
	public void delete(Collection<OBJECT_TYPE> objects) {
		delete(objects, true);
	}

	protected void delete(OBJECT_TYPE object, boolean flush) throws DataAccessException {
		PerfHelper perfHelper = new PerfHelper();
		try {
			Session session = getSession();
			if (object != null) {
				session.delete(object);
			}

			session.flush();
		} catch (Exception excp) {
			log.error(excp.getMessage(), excp);
			throw new DataAccessException("Error deleting the entities. entity: " + StringUtil.toString(object), excp);
		} finally {
			if (flush) {
				log.debug("Time to delete (" + getTableClassName() + "): " + perfHelper.getTimeTakenStr());
			}
		}
	}

	protected void delete(Collection<OBJECT_TYPE> objects, boolean flush) throws DataAccessException {
		if (CollectionUtils.isEmpty(objects))
			return;
		PerfHelper perfHelper = new PerfHelper();
		try {
			Session session = getSession();
			for (OBJECT_TYPE object : objects) {
				if (object != null) {
					session.delete(object);
				}
			}
			if (flush)
				session.flush();
		} catch (Exception excp) {
			log.error(excp.getMessage(), excp);
			throw new DataAccessException("Error deleting the entities. entity: " + StringUtil.toString(objects), excp);
		} finally {
			if (flush) {
				log.debug("Time to delete (" + getTableClassName() + "): " + perfHelper.getTimeTakenStr());
			}
		}

	}

	@Transactional(readOnly = true)
	public List<OBJECT_TYPE> findByCrieria(final List<Criterion> criterions) throws DataAccessException {
		PerfHelper perfHelper = new PerfHelper();
		try {
			Criteria criteria = getSession().createCriteria(getTableClass());

			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}

			return executeCriteriaAndDecrypt(criteria);
		} catch (Exception excp) {
			log.error(excp.getMessage(), excp);
			throw new DataAccessException("Error running the criteria query for class: " + getTableClassName()
					+ ", criteria: " + StringUtil.toString(criterions), excp);
		} finally {
			log.debug("Total Time take by Criteria call: " + perfHelper.getTimeTakenStr());
		}

	}

	// to use this for pagenation
	@Transactional(readOnly = true)
	public Response<List<OBJECT_TYPE>> findByCrieria(final List<Criterion> criterions, List<Order> orders,
			Integer pageNum, Integer maxResults) throws DataAccessException {
		PerfHelper perfHelper = new PerfHelper();
		Response<List<OBJECT_TYPE>> response = new Response<>();
		try {

			Long totalResults = (Long) buildCriteria(criterions).setProjection(Projections.rowCount()).uniqueResult();

			if (pageNum == null || pageNum == 0) {
				pageNum = 1;
			}
			if (maxResults == null || maxResults == 0) {
				maxResults = 10;
			}

			Criteria criteria = buildCriteria(criterions).setFirstResult((pageNum - 1) * maxResults)
					.setMaxResults(maxResults);

			if (orders != null) {
				for (Order order : orders) {
					criteria.addOrder(order);
				}
			}
			List<OBJECT_TYPE> data = executeCriteriaAndDecrypt(criteria);
			response.setTotalResults(totalResults);
			response.setMaxResultsPerPage(maxResults);
			response.setPageNum(pageNum);
			response.setStatus(StatusEnum.SUCCESS.name());
			if (criterions != null && !criterions.isEmpty()) {
				response.setCriterions(criterions.toString());
			}

			if (!data.isEmpty()) {
				response.setMessage("Data fetched successfully.");
			} else {
				response.setMessage("Data not available.");
			}

			response.setData(data);
			return response;
		} catch (Exception excp) {
			log.error(excp.getMessage(), excp);
			throw new DataAccessException("Error running the criteria query for class: " + getTableClassName()
					+ ", criteria: " + StringUtil.toString(criterions), excp);
		} finally {
			log.debug("Total Time take by Criteria call: " + perfHelper.getTimeTakenStr());
		}

	}

	private Criteria buildCriteria(List<Criterion> criterions) {
		Criteria criteria = getSession().createCriteria(getTableClass());
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return criteria;
	}

	@Transactional(readOnly = true)
	public List<OBJECT_TYPE> findByCrieria(Criterion... criterions) throws DataAccessException {
		PerfHelper perfHelper = new PerfHelper();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(getTableClass());

			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
			// return criteria.list();
			return executeCriteriaAndDecrypt(criteria);
		} catch (Exception excp) {
			log.error(excp.getMessage(), excp);
			throw new DataAccessException("Error running the criteria query for class: " + getTableClassName()
					+ ", criteria: " + StringUtil.toString(criterions), excp);
		} finally {
			log.debug("Total Time take by Criteria call: " + perfHelper.getTimeTakenStr());
		}

	}

	@SuppressWarnings("unchecked")
	private List<OBJECT_TYPE> executeCriteriaAndDecrypt(Criteria criteria) {
		List<OBJECT_TYPE> list = criteria.list();

		return list;
	}

	@Transactional(readOnly = true)
	public List<OBJECT_TYPE> findByCrieria(List<Criterion> criterions, Order... orders) throws DataAccessException {
		PerfHelper perfHelper = new PerfHelper();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(getTableClass());

			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}

			for (Order order : orders) {
				criteria.addOrder(order);
			}
			criteria.setMaxResults(100);
			// return criteria.list();
			return executeCriteriaAndDecrypt(criteria);
		} catch (Exception excp) {
			log.error(excp.getMessage(), excp);
			throw new DataAccessException("Error running the criteria query for class: " + getTableClassName()
					+ ", criteria: " + StringUtil.toString(criterions), excp);
		} finally {
			log.debug("Total Time take by Criteria call: " + perfHelper.getTimeTakenStr());
		}

	}

	@Transactional(readOnly = true)
	public List<OBJECT_TYPE> findByCrieria(List<Criterion> criterions, List<Projection> projections, Integer maxRows,
			Order... orders) throws DataAccessException {
		PerfHelper perfHelper = new PerfHelper();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(getTableClass());

			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}

			ProjectionList projectionList = Projections.projectionList();
			for (Projection projection : projections) {
				projectionList.add(projection);
			}
			criteria.setProjection(projectionList);

			for (Order order : orders) {
				criteria.addOrder(order);
			}
			if (maxRows != null) {
				criteria.setMaxResults(maxRows);
			}
			// return criteria.list();
			return executeCriteriaAndDecrypt(criteria);
		} catch (Exception excp) {
			log.error(excp.getMessage(), excp);
			throw new DataAccessException("Error running the criteria query for class: " + getTableClassName()
					+ ", criteria: " + StringUtil.toString(criterions), excp);
		} finally {
			log.debug("Total Time take by Criteria call: " + perfHelper.getTimeTakenStr());
		}

	}

	// @Autowired
	// private EntityManager entityManager;
	//
	// private Session getSession() {
	// return entityManager.unwrap(Session.class);
	// }

	private Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> findBySqlQuery(final String query, Map<String, Object> params, final Class<X> resultClass,
			final Map<String, Type> scalarMapping) throws DataAccessException {

		SQLQuery hibQuery = (SQLQuery) generateQuery(getSession(), query, true, resultClass);

		if (scalarMapping != null) {
			for (String col : scalarMapping.keySet()) {
				hibQuery.addScalar(col, scalarMapping.get(col));
			}
		}

		for (String param : params.keySet()) {
			hibQuery.setParameter(param, params.get(param));
		}

		return (List<X>) hibQuery.list();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findBySqlQueryMapTransformer(final String query, Map<String, Object> params,
			final Map<String, Type> scalarMapping) throws DataAccessException {

		SQLQuery hibQuery = (SQLQuery) generateQuery(getSession(), query, true, null);

		if (scalarMapping != null) {
			for (String col : scalarMapping.keySet()) {
				hibQuery.addScalar(col, scalarMapping.get(col));
			}
		}

		if (params != null) {
			for (String param : params.keySet()) {
				hibQuery.setParameter(param, params.get(param));
			}
		}

		hibQuery.setResultTransformer(new ResultToOrderedMapTransformer());

		return hibQuery.list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <X> List<X> findBySqlQuery(final String query, Map<String, Object> params, final Class<X> resultClass)
			throws DataAccessException {

		Query hibQuery = generateQuery(getSession(), query, true, resultClass);

		for (String param : params.keySet()) {
			if (params.get(param) instanceof List) {
				hibQuery.setParameterList(param, (Collection) params.get(param));
			} else {
				hibQuery.setParameter(param, params.get(param));
			}
		}

		return (List<X>) hibQuery.list();
	}

	public List<Map<String, Object>> findBySqlQuery(final String query, Map<String, Object> params)
			throws DataAccessException {

		Query hibQuery = generateQuery(getSession(), query, true, null);
		// hibQuery.setResultTransformer(arg0)
		return executeHibQuery(params, hibQuery);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map<String, Object>> executeHibQuery(Map<String, Object> params, Query hibQuery) {
		hibQuery.setResultTransformer(new ResultToOrderedMapTransformer());
		for (String param : params.keySet()) {
			if (params.get(param) instanceof List) {
				hibQuery.setParameterList(param, (Collection) params.get(param));
			} else {
				hibQuery.setParameter(param, params.get(param));
			}
		}

		return hibQuery.list();
	}

	public List<Map<String, Object>> findBySqlQuery(final String queryStr, final Integer startingRow, Integer maxRows,
			Map<String, Object> params) {
		Query hibQuery = generateQuery(getSession(), queryStr, true, startingRow, maxRows, null);

		return executeHibQuery(params, hibQuery);
	}

	@SuppressWarnings("rawtypes")
	public List queryUsingSQL(final String query, Map<String, Object> params) throws DataAccessException {
		return queryUsingSQL(query, params, null, null);
	}

	@SuppressWarnings("rawtypes")
	public List queryUsingSQL(final String query, Map<String, Object> params, Class<?> resultClass)
			throws DataAccessException {
		return queryUsingSQL(query, params, null, resultClass);
	}

	@SuppressWarnings("rawtypes")
	public List queryUsingSQL(String query, Map<String, Object> params, Integer i, Class<?> resultClass) {
		Query hibQuery = generateQuery(getSession(), query, true, null, i, resultClass);
		for (String param : params.keySet()) {
			if (params.get(param) instanceof List) {
				hibQuery.setParameterList(param, (Collection) params.get(param));
			} else {
				hibQuery.setParameter(param, params.get(param));
			}
		}

		return hibQuery.list();
	}

	@SuppressWarnings("rawtypes")
	public List queryUsingSQL(final String queryStr, final Integer startingRow, Integer maxRows,
			Map<String, Object> params) {
		Query hibQuery = generateQuery(getSession(), queryStr, true, startingRow, maxRows, null);

		for (String param : params.keySet()) {
			if (params.get(param) instanceof List) {
				hibQuery.setParameterList(param, (Collection) params.get(param));
			} else {
				hibQuery.setParameter(param, params.get(param));
			}
		}

		return hibQuery.list();
	}

	private Query generateQuery(Session session, String queryStr, boolean isNativeSql, Class<?> resultClass) {
		return generateQuery(session, queryStr, isNativeSql, null, null, resultClass);
	}

	private Query generateQuery(Session session, String queryStr, boolean isNativeSql, Integer startingRow,
			Integer maxRows, Class<?> resultClass) {
		Query hibQuery = null;
		if (isNativeSql) {
			hibQuery = session.createSQLQuery(queryStr);
		} else {
			hibQuery = session.createQuery(queryStr);
		}
		if (resultClass != null) {
			hibQuery.setResultTransformer(Transformers.aliasToBean(resultClass));
		}
		if (maxRows != null) {
			if (maxRows.intValue() > 0) {
				hibQuery.setMaxResults(maxRows.intValue());
			} else if (maxRows.intValue() == 0) {
				hibQuery.setMaxResults(MAX_ROWS);
			}
		}
		if (startingRow != null && startingRow.intValue() > 0) {
			hibQuery.setFirstResult(startingRow.intValue());
		}

		return hibQuery;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public int executeQuery(String hqlQuery, Map<String, Object> params) {
		Session session = getSession();
		Query query = session.createQuery(hqlQuery);

		for (String param : params.keySet()) {
			if (params.get(param) instanceof List) {
				query.setParameterList(param, (Collection) params.get(param));
			} else {
				query.setParameter(param, params.get(param));
			}
		}

		return query.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	public List querySqlMapTransformer(final String queryStr, final Integer startingRow, Integer maxRows,
			Map<String, Object> params, final Map<String, Type> scalarMapping) {
		SQLQuery hibQuery = (SQLQuery) generateQuery(getSession(), queryStr, true, startingRow, maxRows, null);

		if (scalarMapping != null) {
			for (String col : scalarMapping.keySet()) {
				hibQuery.addScalar(col, scalarMapping.get(col));
			}
		}

		if (params != null && !params.isEmpty()) {
			for (String param : params.keySet()) {
				if (params.get(param) instanceof List) {
					hibQuery.setParameterList(param, (Collection) params.get(param));
				} else {
					hibQuery.setParameter(param, params.get(param));
				}
			}
		}

		return hibQuery.list();
	}
}
