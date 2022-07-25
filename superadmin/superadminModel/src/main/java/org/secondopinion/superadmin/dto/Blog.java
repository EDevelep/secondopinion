package org.secondopinion.superadmin.dto; 


import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;

import org.secondopinion.superadmin.domain.BaseBlog; 





@SuppressWarnings({ "serial"})
@Entity 
@Table (name="blog")
public class Blog extends BaseBlog{
}