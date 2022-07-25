package org.secondopinion.patient.dto;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.patient.domain.BaseQuestionairre;

@Entity
@Table(name = "questionairre ")
public class Questionairre extends BaseQuestionairre {

}