package org.secondopinion.dao.hibernate;

import java.io.Serializable;

import org.secondopinion.dao.IDAO;

public interface IHbernateDAO<OBJECT_TYPE, KEY_TYPE extends Serializable> extends IDAO<OBJECT_TYPE, KEY_TYPE> {

	public String getTableClassName();

	public Class<OBJECT_TYPE> getTableClass();
	
//	List<OBJECT_TYPE> findByCrieria(Criterion... criterions);

//	public List<OBJECT_TYPE> findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values);
//
//	public List<OBJECT_TYPE> findByNamedQueryAndNamedParam(String queryName, Map<String, ?> params);

}
