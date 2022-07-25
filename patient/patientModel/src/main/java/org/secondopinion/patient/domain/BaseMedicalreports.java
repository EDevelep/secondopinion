package org.secondopinion.patient.domain; 

import java.util.ArrayList; 
import java.util.List; 
import java.util.Date;  
import org.secondopinion.utils.threadlocal.AppThreadLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField; 
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Medicalreports;
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
@MappedSuperclass 
public abstract class BaseMedicalreports extends BaseDomainObject<Long> {

	public static final String FIELD_medicalReportsId = "medicalReportsId";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_alignment = "alignment";
	public static final String FIELD_appointmentId = "appointmentId";
	public static final String FIELD_doctorAppointmentId = "doctorAppointmentId";
	public static final String FIELD_diagnosticCenterAppointmentId = "diagnosticCenterAppointmentId";
	public static final String FIELD_appointmentDate = "appointmentDate";
	public static final String FIELD_medicalTestId = "medicalTestId";
	public static final String FIELD_reportName = "reportName";
	public static final String FIELD_reportTakenOn = "reportTakenOn";
	public static final String FIELD_documentName = "documentName";
	public static final String FIELD_documentLocation = "documentLocation";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long medicalReportsId;
	private Long userId;
	private String alignment;
	private Long appointmentId;
	private Long doctorAppointmentId;
	private Long diagnosticCenterAppointmentId;
	private Date appointmentDate;
	private Long medicalTestId;
	private String reportName;
	private Date reportTakenOn;
	private String documentName;
	private String documentLocation;
	private Character active;
	

	public void setMedicalReportsId( Long  _medicalReportsId){
		this.medicalReportsId = _medicalReportsId;
	}
	@Id
 	@Column(name = "medicalReportsId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMedicalReportsId(){
		 return this.medicalReportsId;
	}
	public void setUserId( Long  _userId){
		this.userId = _userId;
	}
	@NotNull 
	@Column (name="userId")
	public Long getUserId(){
		 return this.userId;
	}
	public void setAlignment( String  _alignment){
		this.alignment = _alignment;
	}
	@Length(max=1000)
	@Column (name="alignment")
	public String getAlignment(){
		 return this.alignment;
	}
	public void setAppointmentId( Long  _appointmentId){
		this.appointmentId = _appointmentId;
	}
	@NotNull 
	@Column (name="appointmentId")
	public Long getAppointmentId(){
		 return this.appointmentId;
	}
	public void setDoctorAppointmentId(Long doctorAppointmentId) {
		this.doctorAppointmentId = doctorAppointmentId;
	}
	@Column (name="doctorAppointmentId")
	public Long getDoctorAppointmentId() {
		return doctorAppointmentId;
	}
	public void setDiagnosticCenterAppointmentId(Long diagnosticCenterAppointmentId) {
		this.diagnosticCenterAppointmentId = diagnosticCenterAppointmentId;
	}
	@Column (name="diagnosticCenterAppointmentId")
	public Long getDiagnosticCenterAppointmentId() {
		return diagnosticCenterAppointmentId;
	}
	public void setAppointmentDate( Date  _appointmentDate){
		this.appointmentDate = _appointmentDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	@Temporal(TemporalType.DATE)
	@Column (name="appointmentDate")
	public Date getAppointmentDate(){
		 return this.appointmentDate;
	}
	public void setMedicalTestId( Long  _medicalTestId){
		this.medicalTestId = _medicalTestId;
	}
	@NotNull 
	@Column (name="medicalTestId")
	public Long getMedicalTestId(){
		 return this.medicalTestId;
	}
	public void setReportName( String  _reportName){
		this.reportName = _reportName;
	}
	@Length(max=250)
	@Column (name="reportName")
	public String getReportName(){
		 return this.reportName;
	}
	public void setReportTakenOn( Date  _reportTakenOn){
		this.reportTakenOn = _reportTakenOn;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	@Column (name="reportTakenOn")
	public Date getReportTakenOn(){
		 return this.reportTakenOn;
	}
	public void setDocumentName( String  _documentName){
		this.documentName = _documentName;
	}
	@Length(max=250)
	@Column (name="documentName")
	public String getDocumentName(){
		 return this.documentName;
	}
	public void setDocumentLocation( String  _documentLocation){
		this.documentLocation = _documentLocation;
	}
	@Length(max=250)
	@Column (name="documentLocation")
	public String getDocumentLocation(){
		 return this.documentLocation;
	}
	public void setActive( Character  _active){
		this.active = _active;
	}
	@NotNull 
	@Column (name="active")
	public Character getActive(){
		 return this.active;
	}


	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseMedicalreports other = (BaseMedicalreports)o;
		if(
			ObjectUtil.isEqual(getUserId(), other.getUserId()) && 
			ObjectUtil.isEqual(getAlignment(), other.getAlignment()) && 
			ObjectUtil.isEqual(getAppointmentId(), other.getAppointmentId()) && 
			ObjectUtil.isEqual(getDoctorAppointmentId(), other.getDoctorAppointmentId()) && 
			ObjectUtil.isEqual(getDiagnosticCenterAppointmentId(), other.getDiagnosticCenterAppointmentId()) && 
			ObjectUtil.isEqual(getAppointmentDate(), other.getAppointmentDate()) && 
			ObjectUtil.isEqual(getMedicalTestId(), other.getMedicalTestId()) && 
			ObjectUtil.isEqual(getReportName(), other.getReportName()) && 
			ObjectUtil.isEqual(getReportTakenOn(), other.getReportTakenOn()) && 
			ObjectUtil.isEqual(getDocumentName(), other.getDocumentName()) && 
			ObjectUtil.isEqual(getDocumentLocation(), other.getDocumentLocation()) && 
			ObjectUtil.isEqual(getActive(), other.getActive()) && 
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
		result = result + (medicalReportsId!= null ? medicalReportsId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.medicalReportsId == null){
			 list.add(new ValidationMessage("Field " + FIELD_medicalReportsId+  " cannot be null"));
		}

		}
		if(this.userId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be null"));
		}

		if((this.alignment != null) && (this.alignment.length()>1000)){
			 list.add(new ValidationMessage("Field " + FIELD_alignment+  " cannot be longer than: " + 1000));
		}

		if(this.appointmentId == null){
			 list.add(new ValidationMessage("Field " + FIELD_appointmentId+  " cannot be null"));
		}
		if(this.doctorAppointmentId == null){
			 list.add(new ValidationMessage("Field " + FIELD_doctorAppointmentId+  " cannot be null"));
		}
		if(this.diagnosticCenterAppointmentId == null){
			 list.add(new ValidationMessage("Field " + FIELD_diagnosticCenterAppointmentId+  " cannot be null"));
		}

		if((this.reportName != null) && (this.reportName.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_reportName+  " cannot be longer than: " + 250));
		}

		if((this.documentName != null) && (this.documentName.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_documentName+  " cannot be longer than: " + 250));
		}

