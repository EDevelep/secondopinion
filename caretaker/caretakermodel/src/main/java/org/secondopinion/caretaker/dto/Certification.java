package org.secondopinion.caretaker.dto; 


import javax.persistence.Entity; 
import javax.persistence.Table;

import org.secondopinioncaretaker.domain.BaseCertification; 





@Entity 
@Table (name="certification")
public class Certification extends BaseCertification{
}