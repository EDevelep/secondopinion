
package org.secondopinion.doctor.service.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.secondopinion.doctor.dao.AssociationDAO;
import org.secondopinion.doctor.dao.CertificationDAO;
import org.secondopinion.doctor.dao.DoctorAddressDAO;
import org.secondopinion.doctor.dao.DoctorDAO;
import org.secondopinion.doctor.dao.FeedetailsDAO;
import org.secondopinion.doctor.dao.PersonaldetailDAO;
import org.secondopinion.doctor.dao.SpecializationDAO;
import org.secondopinion.doctor.domain.BaseAssociation;
import org.secondopinion.doctor.domain.BaseCertification;
import org.secondopinion.doctor.domain.BaseDoctorAddress;
import org.secondopinion.doctor.domain.BaseFeedetails;
import org.secondopinion.doctor.domain.BasePersonaldetail;
import org.secondopinion.doctor.dto.Association;
import org.secondopinion.doctor.dto.Certification;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.DoctorAddress;
import org.secondopinion.doctor.dto.DoctorSaarchDTO;
import org.secondopinion.doctor.dto.DoctorSearchRequest;
import org.secondopinion.doctor.dto.Feedetails;
import org.secondopinion.doctor.dto.Personaldetail;
import org.secondopinion.doctor.dto.Specialization;
import org.secondopinion.doctor.service.IDoctorService;
import org.secondopinion.doctor.service.rest.DoctorElasticSearchRestAPIService;
import org.secondopinion.request.Response;
import org.secondopinion.utils.MailProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.transfer.Copy;
import com.google.common.collect.ImmutableList;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private FeedetailsDAO feedetailsDAO;

	@Autowired
	private SpecializationDAO specializationDAO;

	@Autowired
	private AssociationDAO associationDAO;

	@Autowired
	private DoctorDAO doctorDAO;

	@Autowired
	private DoctorAddressDAO doctorAddressDAO;

	@Autowired
	private CertificationDAO certificationDAO;

	@Autowired
	private PersonaldetailDAO personalDetailDAO;

	@Autowired
	private MailProperties mailProperties;

	@Autowired
	private DoctorElasticSearchRestAPIService doctorElasticSearchRestAPIService;

	@Override
	@Transactional(readOnly = true)
	public Collection<Specialization> getDoctorSpecialization() {
		return specializationDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Doctor getDoctorBySpecilazation(Long specializationId) {
		Specialization specialization = specializationDAO.findspecializationById(specializationId);
		if (Objects.isNull(specialization)) {
			throw new IllegalArgumentException("Specialization not found : " + specializationId);
		}
		Doctor doctor = doctorDAO.findDoctorById(specialization.getDoctorId());

		return buildDoctorObjectWithChildObjects(doctor);
	}

	private Doctor buildDoctorObjectWithChildObjects(Doctor doctor) {
		doctor.setPassword("");
		doctor.setAssociations(associationDAO.findByProperty(BaseAssociation.FIELD_doctorId, doctor.getDoctorId()));
		doctor.setFeedetails(feedetailsDAO.findByProperty(BaseFeedetails.FIELD_doctorId, doctor.getDoctorId()));
		doctor.setDoctorAddresses(
				doctorAddressDAO.findByProperty(BaseDoctorAddress.FIELD_doctorId, doctor.getDoctorId()));
		doctor.setCertifications(
				certificationDAO.findByProperty(BaseCertification.FIELD_doctorId, doctor.getDoctorId()));

		Personaldetail pd = personalDetailDAO.findOneByProperty(BasePersonaldetail.FIELD_doctorId,
				doctor.getDoctorId());
		doctor.setPersonaldetail(pd);

		return doctor;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Doctor>> getAllDoctors(Integer pageNum, @PathVariable Integer maxResults) {
		Response<List<Doctor>> doctors = doctorDAO.getAllDoctors(pageNum, maxResults);
		List<Doctor> listDoctor = doctors.getData();
		listDoctor.stream().forEach(n -> n.setPassword(" "));
		doctors.setData(listDoctor);
		return doctors;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getMyReports(Long doctorid) {
		return doctorDAO.getMyReports(doctorid);
	}

	// specialization
	@Override
	@Transactional
	public void saveSpecialization(Specialization specialization) {
		specializationDAO.save(specialization);
	}

	@Override
	@Transactional(readOnly = true)
	public Specialization getSpecailizationById(Long specializationId) {
		return specializationDAO.findspecializationById(specializationId);
	}

	@Override
	@Transactional(readOnly = true)
	public void deleteSpecialization(Long specializationId) {
		Specialization specialization = specializationDAO.findspecializationById(specializationId);
		if (Objects.isNull(specialization)) {
			throw new IllegalArgumentException("specialization not found.");
		}
		specialization.setActive('N');
		specializationDAO.save(specialization);
	}

	@Override
	@Transactional
	public List<Specialization> getSpecializationsByDoctorId(Long doctorId) {
		return specializationDAO.findSpecializationsByDoctorId(doctorId);
	}

	@Override
	@Transactional
	public void saveDoctorFeeDetails(Feedetails feeDetails) {
		feedetailsDAO.save(feeDetails);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Feedetails> getDoctorFeeDetails(Long doctorId) {
		return feedetailsDAO.findDoctorFeeDetailsBydoctorId(doctorId);
	}

	@Override
	@Transactional()
	public void deleteDoctorFeeDetails(Long feeDetailsId) {
		Feedetails feedetails = feedetailsDAO.findfeeDetailsById(feeDetailsId);
		if (Objects.isNull(feedetails)) {
			throw new IllegalArgumentException("feedetails not found.");
		}
		feedetails.setActive('N');
		feedetailsDAO.save(feedetails);
	}

	@Override
	@Transactional(readOnly = true)
	public Feedetails getFeeDetailsById(Long feedetailsId) {
		return feedetailsDAO.findfeeDetailsById(feedetailsId);
	}

	@Override
	@Transactional
	public void saveDoctorAddress(DoctorAddress doctorAddress) {
		doctorAddressDAO.save(doctorAddress);
		Doctor doctor = doctorDAO.findById(doctorAddress.getDoctorId());
		if (Objects.nonNull(doctor)) {
			DoctorSaarchDTO doctorSaarchDTO = DoctorSaarchDTO.buildDoctorDTOObject(doctor);
			doctorSaarchDTO.setLatitude(doctorAddress.getLatitude());
			doctorSaarchDTO.setLongitude(doctorAddress.getLongitude());
			//doctorElasticSearchRestAPIService.updateDoctorElasticSearchData(doctorSaarchDTO);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Collection<DoctorAddress> getDoctorAddressByDoctorId(Long doctorId) {
		return doctorAddressDAO.findDoctorAddressBydoctorId(doctorId);
	}

	@Override
	@Transactional
	public void deleteDoctorAddress(Long doctorAddressId) {
		DoctorAddress address = doctorAddressDAO.findDoctorAddressById(doctorAddressId);
		if (Objects.isNull(address)) {
			throw new IllegalArgumentException("Address not found.");
		}
		address.setActive('N');
		doctorAddressDAO.delete(address);
	}

	@Override
	@Transactional(readOnly = true)
	public DoctorAddress getDoctorAddressById(Long addressId) {
		return doctorAddressDAO.findDoctorAddressById(addressId);
	}

	/**
	 * @return the mailProperties
	 */
	public MailProperties getMailProperties() {
		return mailProperties;
	}

	/**
	 * @param mailProperties the mailProperties to set
	 */
	public void setMailProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties;
	}

	@Override
	@Transactional
	public void saveCertification(Certification certification) {
		certificationDAO.save(certification);

	}

	@Override
	@Transactional(readOnly = true)
	public Certification getCertificationById(Long certificationId) {
		return certificationDAO.findCertificationById(certificationId);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Certification> getDoctorCertifications(Long doctorId) {
		return certificationDAO.findDoctorCertificationsBydoctorId(doctorId);
	}

	@Override
	@Transactional
	public void deleteDoctorCertication(Long certificationId) {
		Certification certification = certificationDAO.findCertificationById(certificationId);
		if (Objects.isNull(certification)) {
			throw new IllegalArgumentException("Certification not found.");
		}
		certification.setActive('N');
		certificationDAO.save(certification);

	}

	@Override
	@Transactional
	public void savePersonalDetail(Personaldetail personaldetail, MultipartFile profilePic) {
		personalDetailDAO.save(personaldetail);

	}

	@Override
	@Transactional(readOnly = true)
	public Personaldetail getPersonaldetailById(Long personaldetailId) {
		return personalDetailDAO.findPersonaldetailByIdById(personaldetailId);
	}

	@Override
	@Transactional(readOnly = true)
	public Personaldetail getDoctorPersonaldetailByDoctorId(Long doctorId) {
		return personalDetailDAO.findDoctorPersonaldetailByDoctorId(doctorId);
	}

	@Override
	@Transactional
	public void deleteDoctorPersonalDetail(Long personaldetailId) {
		Personaldetail personaldetail = getPersonaldetailById(personaldetailId);
		if (Objects.isNull(personaldetail)) {
			throw new IllegalArgumentException("personaldetail not found.");
		}
		personaldetail.setActive('N');
		personalDetailDAO.save(personaldetail);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> getAllDoctorsBySearchCritieria(DoctorSearchRequest doctorSearchRequest) {
		return doctorDAO.getAllDoctorsBySearchCritieria(doctorSearchRequest);
	}

	@Override
	@Transactional
	public void uploadProfilePic(Long doctorId, MultipartFile profilePic) {

		if (Objects.isNull(profilePic)) {
			throw new IllegalArgumentException("Please upload profile pic");
		}

		Doctor doctor = doctorDAO.findDoctorById(doctorId);
		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException("Doctor not found");
		}
		Personaldetail personaldetail = personalDetailDAO.findDoctorPersonaldetailByDoctorId(doctorId);
		if (Objects.isNull(personaldetail)) {
			personaldetail = new Personaldetail();
			personaldetail.setDoctorId(doctorId);
		}

		try {
			byte[] profilePicBlob = profilePic.getBytes();
			personaldetail.setProfilePic(profilePicBlob);
		} catch (IOException e) {
			throw new IllegalArgumentException("Invalid Profile pic.");
		}
		personalDetailDAO.save(personaldetail);
	}

	@Override
	public void saveAssociation(Association association) {
		associationDAO.save(association);
	}

	@Override
	public List<Association> getAssociationById(Long associationId) {
		return associationDAO.findassociationById(associationId);
	}

	@Override
	public Collection<Association> getAssocaiationByDoctorId(Long doctorId) {
		return associationDAO.findAssocaiationByDoctorId(doctorId);
	}

	@Override
	@Transactional
	public void deleteDoctorAssociation(Long associationId) {
		Association association = associationDAO.findById(associationId);
		if (Objects.isNull(association)) {
			throw new IllegalArgumentException("association not found");
		}
		association.setActive('N');
		associationDAO.save(association);
	}

	@Override
	@Transactional
	public void uploadDoctorSignature(Long doctorId, MultipartFile doctorSignature) {

		// add doctor siganature
		if (Objects.isNull(doctorSignature)) {
			throw new IllegalArgumentException("Please upload profile pic");
		}

		Doctor doctor = doctorDAO.findDoctorById(doctorId);
		if (Objects.isNull(doctor)) {
			throw new IllegalArgumentException("Doctor not found");
		}
		Personaldetail personaldetail = personalDetailDAO.findDoctorPersonaldetailByDoctorId(doctorId);
		if (Objects.isNull(personaldetail)) {
			personaldetail = new Personaldetail();
			personaldetail.setDoctorId(doctorId);
		}

		try {
			byte[] profilePicBlob = doctorSignature.getBytes();
			personaldetail.setDoctorSignature(profilePicBlob);
		} catch (IOException e) {
			throw new IllegalArgumentException("Invalid Profile pic.");
		}
		personalDetailDAO.save(personaldetail);

	}

	@Override
	@Transactional
	public List<Association> findAllAssociation() {
		return ImmutableList.copyOf(associationDAO.findAll());
	}

}
