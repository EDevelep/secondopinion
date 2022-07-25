package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Socialhistory;

public interface SocialhistoryDAO extends IDAO<Socialhistory ,Long >{

	List<Socialhistory> findSocialhistoryByUserId(Long userid);
}