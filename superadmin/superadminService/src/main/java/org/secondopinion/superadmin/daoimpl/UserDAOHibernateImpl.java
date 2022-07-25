package org.secondopinion.superadmin.daoimpl;


import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.superadmin.dto.User;
import org.springframework.stereotype.Component;

@Component
public class UserDAOHibernateImpl extends BaseUserDAOHibernate {

	@Override
	public void save(User user) throws DataAccessException {

		super.save(user);

	}

}
