package org.secondopinion.patient.dao.impl.hibernate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.secondopinion.patient.dao.AddressDAO;
import org.secondopinion.patient.dao.PersonaldetailDAO;
import org.secondopinion.patient.dao.RelationshipDAO;
import org.secondopinion.patient.domain.BaseUser;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.dao.hibernate.DaoUtil;
import org.secondopinion.patient.dto.ACCESS_TYPE;
import org.secondopinion.patient.dto.DoctorDashBoardDTO;
import org.secondopinion.patient.dto.PrescriptionDTO;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.UserDTO;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Component
public class UserDAOHibernateImpl extends BaseUserDAOHibernate {

	@Autowired
	private AddressDAO addressDAO;

	@Autowired
	private PersonaldetailDAO personaldetailDAO;

	@Autowired
	private RelationshipDAO relationshipDAO;

	private static final String USER_RELATION_DETAILS = "select a.firstName, a.lastName, a.userId, b.verificationId, b.relationship, b.approvedOn from user a, relationship b"
			+ " where a.userId = b.userId and a.userId in (:USER_IDS)";

	private static final String USER_DETAILS = "select u.userId,u.userName,u.firstName,u.lastName,u.middleName,u.emailId,u.password,u.cellNumber,u.officeNumber,u.homeNumber,u.primaryContact,u.emergencycontact,u.active,u.retry,u.locked,u.activatedDate,u.operatedByUser,u.lastLogin,u.nativelanguage,u.preferredlanguages from user u where u.userId in (:USER_IDS);";

	private static final String FETCH_EMAIL_ID_QUERY = "select " + BaseUser.FIELD_emailId + " from user where "
			+ BaseUser.FIELD_userId + " =  :" + BaseUser.FIELD_userId;

	@Override
	public void save(User user) throws DataAccessException {
		if (Objects.isNull(user.getUserId())) {
			user.setActive('N');
			user.setLocked('N');
			user.setRetry(0);
		}
		super.save(user);

		if (!CollectionUtils.isEmpty(user.getAddress())) {
			user.getAddress().stream().forEach(n -> {
				if (Objects.isNull(n.getUserId())) {
					n.setUserId(user.getUserId());
				}
			});

			getAddressDAO().save(user.getAddress());
		}

	}

	@Override
	public List<Object[]> getUsersForWithRelationship(List<Long> userIds) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("USER_IDS", userIds);