		if((this.documentLocation != null) && (this.documentLocation.length()>250)){
			 list.add(new ValidationMessage("Field " + FIELD_documentLocation+  " cannot be longer than: " + 250));
		}

		if(this.active == null){
			 list.add(new ValidationMessage("Field " + FIELD_active+  " cannot be null"));
		}

	if(this.isFromDB()){
		if((this.createdBy != null) && (this.createdBy.length()>255)){
			 list.add(new ValidationMessage("Field " + FIELD_createdBy+  " cannot be longer than: " + 255));
		}
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
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("medicalReportsId = " + medicalReportsId + "\n");
;
		str.append("userId = " + userId + "\n");
		str.append("alignment = " + alignment + "\n");
		str.append("appointmentId = " + appointmentId + "\n");
		str.append("doctorAppointmentId = " + doctorAppointmentId + "\n");
		str.append("diagnosticCenterAppointmentId = " + diagnosticCenterAppointmentId + "\n");
		str.append("appointmentDate = " + appointmentDate + "\n");
		str.append("medicalTestId = " + medicalTestId + "\n");
		str.append("reportName = " + reportName + "\n");
		str.append("reportTakenOn = " + reportTakenOn + "\n");
		str.append("documentName = " + documentName + "\n");
		str.append("documentLocation = " + documentLocation + "\n");
		str.append("active = " + active + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (medicalReportsId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_medicalReportsId, getMedicalReportsId()));
		return list;
	}

	@Transient
	@Override
	public Long getId(){
		return getMedicalReportsId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Medicalreports medicalreports = new Medicalreports();
		medicalreports.setFromDB(false);
		medicalreports.setUserId(getUserId());
		medicalreports.setAlignment(getAlignment());
		medicalreports.setAppointmentId(getAppointmentId());
		medicalreports.setDoctorAppointmentId(getDoctorAppointmentId());
		medicalreports.setDiagnosticCenterAppointmentId(getDiagnosticCenterAppointmentId());
		medicalreports.setAppointmentDate(getAppointmentDate());
		medicalreports.setMedicalTestId(getMedicalTestId());
		medicalreports.setReportName(getReportName());
		medicalreports.setReportTakenOn(getReportTakenOn());
		medicalreports.setDocumentName(getDocumentName());
		medicalreports.setDocumentLocation(getDocumentLocation());
		medicalreports.setActive(getActive());
		medicalreports.setCreatedDate(getCreatedDate());
		//afterClone(medicalreports);
		return medicalreports;
	}
}