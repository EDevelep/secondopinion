/*
 * package org.secondopinion.dao;
 * 
 * import java.util.HashSet; import java.util.Set;
 * 
 * import org.junit.Test;
 * 
 * import org.secondopinion.patient.dao.impl.hibernate.UserDAOHibernateImpl;
 * import org.secondopinion.patient.dto.ACCESS_TYPE; import
 * org.secondopnion.patient.PatientApplicationTest; import
 * org.springframework.beans.factory.annotation.Autowired;
 * 
 * public class UserRegDaoTest extends PatientApplicationTest {
 * 
 * @Autowired private UserDAOHibernateImpl userDAOHibernateImpl;
 * 
 * @Test public void test_hasUserAccessToDetails() { Long userId = 23L; Long
 * forUserId = 34L; ACCESS_TYPE accessType = ACCESS_TYPE.PERSONAL_DETAILS;
 * userDAOHibernateImpl.hasUserAccessToDetails(userId, forUserId, accessType); }
 * 
 * @Test public void test_getUserNameByUserIds() { Set<Long> set = new
 * HashSet<Long>(); set.add(23L);
 * userDAOHibernateImpl.getUserNameByUserIds(set); }
 * 
 * @Test public void test_getByIdNotAndUsernameEquals() { Long userId = 45L;
 * String emailId = "jatin@gmail.com";
 * userDAOHibernateImpl.getByIdNotAndUsernameEquals(userId, emailId); }
 * 
 * @Test public void testupdateLastLoginTime() { Long userId = 45L;
 * userDAOHibernateImpl.updateLastLoginTime(userId); } }
 */