package org.secondopinion.domain;

import java.util.Collection;

public interface IUser {
	Long getUserId();

	Long getCompanyId();

	Collection<Object[]> getRoles();
}
