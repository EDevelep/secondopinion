package org.secondopinion.pharmacy.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;

import org.secondopinion.pharmacy.domain.BaseInventory; 





@SuppressWarnings({ "serial"})
@Entity 
@Table (name="inventory")
public class Inventory extends BaseInventory{
}