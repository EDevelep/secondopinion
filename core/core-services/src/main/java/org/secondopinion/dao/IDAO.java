package org.secondopinion.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.secondopinion.common.dto.DAOResult;
import org.secondopinion.dao.exception.DataAccessException;


public interface IDAO<OBJECT_TYPE, KEY_TYPE  extends Serializable> {

	Collection<OBJECT_TYPE> findAll();
	
	OBJECT_TYPE findById(KEY_TYPE key);

//	public List<OBJECT_TYPE> findByExample(OBJECT_TYPE exampleInstance, String excludeProperty);
//	
	List<OBJECT_TYPE> findByProperty(String propertyName, Object propertyValue);

	OBJECT_TYPE findOneByProperty(String propertyName, Object propertyValue);
	
	@SuppressWarnings("rawtypes")
	List<OBJECT_TYPE> findByPropertyValues(String propertyName, List propertyValue);
	
	void save(OBJECT_TYPE type);

	void save(Collection<OBJECT_TYPE> types);
	
	DAOResult<OBJECT_TYPE, KEY_TYPE> saveWithRetry(Collection<OBJECT_TYPE> types);
	
	void delete(OBJECT_TYPE object) throws DataAccessException;
	
	void delete(Collection<OBJECT_TYPE> object) throws DataAccessException;
	
//	void findBySql(String sql, Map<Object, String> params);
	
//	void findByCriteria(Criteria);
}
