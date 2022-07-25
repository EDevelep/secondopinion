package org.secondopinion.patient.domain; 

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Date; 
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Socialhistory;
import org.secondopinion.utils.StringUtil; 
import org.secondopinion.utils.ObjectUtil; 

import javax.persistence.Column; 
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length; 
@SuppressWarnings({ "serial"})
@MappedSuperclass 
public abstract class BaseSocialhistory extends BaseDomainObject<Long> {

	public static final String FIELD_socialhistoryid = "socialhistoryid";
	public static final String FIELD_abortions = "abortions";
	public static final String FIELD_childrenNumber = "childrenNumber";
	public static final String FIELD_deliveries = "deliveries";
	public static final String FIELD_grandChildren = "grandChildren";
	public static final String FIELD_maritalStatus = "maritalStatus";
	public static final String FIELD_misCariage = "misCariage";
	public static final String FIELD_nameOfSpouse = "nameOfSpouse";
	public static final String FIELD_occupation = "occupation";
	public static final String FIELD_pregnancies = "pregnancies";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_vaginalSections = "vaginalSections";
	public static final String FIELD_alcoholType = "alcoholType";
	public static final String FIELD_alcoholUsagePerDay = "alcoholUsagePerDay";
	public static final String FIELD_allergies = "allergies";
	public static final String FIELD_drugs = "drugs";
	public static final String FIELD_drugsType = "drugsType";
	public static final String FIELD_tobaccoDuration = "tobaccoDuration";
	public static final String FIELD_tobaccoUsagePerDay = "tobaccoUsagePerDay";
	public static final String FIELD_alcohol = "alcohol";
	public static final String FIELD_satesubmitte = "satesubmitte";
	public static final String FIELD_tobacco = "tobacco";
	public static final String FIELD_tobaccousage = "tobaccousage";
	public static final String FIELD_csection = "csection";
	public static final String FIELD_active = "active";
	public static final String FIELD_datesubmitted = "datesubmitted";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long socialhistoryid;
	private String abortions;
	private Integer childrenNumber;
	private Integer deliveries;
	private Integer grandChildren;
	private String maritalStatus;
	private String misCariage;
	private String nameOfSpouse;
	private String occupation;
	private Integer pregnancies;
	private Long userId;
	private Integer vaginalSections;
	private String alcoholType;
	private String alcoholUsagePerDay;
	private String allergies;
	private String drugs;
	private String drugsType;
	private String tobaccoDuration;
	private String tobaccoUsagePerDay;
	private String alcohol;
	private Date satesubmitte;
	private String tobacco;
	private String tobaccousage;
	private String csection;
	private Character active;
	private Date datesubmitted;

	public void setSocialhistoryid( Long  _socialhistoryid){
		this.socialhistoryid = _socialhistoryid;
	}
	@Id
 	@Column(name = "socialhistoryid") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getSocialhistoryid(){
		 return this.socialhistoryid;
	}
	public void setAbortions( String  _abortions){
		this.abortions = _abortions;
	}
	@Length(max=250)
	@Column (name="abortions")
	public String getAbortions(){
		 return this.abortions;
	}
	public void setChildrenNumber( Integer  _childrenNumber){
		this.childrenNumber = _childrenNumber;
	}
	@Column (name="childrenNumber")
	public Integer getChildrenNumber(){
		 return this.childrenNumber;
	}

