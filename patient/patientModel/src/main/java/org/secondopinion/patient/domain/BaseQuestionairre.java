package org.secondopinion.patient.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Questionairre;
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
public abstract class BaseQuestionairre extends BaseDomainObject<Long> {

	public static final String FIELD_questionairreid = "questionairreid";
	public static final String FIELD_alcoholType = "alcoholType";
	public static final String FIELD_alcoholUsagePerDay = "alcoholUsagePerDay";
	public static final String FIELD_allergies = "allergies";
	public static final String FIELD_drugs = "drugs";
	public static final String FIELD_drugsType = "drugsType";
	public static final String FIELD_reason = "reason";
	public static final String FIELD_tobaccoDuration = "tobaccoDuration";
	public static final String FIELD_tobaccoUsagePerDay = "tobaccoUsagePerDay";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_alcohol = "alcohol";
	public static final String FIELD_active = "active";
	public static final String FIELD_tobacco = "tobacco";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long questionairreid;
	private String alcoholType;
	private String alcoholUsagePerDay;
	private String allergies;
	private String drugs;
	private String drugsType;
	private String reason;
	private String tobaccoDuration;
	private String tobaccoUsagePerDay;
	private Long userId;
	private String alcohol;
	private Character active;
	private String tobacco;


	public void setQuestionairreid( Long  _questionairreid){
		this.questionairreid = _questionairreid;
	}
	@Id
 	@Column(name = "questionairreid") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getQuestionairreid(){
		 return this.questionairreid;
	}
	public void setAlcoholType( String  _alcoholType){
		this.alcoholType = _alcoholType;
	}
	@Length(max=250)
	@Column (name="alcoholType")
	public String getAlcoholType(){
		 return this.alcoholType;
	}
	public void setAlcoholUsagePerDay( String  _alcoholUsagePerDay){
		this.alcoholUsagePerDay = _alcoholUsagePerDay;
	}
	@Length(max=250)
	@Column (name="alcoholUsagePerDay")
	public String getAlcoholUsagePerDay(){
		 return this.alcoholUsagePerDay;
	}
	public void setAllergies( String  _allergies){
		this.allergies = _allergies;
	}
	@Length(max=250)
	@Column (name="allergies")
	public String getAllergies(){
		 return this.allergies;
	}
	public void setDrugs( String  _drugs){
		this.drugs = _drugs;
	}
	@Length(max=250)
	@Column (name="drugs")
	public String getDrugs(){
		 return this.drugs;
	}
	public void setDrugsType( String  _drugsType){
		this.drugsType = _drugsType;
	}
	@Length(max=250)
	@Column (name="drugsType")
	public String getDrugsType(){
		 return this.drugsType;
	}
	public void setReason( String  _reason){
		this.reason = _reason;
	}
	@Length(max=1000)
	@Column (name="reason")
	public String getReason(){
		 return this.reason;
	}
	public void setTobaccoDuration( String  _tobaccoDuration){
		this.tobaccoDuration = _tobaccoDuration;
	}
	@Length(max=250)
	@Column (name="tobaccoDuration")
	public String getTobaccoDuration(){
		 return this.tobaccoDuration;
	}
	public void setTobaccoUsagePerDay( String  _tobaccoUsagePerDay){
		this.tobaccoUsagePerDay = _tobaccoUsagePerDay;
	}
	@Length(max=250)
	@Column (name="tobaccoUsagePerDay")
	public String getTobaccoUsagePerDay(){
		 return this.tobaccoUsagePerDay;
	}
	public void setUserId( Long  _userId){
		this.userId = _userId;
	}
	@NotNull 
	@Column (name="userId")
	public Long getUserId(){
		 return this.userId;
	}
	public void setAlcohol( String  _alcohol){
		this.alcohol = _alcohol;
	}
	@Length(max=250)
	@Column (name="alcohol")
	public String getAlcohol(){
		 return this.alcohol;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setTobacco( String  _tobacco){
		this.tobacco = _tobacco;
	}
	@Length(max=255)
	@Column (name="tobacco")
	public String getTobacco(){
		 return this.tobacco;
	}

	
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseQuestionairre other = (BaseQuestionairre)o;
		if(
			ObjectUtil.isEqual(getAlcoholType(), other.getAlcoholType()) && 
			ObjectUtil.isEqual(getAlcoholUsagePerDay(), other.getAlcoholUsagePerDay()) && 
			ObjectUtil.isEqual(getAllergies(), other.getAllergies()) && 
			ObjectUtil.isEqual(getDrugs(), other.getDrugs()) && 
			ObjectUtil.isEqual(getDrugsType(), other.getDrugsType()) && 
			ObjectUtil.isEqual(getReason(), other.getReason()) && 
			ObjectUtil.isEqual(getTobaccoDuration(), other.getTobaccoDuration()) && 
			ObjectUtil.isEqual(getTobaccoUsagePerDay(), other.getTobaccoUsagePerDay()) && 
			ObjectUtil.isEqual(getUserId(), other.getUserId()) && 
			ObjectUtil.isEqual(getAlcohol(), other.getAlcohol()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getTobacco(), other.getTobacco()) && 
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
		result = result + (questionairreid!= null ? questionairreid.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.questionairreid == null){
			 list.add(new ValidationMessage("Field " + FIELD_questionairreid+  " cannot be null"));
		}

		}
		if((this.alcoholType != null) && (this.alcoholType.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_alcoholType+  " cannot be longer than: " + 250));
		}

