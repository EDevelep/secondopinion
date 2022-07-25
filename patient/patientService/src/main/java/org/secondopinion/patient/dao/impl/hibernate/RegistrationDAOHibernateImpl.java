package  org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.secondopinion.common.dto.SortDTO.SortDirection;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.dao.UserDAO;
import org.secondopinion.patient.dto.PatientFlagsRequest.PatientFlag;
import org.secondopinion.patient.dto.Registration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.dto.User;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class RegistrationDAOHibernateImpl extends BaseRegistrationDAOHibernate{

	@Autowired
	private UserDAO userDAO;

	
	

	@Override
	@Transactional
	public void save(  Registration registration) throws DataAccessException {
		if(Objects.isNull(registration.getRegistrationId())) {
			registration.setActive('N');
		}
		
		super.save(registration);
	}
	
	
	
	@Override
	@Transactional(readOnly=true)
	public List<User> fetchDoctorsByFlagWithPagination(PatientFlag patientFlag, Map<String, SortDirection> sortMap,
			Integer pageNo, Integer pageSize) {
		Criterion criterion = Restrictions.eq(patientFlag.getColumnName(), patientFlag.getCharYorN()); 

		
		List<Registration> registrations = new ArrayList<>();
		if(Objects.isNull(pageNo ) || pageNo == 0 || Objects.isNull(pageSize ) || pageSize == 0) {
			registrations = findByCrieria(criterion);
		} else {
			List<Order> orders = new ArrayList<>();
			if(sortMap != null) {
				orders = sortMap.entrySet().stream().map(entry -> {
					Order order = null;
					if(entry.getValue().getIsAsc()) {
						order = Order.asc(entry.getKey());
					} else {
						order = Order.desc(entry.getKey());
					}
					return order;
				}).collect(Collectors.toList());
			}
			Response<List<Registration>> registrationMap = findByCrieria(Arrays.asList(criterion), orders, pageNo,pageSize) ;
			registrations = registrationMap.getData();
		}

		if(CollectionUtils.isEmpty(registrations )) {
			return new ArrayList<>();
		}
		if(PatientFlag.VERIFIED.equals(patientFlag)) {
			registrations = registrations.stream().filter(rgtn -> rgtn.hasUserVerified()).collect(Collectors.toList());
		}
		return registrations.stream().map(rgtn -> userDAO.findById(rgtn.getUserId())).collect(Collectors.toList());
	}
}