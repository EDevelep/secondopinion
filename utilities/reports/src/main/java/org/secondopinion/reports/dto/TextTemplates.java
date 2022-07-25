package org.secondopinion.reports.dto; 

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.reports.domain.BaseTextTemplates; 

@SuppressWarnings({ "serial"})
@Entity 
@Table (name="texttemplates")
public class TextTemplates extends BaseTextTemplates{
	
}