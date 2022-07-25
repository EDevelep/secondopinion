package org.secondopinion.userMgmt.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.userMgmt.domain.BaseUserProfilePic;

@Entity
@Table(name = "userprofilepic")
public class UserProfilePic extends BaseUserProfilePic {
}