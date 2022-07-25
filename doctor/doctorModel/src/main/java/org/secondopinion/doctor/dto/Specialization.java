package org.secondopinion.doctor.dto; 


import javax.persistence.Entity; 
import javax.persistence.Table;

import org.secondopinion.doctor.domain.BaseSpecialization; 


@Entity 
@Table (name="specialization")
public class Specialization extends BaseSpecialization{
}