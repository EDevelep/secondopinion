package org.secondopinion.doctor.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.doctor.domain.BaseDoctorratings;

@Entity
@Table(name = "doctorratings")
public class Doctorratings extends BaseDoctorratings {
}