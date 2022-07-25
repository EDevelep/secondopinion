package org.secondopinion.patient.dto;

import javax.persistence.Entity;

import javax.persistence.Table;

import org.secondopinion.patient.domain.BaseSurgerydetails;

@Entity
@Table(name = "surgerydetails")
public class Surgerydetails extends BaseSurgerydetails {
}