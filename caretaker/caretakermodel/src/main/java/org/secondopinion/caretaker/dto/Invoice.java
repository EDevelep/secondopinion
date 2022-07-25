package org.secondopinion.caretaker.dto; 



import javax.persistence.Entity; 
import javax.persistence.Table;

import org.secondopinioncaretaker.domain.BaseInvoice; 





@Entity 
@Table (name="invoice")
public class Invoice extends BaseInvoice{

}