package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.dto.Carddetails;
import org.secondopinion.utils.AES;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;



@Repository
public class CardDetailsDAOHibernateImpl extends BaseCarddetailsDAOHibernate{


	@Override
	@Transactional()
	public void save(Carddetails cardDetails ) {
		if(Objects.isNull(cardDetails.getCarddetailsId())) {
			cardDetails.setActive('Y');
		}
		try {
			cardDetails.setCardnumber(/* AES.encrypt */(cardDetails.getCardnumber()));
		} catch (Exception e) {
			throw new RuntimeException("Unable to encrypt the card number.");
		}
		super.save(cardDetails);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Carddetails> getByPatientAndCardNumber(Long patientId, String cardnumber) {
		
		String encryptedCardNumber = null;
		try {
			encryptedCardNumber = /* AES.encrypt */ (cardnumber);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.nonNull(patientId)) {
			criterions.add(Restrictions.eq(Carddetails.FIELD_userId, patientId));
		} 
		if(StringUtil.isNullOrEmpty(cardnumber)) {
			throw new IllegalArgumentException("Field cardNumber can not be null.");
		}
		criterions.add(Restrictions.eq(Carddetails.FIELD_cardnumber, encryptedCardNumber));
		criterions.add(Restrictions.eq(Carddetails.FIELD_active, 'Y'));
		return decryptTheCarddetails(findByCrieria(criterions));
	}

	@Override
	@Transactional(readOnly=true)
	public Carddetails findCarddetailsById(Long cardId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Carddetails.FIELD_carddetailsId, cardId));
		criterions.add(Restrictions.eq(Carddetails.FIELD_active, 'Y'));
		
		List<Carddetails> carddetails=decryptTheCarddetails(findByCrieria(criterions));
		if(CollectionUtils.isEmpty(carddetails)) return null;
		return carddetails.get(0);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Carddetails> findCarddetailsByUserId(Long userId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Carddetails.FIELD_userId, userId));
		criterions.add(Restrictions.eq(Carddetails.FIELD_active, 'Y'));
		
		return decryptTheCarddetails(findByCrieria(criterions));
	}
	
	private List<Carddetails> decryptTheCarddetails(List<Carddetails> carddetailses) {
		if(CollectionUtils.isEmpty(carddetailses) ) {
			return carddetailses;
		}
		carddetailses.stream().forEach(cd -> {
			try {
				cd.setCardnumber( /* AES.decrypt */ (cd.getCardnumber()));
			} catch (Exception e) {
				
			}
		});
		return carddetailses;
	}
}