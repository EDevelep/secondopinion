package org.secondopinion.doctor.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.secondopinion.doctor.dto.Association;
import org.secondopinion.doctor.dto.Certification;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.DoctorAddress;
import org.secondopinion.doctor.dto.DoctorSearchRequest;
import org.secondopinion.doctor.dto.Feedetails;
import org.secondopinion.doctor.dto.Personaldetail;
import org.secondopinion.doctor.dto.Specialization;
import org.secondopinion.request.Response;
import org.springframework.web.multipart.MultipartFile;

public interface IDoctorService {

	// Doctor
	Doctor getDoctorBySpecilazation(Long specializationId);
	Response<List<Doctor>> getAllDoctors(Integer pageNum,  Integer maxResults);
	Collection<Doctor> getAllDoctorsBySearchCritieria(DoctorSearchRequest doctorSearchRequest );
	Map<String, Object> getMyReports(Long doctorid);
	
	 void uploadDoctorSignature(Long doctorId, MultipartFile doctorSignature);
	// Doctor Address
	void saveDoctorAddress(DoctorAddress doctorAddress);
	DoctorAddress getDoctorAddressById(Long addressId);
	Collection<DoctorAddress> getDoctorAddressByDoctorId(Long doctorId);
	void deleteDoctorAddress(Long addressId);

	// Doctor's fee details
	void saveDoctorFeeDetails(Feedetails feeDetails);
	Feedetails getFeeDetailsById(Long feedetailsId);
	Collection<Feedetails> getDoctorFeeDetails(Long doctorId);
	void deleteDoctorFeeDetails(Long feeDetailsId);

	//Specialization
	void saveSpecialization(Specialization specialization);
	Specialization getSpecailizationById(Long specializationId);
	Collection<Specialization> getSpecializationsByDoctorId(Long doctorId);
	Collection<Specialization> getDoctorSpecialization();
	void deleteSpecialization(Long specializationId);

	
	//Certification
	void saveCertification(Certification certification);
	Certification getCertificationById(Long certificationId);
	Collection<Certification> getDoctorCertifications(Long doctorId);
	void deleteDoctorCertication(Long certificationId);
	
	//Personaldetail
	void savePersonalDetail(Personaldetail personaldetail, MultipartFile profilePic);
	Personaldetail getPersonaldetailById(Long personaldetailId);
	Personaldetail getDoctorPersonaldetailByDoctorId(Long doctorId);
	void deleteDoctorPersonalDetail(Long personaldetailId);
	void uploadProfilePic(Long doctorId, MultipartFile profilePic);
	
	//Association
	void saveAssociation(Association association);
	List<Association> getAssociationById(Long associationId);
	Collection<Association> getAssocaiationByDoctorId(Long doctorId);
	void deleteDoctorAssociation(Long associationId);
	
	List<Association> findAllAssociation();
	

}
