package org.payment.gateway;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentGatewayConnectorService {

	private PaymentGatewayConnectorService() {
		
	}
	
	public static final PaymentGatewayConnectorService INSTANCE = new  PaymentGatewayConnectorService();
	
	@SuppressWarnings("rawtypes")
	Map<String, IPaymentGateway> pgConnectorMap = new HashMap<>();

	@SuppressWarnings("unchecked")
	public <INPUT, OUTPUT> OUTPUT chargeTheCustomer(INPUT input) {
		
		PaymentGatewayDTO dto = (PaymentGatewayDTO) input;
		
		String type = dto.getType();
		String secretKey = dto.getSecretKey();
		validateRequiredFields(dto);
		IPaymentGateway<INPUT, OUTPUT> paymentGateway;

		if (!pgConnectorMap.containsKey(type)) {
			paymentGateway = PaymentGatewayConnectorEnum.pgConnection(type, secretKey);
			pgConnectorMap.put(type, paymentGateway);
		} else {
			paymentGateway = pgConnectorMap.get(type);
		}

		return paymentGateway.chargeTheCustomer(input);
	}

	private void validateRequiredFields(PaymentGatewayDTO dto) {
		String type = dto.getType();
		String secretKey = dto.getSecretKey();

		if(type == null || type.isEmpty()) {
			throw new IllegalArgumentException("type can not be null");
		}
		List<String> pgTypes = Arrays.stream(PaymentGatewayConnectorEnum.values()).map(pge -> pge.name()).collect(Collectors.toList());
		if(!pgTypes.contains(type)) {
			throw new IllegalArgumentException("the type should be in " + pgTypes);
		}
		if(secretKey == null || secretKey.isEmpty()) {
			throw new IllegalArgumentException("");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public <INPUT, OUTPUT> OUTPUT refundToTheCustomer(INPUT input) {
		PaymentGatewayDTO dto = (PaymentGatewayDTO) input;
		String type = dto.getType();
		String secretKey = dto.getSecretKey();
		validateRequiredFields(dto);
		IPaymentGateway<INPUT, OUTPUT> paymentGateway;

		if (!pgConnectorMap.containsKey(type)) {
			paymentGateway = PaymentGatewayConnectorEnum.pgConnection(type, secretKey);
			pgConnectorMap.put(type, paymentGateway);
		} else {
			paymentGateway = pgConnectorMap.get(type);
		}

		return paymentGateway.refundToTheCustomer(input);
	}
	
	
	
}
