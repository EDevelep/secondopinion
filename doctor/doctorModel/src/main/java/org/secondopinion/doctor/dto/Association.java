package org.secondopinion.doctor.dto; 


import javax.persistence.Entity; 
import javax.persistence.Table;

import org.secondopinion.doctor.domain.BaseAssociation; 


@Entity 
@Table (name="association")
public class Association extends BaseAssociation{
}