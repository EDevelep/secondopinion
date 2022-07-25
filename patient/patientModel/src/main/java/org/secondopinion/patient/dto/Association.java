package org.secondopinion.patient.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.patient.domain.BaseAssociation;

@Entity
@Table(name = "association")
public class Association extends BaseAssociation {
}