	public void setDeliveries( Integer  _deliveries){
		this.deliveries = _deliveries;
	}
	@Column (name="deliveries")
	public Integer getDeliveries(){
		 return this.deliveries;
	}
	public void setGrandChildren( Integer  _grandChildren){
		this.grandChildren = _grandChildren;
	}
	@Column (name="grandChildren")
	public Integer getGrandChildren(){
		 return this.grandChildren;
	}
	public void setMaritalStatus( String  _maritalStatus){
		this.maritalStatus = _maritalStatus;
	}
	@Length(max=45)
	@Column (name="maritalStatus")
	public String getMaritalStatus(){
		 return this.maritalStatus;
	}
	public void setMisCariage( String  _misCariage){
		this.misCariage = _misCariage;
	}
	@Length(max=250)
	@Column (name="misCariage")
	public String getMisCariage(){
		 return this.misCariage;
	}
	public void setNameOfSpouse( String  _nameOfSpouse){
		this.nameOfSpouse = _nameOfSpouse;
	}
	@Length(max=250)
	@Column (name="nameOfSpouse")
	public String getNameOfSpouse(){
		 return this.nameOfSpouse;
	}
	public void setOccupation( String  _occupation){
		this.occupation = _occupation;
	}
	@Length(max=250)
	@Column (name="occupation")
	public String getOccupation(){
		 return this.occupation;
	}
	public void setPregnancies( Integer  _pregnancies){
		this.pregnancies = _pregnancies;
	}
	@Column (name="pregnancies")
	public Integer getPregnancies(){
		 return this.pregnancies;
	}
	public void setUserId( Long  _userId){
		this.userId = _userId;
	}
	@NotNull 
	@Column (name="userId")
	public Long getUserId(){
		 return this.userId;
	}
	public void setVaginalSections( Integer  _vaginalSections){
		this.vaginalSections = _vaginalSections;
	}
	@Column (name="vaginalSections")
	public Integer getVaginalSections(){
		 return this.vaginalSections;
	}
	public void setAlcoholType( String  _alcoholType){
		this.alcoholType = _alcoholType;
	}
	@Length(max=255)
	@Column (name="alcoholType")
	public String getAlcoholType(){
		 return this.alcoholType;
	}
	public void setAlcoholUsagePerDay( String  _alcoholUsagePerDay){
		this.alcoholUsagePerDay = _alcoholUsagePerDay;
	}
	@Length(max=255)
	@Column (name="alcoholUsagePerDay")
	public String getAlcoholUsagePerDay(){
		 return this.alcoholUsagePerDay;
	}
	public void setAllergies( String  _allergies){
		this.allergies = _allergies;
	}
	@Length(max=255)
	@Column (name="allergies")
	public String getAllergies(){
		 return this.allergies;
	}
	public void setDrugs( String  _drugs){
		this.drugs = _drugs;
	}
	@Length(max=255)
	@Column (name="drugs")
	public String getDrugs(){
		 return this.drugs;
	}
	public void setDrugsType( String  _drugsType){
		this.drugsType = _drugsType;
	}
	@Length(max=255)
	@Column (name="drugsType")
	public String getDrugsType(){
		 return this.drugsType;
	}
	public void setTobaccoDuration( String  _tobaccoDuration){
		this.tobaccoDuration = _tobaccoDuration;
	}
	@Length(max=255)
	@Column (name="tobaccoDuration")
	public String getTobaccoDuration(){
		 return this.tobaccoDuration;
	}
	public void setTobaccoUsagePerDay( String  _tobaccoUsagePerDay){
		this.tobaccoUsagePerDay = _tobaccoUsagePerDay;
	}
	@Length(max=255)
	@Column (name="tobaccoUsagePerDay")
	public String getTobaccoUsagePerDay(){
		 return this.tobaccoUsagePerDay;
	}
	public void setAlcohol( String  _alcohol){
		this.alcohol = _alcohol;
	}
	@Length(max=255)
	@Column (name="alcohol")
	public String getAlcohol(){
		 return this.alcohol;
	}
	public void setSatesubmitte( Date  _satesubmitte){
		this.satesubmitte = _satesubmitte;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Temporal(TemporalType.DATE)
	@Column (name="satesubmitte")
	public Date getSatesubmitte(){
		 return this.satesubmitte;
	}
	public void setTobacco( String  _tobacco){
		this.tobacco = _tobacco;
	}
	@Length(max=255)
	@Column (name="tobacco")
	public String getTobacco(){
		 return this.tobacco;
	}
	public void setTobaccousage( String  _tobaccousage){
		this.tobaccousage = _tobaccousage;
	}
	@Length(max=255)
	@Column (name="tobaccousage")
	public String getTobaccousage(){
		 return this.tobaccousage;
	}
	public void setCsection( String  _csection){
		this.csection = _csection;
	}
	@Length(max=255)
	@Column (name="csection")
	public String getCsection(){
		 return this.csection;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}
	public void setDatesubmitted( Date  _datesubmitted){
		this.datesubmitted = _datesubmitted;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Temporal(TemporalType.DATE)
	@Column (name="datesubmitted")
	public Date getDatesubmitted(){
		 return this.datesubmitted;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseSocialhistory other = (BaseSocialhistory)o;
		if(
			ObjectUtil.isEqual(getAbortions(), other.getAbortions()) && 
			ObjectUtil.isEqual(getChildrenNumber(), other.getChildrenNumber()) && 
			ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy()) && 
			ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate()) && 
			ObjectUtil.isEqual(getDeliveries(), other.getDeliveries()) && 
			ObjectUtil.isEqual(getGrandChildren(), other.getGrandChildren()) && 
			ObjectUtil.isEqual(getMaritalStatus(), other.getMaritalStatus()) && 
			ObjectUtil.isEqual(getMisCariage(), other.getMisCariage()) && 
			ObjectUtil.isEqual(getNameOfSpouse(), other.getNameOfSpouse()) && 
			ObjectUtil.isEqual(getOccupation(), other.getOccupation()) && 
			ObjectUtil.isEqual(getPregnancies(), other.getPregnancies()) && 
			ObjectUtil.isEqual(getUserId(), other.getUserId()) && 
			ObjectUtil.isEqual(getVaginalSections(), other.getVaginalSections()) && 
			ObjectUtil.isEqual(getAlcoholType(), other.getAlcoholType()) && 
			ObjectUtil.isEqual(getAlcoholUsagePerDay(), other.getAlcoholUsagePerDay()) && 
			ObjectUtil.isEqual(getAllergies(), other.getAllergies()) && 
			ObjectUtil.isEqual(getDrugs(), other.getDrugs()) && 
			ObjectUtil.isEqual(getDrugsType(), other.getDrugsType()) && 
			ObjectUtil.isEqual(getTobaccoDuration(), other.getTobaccoDuration()) && 
			ObjectUtil.isEqual(getTobaccoUsagePerDay(), other.getTobaccoUsagePerDay()) && 
			ObjectUtil.isEqual(getAlcohol(), other.getAlcohol()) && 
			ObjectUtil.isEqual(getSatesubmitte(), other.getSatesubmitte()) && 
			ObjectUtil.isEqual(getTobacco(), other.getTobacco()) && 
			ObjectUtil.isEqual(getTobaccousage(), other.getTobaccousage()) && 
			ObjectUtil.isEqual(getCsection(), other.getCsection()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
			ObjectUtil.isEqual(getDatesubmitted(), other.getDatesubmitted()) && 
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (socialhistoryid!= null ? socialhistoryid.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.socialhistoryid == null){
			 list.add(new ValidationMessage("Field " + FIELD_socialhistoryid+  " cannot be null"));
		}

		}
		if((this.abortions != null) && (this.abortions.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_abortions+  " cannot be longer than: " + 250));
		}

		if((this.createdBy != null) && (this.createdBy.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 45));
		}

