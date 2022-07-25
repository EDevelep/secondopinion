package org.secondopinion.doctor.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.doctor.dto.Staticdata;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage; 
import org.secondopinion.utils.StringUtil; 
import org.secondopinion.utils.ObjectUtil; 

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass; 
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseStaticdata extends BaseDomainObject<Long> {

	public static final String FIELD_staticdataId = "staticdataId";
	public static final String FIELD_datasetType = "datasetType";
	public static final String FIELD_category = "category";
	public static final String FIELD_label = "label";
	public static final String FIELD_value = "value";
	
	private Long staticdataId;
	private String datasetType;
	private String category;
	private String label;
	private String value;
	
	public void setStaticdataId( Long  _staticdataId){
		this.staticdataId = _staticdataId;
	}
	@Id
 	@Column(name = "staticdataId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getStaticdataId(){
		 return this.staticdataId;
	}
	public void setDatasetType( String  _datasetType){
		this.datasetType = _datasetType;
	}
	@Length(max=150)
	@Column (name="datasetType")
	public String getDatasetType(){
		 return this.datasetType;
	}
	public void setCategory( String  _category){
		this.category = _category;
	}
	@Length(max=150)
	@Column (name="category")
	public String getCategory(){
		 return this.category;
	}
	public void setLabel( String  _label){
		this.label = _label;
	}
	@Length(max=150)
	@Column (name="label")
	public String getLabel(){
		 return this.label;
	}
	public void setValue( String  _value){
		this.value = _value;
	}
	@Length(max=45)
	@Column (name="value")
	public String getValue(){
		 return this.value;
	}


	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseStaticdata other = (BaseStaticdata)o;
		if(
			ObjectUtil.isEqual(getDatasetType(), other.getDatasetType()) && 
			ObjectUtil.isEqual(getCategory(), other.getCategory()) && 
			ObjectUtil.isEqual(getLabel(), other.getLabel()) && 
			ObjectUtil.isEqual(getValue(), other.getValue()) && 
			ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy()) && 
			ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (staticdataId!= null ? staticdataId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.staticdataId == null){
			 list.add(new ValidationMessage("Field " + FIELD_staticdataId+  " cannot be null"));
		}

		}
		if((this.datasetType != null) && (this.datasetType.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_datasetType+  " cannot be longer than: " + 150));
		}

		if((this.category != null) && (this.category.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_category+  " cannot be longer than: " + 150));
		}

		if((this.label != null) && (this.label.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_label+  " cannot be longer than: " + 150));
		}

		if((this.value != null) && (this.value.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_value+  " cannot be longer than: " + 45));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
		}
	}
	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 45));
		}
	}
		if(list.size()>0)setHasValidationErrors(true);

		setValidationMessages(list);
		
	}

	@Override
	public final void setAuditFields() {
		if(!isFromDB()){
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("staticdataId = " + staticdataId + "\n");
;
		str.append("datasetType = " + datasetType + "\n");
		str.append("category = " + category + "\n");
		str.append("label = " + label + "\n");
		str.append("value = " + value + "\n");
		str.append("createdby = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (staticdataId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_staticdataId, getStaticdataId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getStaticdataId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Staticdata staticdata = new Staticdata();
		staticdata.setFromDB(false);
		staticdata.setDatasetType(getDatasetType());
		staticdata.setCategory(getCategory());
		staticdata.setLabel(getLabel());
		staticdata.setValue(getValue());
		staticdata.setCreatedDate(getCreatedDate());
		//afterClone(staticdata);
		return staticdata;
	}
}