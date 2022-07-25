package org.secondopinion.userMgmt.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.userMgmt.dto.UserProfilePic;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserProfilePicDAOHibernateImpl extends BaseUserProfilePicDAOHibernate{
	
	private static final String deleteProfilePicSql = "delete from UserProfilePic where userProfilePicId=:userProfilePicId";
	
	@Override
	@Transactional
	public void delete(UserProfilePic userProfilePicRole) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userProfilePicId", userProfilePicRole.getUserProfilePicId());
		executeQuery(deleteProfilePicSql, params);
	}

	@Override
	@Transactional(readOnly=true)
	public UserProfilePic getUserProfilePicByUserId(String userId) {
		
		Criterion criterion = Restrictions.eq(UserProfilePic.FIELD_userId, userId);
		
		List<UserProfilePic> userProfilePics = findByCrieria(criterion);
		
		if(userProfilePics != null && userProfilePics.size()>0){
			return userProfilePics.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void saveOrUpdateProfilePic(UserProfilePic userProfilePic) {
		save(userProfilePic);
	}
}