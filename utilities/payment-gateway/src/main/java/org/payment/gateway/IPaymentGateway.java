package org.payment.gateway;


public interface IPaymentGateway<INPUT, OUTPUT> {

	public OUTPUT chargeTheCustomer(INPUT input);
	
	public OUTPUT refundToTheCustomer(INPUT input);

}
