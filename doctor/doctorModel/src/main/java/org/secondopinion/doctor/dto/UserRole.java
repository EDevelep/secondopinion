package org.secondopinion.doctor.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.doctor.domain.BaseUserRole;



@Entity
@Table(name = "userrole")
public class UserRole extends BaseUserRole {
}