		if((this.maritalStatus != null) && (this.maritalStatus.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_maritalStatus+  " cannot be longer than: " + 45));
		}

		if((this.misCariage != null) && (this.misCariage.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_misCariage+  " cannot be longer than: " + 250));
		}

		if((this.nameOfSpouse != null) && (this.nameOfSpouse.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_nameOfSpouse+  " cannot be longer than: " + 250));
		}

		if((this.occupation != null) && (this.occupation.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_occupation+  " cannot be longer than: " + 250));
		}

		if(this.userId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be null"));
		}

		if((this.alcoholType != null) && (this.alcoholType.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_alcoholType+  " cannot be longer than: " + 255));
		}

		if((this.alcoholUsagePerDay != null) && (this.alcoholUsagePerDay.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_alcoholUsagePerDay+  " cannot be longer than: " + 255));
		}

		if((this.allergies != null) && (this.allergies.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_allergies+  " cannot be longer than: " + 255));
		}

		if((this.drugs != null) && (this.drugs.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_drugs+  " cannot be longer than: " + 255));
		}

		if((this.drugsType != null) && (this.drugsType.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_drugsType+  " cannot be longer than: " + 255));
		}

		if((this.tobaccoDuration != null) && (this.tobaccoDuration.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_tobaccoDuration+  " cannot be longer than: " + 255));
		}

		if((this.tobaccoUsagePerDay != null) && (this.tobaccoUsagePerDay.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_tobaccoUsagePerDay+  " cannot be longer than: " + 255));
		}

		if((this.alcohol != null) && (this.alcohol.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_alcohol+  " cannot be longer than: " + 255));
		}

		if((this.tobacco != null) && (this.tobacco.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_tobacco+  " cannot be longer than: " + 255));
		}

		if((this.tobaccousage != null) && (this.tobaccousage.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_tobaccousage+  " cannot be longer than: " + 255));
		}

		if((this.csection != null) && (this.csection.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_csection+  " cannot be longer than: " + 255));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
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
		str.append("socialhistoryid = " + socialhistoryid + "\n");