		return findBySqlQuery(USER_RELATION_DETAILS, params, null);
	}

	@Override
	@Transactional
	public List<UserDTO> getUsers(List<Long> userIds) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("USER_IDS", userIds);
		List<UserDTO> user = findBySqlQuery(USER_DETAILS, params, UserDTO.class);
		return user;
	}

	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	public PersonaldetailDAO getPersonaldetailDAO() {
		return personaldetailDAO;
	}

	public void setPersonaldetailDAO(PersonaldetailDAO personaldetailDAO) {
		this.personaldetailDAO = personaldetailDAO;
	}

	@Override
	@Transactional
	public boolean hasUserAccessToDetails(Long userId, Long forUserId, ACCESS_TYPE accessType,
			RELATIONSHIP_TYPE relationship_TYPE) {

		Relationship relationship = relationshipDAO.checkRelationshipExists(userId, forUserId, relationship_TYPE);

		if (relationship != null) {

			// TODO: Below if condition should be removed.
			RELATIONSHIP_TYPE relationshipType = RELATIONSHIP_TYPE.valueOf(relationship.getRelationship());

			if (relationshipType == RELATIONSHIP_TYPE.DOCTOR || relationshipType == RELATIONSHIP_TYPE.PARENT
					|| relationshipType == RELATIONSHIP_TYPE.SELF || relationshipType == RELATIONSHIP_TYPE.CHILD
					|| relationshipType == RELATIONSHIP_TYPE.NUTRITIONIST
					|| relationshipType == RELATIONSHIP_TYPE.FRIEND || relationshipType == RELATIONSHIP_TYPE.PARENT
					|| relationshipType == RELATIONSHIP_TYPE.CHILD || relationshipType == RELATIONSHIP_TYPE.RELATIVE) {
				return true;
			}

			return ACCESS_TYPE.hasAccess(accessType, relationship);
		}
		return false;
	}

	private static final String updateLastLoginUserSql = "update User set " + User.FIELD_lastLogin + "=:"
			+ User.FIELD_lastLogin + ",  " + User.FIELD_retry + "=:" + User.FIELD_retry + ",  " + User.FIELD_locked
			+ "=:" + User.FIELD_locked + " where " + User.FIELD_userId + "= :" + User.FIELD_userId;
	private static final String updateRetryUserSql = "update User set " + User.FIELD_retry + "=:" + User.FIELD_retry
			+ ",  " + User.FIELD_locked + "=:" + User.FIELD_locked + " where " + User.FIELD_userId + "= :"
			+ User.FIELD_userId;

	private static final String dashBoardQuery =

			"select userId, patientName, max(appointmentId) as patientAppointmentId, max(referenceAppointmentId) as doctorAppointmentId, "
			+ "max(appointmentDate) as latestAppointmentDate, max(prescriptionId) lastestPrescriptionId\r\n"
					+ "from appointment\r\n"
					+ "where referenceEntityId = :doctor_Id and appointmentFor = :appointment_For \r\n"
					+ "group by userId, patientName;";

	@Override
	@Transactional
	public void updateLastLoginTime(Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put(User.FIELD_lastLogin, new Date());
		params.put(User.FIELD_userId, userId);
		params.put(User.FIELD_locked, 'N');
		params.put(User.FIELD_retry, 0);
		executeQuery(updateLastLoginUserSql, params);

	}

	@Override
	public boolean getByIdNotAndUsernameEquals(Long userId, String emailId) {
		Criterion userNameCriterion = Restrictions.eq(User.FIELD_emailId, emailId);
		Criterion activeCriterion = Restrictions.ne(User.FIELD_userId, userId);
		List<User> user = findByCrieria(Restrictions.and(userNameCriterion, activeCriterion));
		return user.size() > 0;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Long, String> getUserNameByUserIds(Set<Long> userIds) {

		Map<Long, String> userNameAndClientNameMap = new HashMap<>();
		String inQuery = DaoUtil.generateInQuery(userIds, User.FIELD_userId, false);
		String usernameInQuery = "select u." + User.FIELD_userId + ", u." + User.FIELD_userName + " from user u where "
				+ inQuery;

		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(User.FIELD_userId, StandardBasicTypes.LONG);
		scalarMapping.put(User.FIELD_userName, StandardBasicTypes.STRING);

		List<Map<String, Object>> objects = querySqlMapTransformer(usernameInQuery, null, null, null, scalarMapping);
		for (Iterator<Map<String, Object>> iterator = objects.iterator(); iterator.hasNext();) {
			Map<String, Object> userNameMap = iterator.next();
			Long userId = (Long) userNameMap.get(User.FIELD_userId);
			userNameAndClientNameMap.put(userId, (String) userNameMap.get(User.FIELD_userName));
		}
		return userNameAndClientNameMap;
	}

	@Override
	@Transactional(readOnly = true)
	public String geUsersEmailId(Long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put(BaseUser.FIELD_userId, userId);

		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(BaseUser.FIELD_emailId, StandardBasicTypes.STRING);

		String emailId = null;

		List<Map<String, Object>> objects = findBySqlQueryMapTransformer(FETCH_EMAIL_ID_QUERY, params, scalarMapping);
		for (Iterator<Map<String, Object>> iterator = objects.iterator(); iterator.hasNext();) {
			Map<String, Object> userEmailidMap = iterator.next();
			emailId = (String) userEmailidMap.get(BaseUser.FIELD_emailId);
		}

		return emailId;
	}

	@Override
	@Transactional
	public User findUserByUserId(Long userId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(User.FIELD_userId, userId));
		criterions.add(Restrictions.eq(User.FIELD_active, 'Y'));
		List<User> user = findByCrieria(criterions);
		if (CollectionUtils.isEmpty(user))
			return null;
		return user.get(0);

	}

	@Override
	public void updateRetryCountIfLoginFailed(Long userId, Integer retry) {
		Map<String, Object> params = new HashMap<>();

		params.put(User.FIELD_userId, userId);
		params.put(User.FIELD_locked, 'N');

		retry = retry == null ? 1 : retry + 1;
		if (retry >= 4) {
			params.put(User.FIELD_locked, 'Y');
		}
		params.put(User.FIELD_retry, retry);

		executeQuery(updateRetryUserSql, params);

	}

	@Override
	@Transactional
	public Response<List<User>> getAssociatedUsers(Long userId, Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(User.FIELD_userId, userId));
		return findByCrieria(criterions, null, pageNum, maxResults);
	}

	@Override
	@Transactional
	public User findUserByUserNameAndEmailId(String email) {

		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.or(Restrictions.eq(BaseUser.FIELD_userName, email),
				Restrictions.eqOrIsNull(BaseUser.FIELD_emailId, email)));
		criterions.add(Restrictions.eq(BaseUser.FIELD_dependent, 'N'));

		List<User> users = findByCrieria(criterions);

		if (users != null & users.size() > 0) {
			return users.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public User findUserByUserNameAndEmailIdDependent(String email) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.or(Restrictions.eq(BaseUser.FIELD_userName, email),
				Restrictions.eqOrIsNull(BaseUser.FIELD_emailId, email)));
		criterions.add(Restrictions.eq(BaseUser.FIELD_dependent, 'Y'));

		List<User> users = findByCrieria(criterions);

		if (users != null & users.size() > 0) {
			return users.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public User emailVerificationUserByUserNameAndEmailId(String emailid, String userName) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.or(Restrictions.eq(BaseUser.FIELD_userName, emailid),
				Restrictions.eqOrIsNull(BaseUser.FIELD_emailId, emailid)));
		criterions.add(Restrictions.eq(BaseUser.FIELD_dependent, 'N'));
		criterions.add(Restrictions.eq(BaseUser.FIELD_userName, userName));

		List<User> users = findByCrieria(criterions);

		if (users != null & users.size() > 0) {
			return users.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public User emailVerificationUserByUserNameAndEmailIds(String emailid, String userName) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.or(Restrictions.eq(BaseUser.FIELD_userName, emailid),
				Restrictions.eqOrIsNull(BaseUser.FIELD_emailId, emailid)));
		criterions.add(Restrictions.eq(BaseUser.FIELD_dependent, 'Y'));
		criterions.add(Restrictions.eq(BaseUser.FIELD_userName, userName));

		List<User> users = findByCrieria(criterions);

		if (users != null & users.size() > 0) {
			return users.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public User findUserByUsercellNumber(String cellNumber, String userName) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseUser.FIELD_dependent, 'N'));
		criterions.add(Restrictions.eq(BaseUser.FIELD_userName, userName));
		criterions.add(Restrictions.eq(BaseUser.FIELD_cellNumber, cellNumber));
		List<User> users = findByCrieria(criterions);

		if (users != null & users.size() > 0) {
			return users.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public User findUserByUsercellNumbers(String cellNumber, String userName) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseUser.FIELD_dependent, 'Y'));
		criterions.add(Restrictions.eq(BaseUser.FIELD_userName, userName));
		criterions.add(Restrictions.eq(BaseUser.FIELD_cellNumber, cellNumber));
		List<User> users = findByCrieria(criterions);

		if (users != null & users.size() > 0) {
			return users.get(0);
		}

		return null;
	}

	@Override
	public User findUserByUserIds(List<Long> userIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Collection<DoctorDashBoardDTO> getDoctorDashBoardForDoctorIdAndAppointmentFor(Long doctorId,
			String appointmentFor) {
		Map<String, Object> params = new HashMap<>();

		params.put("doctor_Id", doctorId);
		params.put("appointment_For", appointmentFor);

		//List<DoctorDashBoardDTO> doctorDashBoardDTO = findBySqlQuery(dashBoardQuery, params, DoctorDashBoardDTO.class);
		
		List<Map<String, Object>> medicationData = findBySqlQuery(dashBoardQuery, params);
		Map<BigInteger, DoctorDashBoardDTO> medicationUsageData = new HashMap<BigInteger, DoctorDashBoardDTO>();
		medicationData.stream().forEach(n -> {
			BigInteger userId = (BigInteger) n.get("userId");
			DoctorDashBoardDTO prescriptionDTO = medicationUsageData.get(userId);

			if (prescriptionDTO == null) {
				prescriptionDTO = new DoctorDashBoardDTO(userId, n);
				medicationUsageData.put(userId, prescriptionDTO);
			}

		});

		return medicationUsageData.values();

		
	}

}