package org.payment.gateway;

public interface IPaymentConnector<T extends IPaymentGateway<INPUT, OUTPUT>, INPUT, OUTPUT> {

	T createPaymentGateway(String secretKey);
			
	
}
