package org.secondopinion.userMgmt.dao; 

import org.secondopinion.userMgmt.dto.UserProfilePic;

import org.secondopinion.dao.IDAO;

public interface UserProfilePicDAO extends IDAO<UserProfilePic,Integer >{
	public UserProfilePic getUserProfilePicByUserId(String userId);

	public void saveOrUpdateProfilePic(UserProfilePic userProfilePic);
}