;
		str.append("abortions = " + abortions + "\n");
		str.append("childrenNumber = " + childrenNumber + "\n");
		str.append("creartedby = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("deliveries = " + deliveries + "\n");
		str.append("grandChildren = " + grandChildren + "\n");
		str.append("maritalStatus = " + maritalStatus + "\n");
		str.append("misCariage = " + misCariage + "\n");
		str.append("nameOfSpouse = " + nameOfSpouse + "\n");
		str.append("occupation = " + occupation + "\n");
		str.append("pregnancies = " + pregnancies + "\n");
		str.append("userId = " + userId + "\n");
		str.append("vaginalSections = " + vaginalSections + "\n");
		str.append("alcoholType = " + alcoholType + "\n");
		str.append("alcoholUsagePerDay = " + alcoholUsagePerDay + "\n");
		str.append("allergies = " + allergies + "\n");
		str.append("drugs = " + drugs + "\n");
		str.append("drugsType = " + drugsType + "\n");
		str.append("tobaccoDuration = " + tobaccoDuration + "\n");
		str.append("tobaccoUsagePerDay = " + tobaccoUsagePerDay + "\n");
		str.append("alcohol = " + alcohol + "\n");
		str.append("satesubmitte = " + satesubmitte + "\n");
		str.append("tobacco = " + tobacco + "\n");
		str.append("tobaccousage = " + tobaccousage + "\n");
		str.append("csection = " + csection + "\n");
		str.append("active = " + active + "\n");
		str.append("datesubmitted = " + datesubmitted + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (socialhistoryid == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_socialhistoryid, getSocialhistoryid()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getSocialhistoryid();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Socialhistory socialhistory = new Socialhistory();
		socialhistory.setFromDB(false);
		socialhistory.setAbortions(getAbortions());
		socialhistory.setChildrenNumber(getChildrenNumber());
		socialhistory.setCreatedBy(getCreatedBy());
		socialhistory.setCreatedDate(getCreatedDate());
		socialhistory.setDeliveries(getDeliveries());
		socialhistory.setGrandChildren(getGrandChildren());
		socialhistory.setMaritalStatus(getMaritalStatus());
		socialhistory.setMisCariage(getMisCariage());
		socialhistory.setNameOfSpouse(getNameOfSpouse());
		socialhistory.setOccupation(getOccupation());
		socialhistory.setPregnancies(getPregnancies());
		socialhistory.setUserId(getUserId());
		socialhistory.setVaginalSections(getVaginalSections());
		socialhistory.setAlcoholType(getAlcoholType());
		socialhistory.setAlcoholUsagePerDay(getAlcoholUsagePerDay());
		socialhistory.setAllergies(getAllergies());
		socialhistory.setDrugs(getDrugs());
		socialhistory.setDrugsType(getDrugsType());
		socialhistory.setTobaccoDuration(getTobaccoDuration());
		socialhistory.setTobaccoUsagePerDay(getTobaccoUsagePerDay());
		socialhistory.setAlcohol(getAlcohol());
		socialhistory.setSatesubmitte(getSatesubmitte());
		socialhistory.setTobacco(getTobacco());
		socialhistory.setTobaccousage(getTobaccousage());
		socialhistory.setCsection(getCsection());
		socialhistory.setActive(getActive());
		socialhistory.setDatesubmitted(getDatesubmitted());
		//afterClone(socialhistory);
		return socialhistory;
	}
}