		if((this.alcoholUsagePerDay != null) && (this.alcoholUsagePerDay.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_alcoholUsagePerDay+  " cannot be longer than: " + 250));
		}

		if((this.allergies != null) && (this.allergies.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_allergies+  " cannot be longer than: " + 250));
		}

		if((this.drugs != null) && (this.drugs.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_drugs+  " cannot be longer than: " + 250));
		}

		if((this.drugsType != null) && (this.drugsType.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_drugsType+  " cannot be longer than: " + 250));
		}

		if((this.reason != null) && (this.reason.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_reason+  " cannot be longer than: " + 1000));
		}

		if((this.tobaccoDuration != null) && (this.tobaccoDuration.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_tobaccoDuration+  " cannot be longer than: " + 250));
		}

		if((this.tobaccoUsagePerDay != null) && (this.tobaccoUsagePerDay.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_tobaccoUsagePerDay+  " cannot be longer than: " + 250));
		}

		if(this.userId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be null"));
		}

		if((this.alcohol != null) && (this.alcohol.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_alcohol+  " cannot be longer than: " + 250));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

		if((this.tobacco != null) && (this.tobacco.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_tobacco+  " cannot be longer than: " + 255));
		}

		if((this.createdBy != null) && (this.createdBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
		}

	if(this.isFromDB()){
		if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>100)){
			 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 100));
		}
	}
		if(list.size()>0)setHasValidationErrors(true);

		setValidationMessages(list);
		
	}

	@Override
	public final void setAuditFields() {
		if(!isFromDB()){
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("questionairreid = " + questionairreid + "\n");
;
		str.append("alcoholType = " + alcoholType + "\n");
		str.append("alcoholUsagePerDay = " + alcoholUsagePerDay + "\n");
		str.append("allergies = " + allergies + "\n");
		str.append("drugs = " + drugs + "\n");
		str.append("drugsType = " + drugsType + "\n");
		str.append("reason = " + reason + "\n");
		str.append("tobaccoDuration = " + tobaccoDuration + "\n");
		str.append("tobaccoUsagePerDay = " + tobaccoUsagePerDay + "\n");
		str.append("userId = " + userId + "\n");
		str.append("alcohol = " + alcohol + "\n");
		str.append("active = " + active + "\n");
		str.append("tobacco = " + tobacco + "\n");
		str.append("creartedby = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (questionairreid == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_questionairreid, getQuestionairreid()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getQuestionairreid();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Questionairre questionairre = new Questionairre();
		questionairre.setFromDB(false);
		questionairre.setAlcoholType(getAlcoholType());
		questionairre.setAlcoholUsagePerDay(getAlcoholUsagePerDay());
		questionairre.setAllergies(getAllergies());
		questionairre.setDrugs(getDrugs());
		questionairre.setDrugsType(getDrugsType());
		questionairre.setReason(getReason());
		questionairre.setTobaccoDuration(getTobaccoDuration());
		questionairre.setTobaccoUsagePerDay(getTobaccoUsagePerDay());
		questionairre.setUserId(getUserId());
		questionairre.setAlcohol(getAlcohol());
		questionairre.setActive(getActive());
		questionairre.setTobacco(getTobacco());
		questionairre.setCreatedBy(getCreatedBy());
		questionairre.setCreatedDate(getCreatedDate());
		//afterClone(questionairre);
		return questionairre;
	}
}