package org.payment.gateway;

public enum PaymentGatewayConnectorEnum {

	STRIPE(new StripePGConnector()); //RAZOR_PAY(new RazorPayPGConnector());
	
	@SuppressWarnings("rawtypes")
	private IPaymentConnector paymentConnector;
	
	@SuppressWarnings("rawtypes")
	private PaymentGatewayConnectorEnum(IPaymentConnector paymentConnector) {
		this.paymentConnector = paymentConnector;
	}
	
	@SuppressWarnings("rawtypes")
	public static IPaymentGateway pgConnection(String type, String secretKey) {
		PaymentGatewayConnectorEnum connector = PaymentGatewayConnectorEnum.valueOf(type);
		return connector.paymentConnector.createPaymentGateway(secretKey);
	}
}
