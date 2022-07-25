package org.payment.gateway;


import org.payment.gateway.stripe.StripePaymentServiceImpl;

public class StripePGConnector implements IPaymentConnector<StripePaymentServiceImpl, PaymentGatewayDTO, Object> {

	
	@Override
	public StripePaymentServiceImpl createPaymentGateway(String secretKey) {
		return new StripePaymentServiceImpl(secretKey);
	}

}
