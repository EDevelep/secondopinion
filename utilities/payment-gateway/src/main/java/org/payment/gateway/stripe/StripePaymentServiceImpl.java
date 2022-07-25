package org.payment.gateway.stripe;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.payment.gateway.IPaymentGateway;
import org.payment.gateway.PaymentGatewayDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Refund;
import com.stripe.model.Token;

public class StripePaymentServiceImpl implements IPaymentGateway<PaymentGatewayDTO, Object>{
private static final Logger logger = LoggerFactory.getLogger(StripePaymentServiceImpl.class);

	
	public StripePaymentServiceImpl(String secretKey) {
		Stripe.apiKey = secretKey;
	}

	private Customer createCustomer(String email, String description) throws StripeException {
		Map<String, Object> customerParams = new HashMap<>();
		customerParams.put("email", email);
		customerParams.put("description", description);
		return Customer.create(customerParams);
	}

	private Customer getCustomer(String id) throws StripeException {
		return Customer.retrieve(id);
	}

	public Charge chargeTheCustomer(PaymentGatewayDTO dto) {
		
		Charge charge = null;
		try {
			Token token = createToken(dto.getCardnumber(), dto.getExpmonth(), dto.getExpyear(), dto.getCvv());
			Customer customer = createCustomer(dto.getCutomerEmail(), dto.getDescription());
			Map<String, Object> chargeParams = new HashMap<>();
			chargeParams.put("amount", BigDecimal.valueOf(dto.getAmountPaid()).multiply(new BigDecimal(100)).longValue());
			chargeParams.put("currency", dto.getCurrencyType());
			chargeParams.put("description", dto.getDescription());
			chargeParams.put("source", token.getId());
			
			charge = Charge.create(chargeParams);
			charge.setCustomerObject(customer);
			logger.info("payment for customer : {}", customer.getId());
		} catch (StripeException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		return charge;
	}

	private Token createToken(String cardnumber, Long expmonth, Long expyear, String cvv) throws StripeException {
		Map<String, Object> tokenParams = new HashMap<>();
		Map<String, Object> cardParams = new HashMap<>();
		

		cardParams.put("number", cardnumber);
		cardParams.put("exp_month", expmonth);
		cardParams.put("exp_year", expyear);
		cardParams.put("cvc", cvv);
		tokenParams.put("card", cardParams);
		return Token.create(tokenParams);
	}

	public Token getToken(String tokenId) throws StripeException {
		return Token.retrieve(tokenId);
	}

	/**
	 * This method used to refund the account
	 * 
	 * @param chargeCardId
	 * @param amount
	 * @param reason
	 * @return
	 * @throws StripeException
	 */
	public Refund refundToTheCustomer(PaymentGatewayDTO dto) {
		Map<String, Object> refundParams = new HashMap<>();
		refundParams.put("charge", dto.getPaymentReferenceId());
		if (Objects.nonNull(dto.getRefundAmount())) {
			refundParams.put("amount", dto.getRefundAmount());
		}
		if (dto.getRefundReason() != null && !dto.getRefundReason().isEmpty()) {
			refundParams.put("reason", dto.getRefundReason());
		}
		try {
			return Refund.create(refundParams);
		} catch (StripeException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}

}
