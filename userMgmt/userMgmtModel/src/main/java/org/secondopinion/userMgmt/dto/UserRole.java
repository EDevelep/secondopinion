package org.secondopinion.userMgmt.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.userMgmt.domain.BaseUserRole;

@Entity
@Table(name = "userrole")
public class UserRole extends BaseUserRole {
}