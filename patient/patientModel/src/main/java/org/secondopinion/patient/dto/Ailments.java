package org.secondopinion.patient.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.patient.domain.BaseAilments; 





@Entity 
@Table (name="ailments")
public class Ailments extends BaseAilments{
}