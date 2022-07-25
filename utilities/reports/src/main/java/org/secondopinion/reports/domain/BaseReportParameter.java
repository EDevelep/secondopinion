package org.secondopinion.reports.domain; 

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.reports.dto.ReportParameter;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.reports.dto.CompanyTemplate;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseReportParameter extends BaseDomainObject<Long> {

	public static final String FIELD_reportParameterId = "reportParameterId";
	public static final String FIELD_reportId = "reportId";
	public static final String FIELD_parameterName = "parameterName";
	public static final String FIELD_parameterValue = "parameterValue";
	public static final String FIELD_parameterType = "parameterType";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long reportParameterId;
	private Long reportId;
	private String parameterName;
	private String parameterValue;
	private String parameterType;

	public void setReportParameterId( Long  _reportParameterId){
		this.reportParameterId = _reportParameterId;
	}
	@Id
// 	@NotNull
	@Column(name = "reportParameterId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getReportParameterId(){
		 return this.reportParameterId;
	}
	public void setReportId( Long  _reportId){
		this.reportId = _reportId;
	}
	@NotNull 
	@Column (name="reportId")
	public Long getReportId(){
		 return this.reportId;
	}
	public void setParameterName( String  _parameterName){
		this.parameterName = _parameterName;
	}
	@Length(max=100)
	@Column (name="parameterName")
	public String getParameterName(){
		 return this.parameterName;
	}
	public void setParameterValue( String  _parameterValue){
		this.parameterValue = _parameterValue;
	}
	@Length(max=150)
	@Column (name="parameterValue")
	public String getParameterValue(){
		 return this.parameterValue;
	}
	public void setParameterType( String  _parameterType){
		this.parameterType = _parameterType;
	}
	@NotNull 
	@Length(max=45)
	@Column (name="parameterType")
	public String getParameterType(){
		 return this.parameterType;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseReportParameter other = (BaseReportParameter)o;
		if(
			ObjectUtil.isEqual(getReportId(), other.getReportId()) && 
			ObjectUtil.isEqual(getParameterName(), other.getParameterName()) && 
			ObjectUtil.isEqual(getParameterValue(), other.getParameterValue()) && 
			ObjectUtil.isEqual(getParameterType(), other.getParameterType()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (reportParameterId!= null ? reportParameterId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.reportParameterId == null){
			 list.add(new ValidationMessage("Field " + FIELD_reportParameterId+  " cannot be null"));
		}

		}
		if(this.reportId == null){
			 list.add(new ValidationMessage("Field " + FIELD_reportId+  " cannot be null"));
		}

		if((this.parameterName != null) && (this.parameterName.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_parameterName+  " cannot be longer than: " + 100));
		}

		if((this.parameterValue != null) && (this.parameterValue.length()>150)){
			 list.add(new ValidationMessage("Field " + FIELD_parameterValue+  " cannot be longer than: " + 150));
		}

		if( StringUtil.isNullOrEmpty(this.parameterType)){
			 list.add(new ValidationMessage("Field " + FIELD_parameterType+  " cannot be null"));
		}

		if((this.parameterType != null) && (this.parameterType.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_parameterType+  " cannot be longer than: " + 45));
		}

		if(this.isFromDB()){
			if( StringUtil.isNullOrEmpty(this.lastUpdatedBy)){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be null"));
			}
		}
		if(this.isFromDB()){
			if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>45)){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 45));
			}
		}
		if(this.isFromDB()){
			if(this.lastUpdatedTs == null){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedTs+  " cannot be null"));
			}
		}
		if(list.size()>0)setHasValidationErrors(true);

		setValidationMessages(list);
		
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("reportParameterId = " + reportParameterId + "\n");
		str.append("reportId = " + reportId + "\n");
		str.append("parameterName = " + parameterName + "\n");
		str.append("parameterValue = " + parameterValue + "\n");
		str.append("parameterType = " + parameterType + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (reportParameterId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_reportParameterId, getReportParameterId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getReportParameterId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ReportParameter reportParameter = new ReportParameter();
		reportParameter.setFromDB(false);
		reportParameter.setReportId(getReportId());
		reportParameter.setParameterName(getParameterName());
		reportParameter.setParameterValue(getParameterValue());
		reportParameter.setParameterType(getParameterType());
		//afterClone(reportParameter);
		return reportParameter;
	}
}