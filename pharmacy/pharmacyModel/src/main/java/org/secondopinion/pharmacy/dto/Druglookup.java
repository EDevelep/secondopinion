package org.secondopinion.pharmacy.dto; 


import javax.persistence.Entity; 
import javax.persistence.Table; 

import org.secondopinion.pharmacy.domain.BaseDruglookup; 



@Entity 
@Table (name="druglookup")
public class Druglookup extends BaseDruglookup{
	
	public enum DrugTypeEnum {
		HOMEOPATHY, ALLOPATHY
	}
	
	public enum DrugFormEnum {
		SYRUP, TABLET, VACCINE, GEL_OR_CREAM, SYRANJEE, OTHER
	}
}