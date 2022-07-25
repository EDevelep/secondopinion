
package org.secondopinion.patient.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;

import org.payment.gateway.razorpay.RazorPay;
import org.secondopinion.patient.dto.AppointmentBookingDTO;
import org.secondopinion.patient.dto.AppointmentDTO;
import org.secondopinion.patient.dto.FetchPayment;
import org.secondopinion.patient.dto.PaymentGatewayDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;

@Service
public class RazorPaymentServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(RazorPaymentServiceImpl.class);

	private RazorpayClient client;

	private static final String SECRET_ID = "rzp_live_Ia7ua7hM2FB54a";
	private static final String SECRET_KEY = "50mY9jsOK8sh562xyMcKQN3E";
	
	
	//private static final String SECRET_ID = "rzp_test_EII6ZFbUo3uVl5";
	//private static final String SECRET_KEY = "OZSQoAwdbAnG5l9PsXC06nFp";
	//rzp_test_EII6ZFbUo3uVl5

	public RazorPaymentServiceImpl() throws RazorpayException {
		this.client = new RazorpayClient(SECRET_ID, SECRET_KEY);
	}

	public FetchPayment fetchPaymentsByUsingPaymentId(AppointmentBookingDTO appointment)

	{
		FetchPayment fetchPayment = null;
		try {
			Payment payment = client.Payments.fetch(appointment.getPaymentId());
			fetchPayment = getDetails(payment);
		} catch (RazorpayException e) {
			logger.error("Exceptin In Payment Get Way", e.getMessage());
		}
		return fetchPayment;
	}

	public FetchPayment fetchPaymentsByUsingPaymentId(String paymentId)

	{
		FetchPayment fetchPayment = null;
		try {
			Payment payment = client.Payments.fetch(paymentId);
			fetchPayment = getDetails(payment);
		} catch (RazorpayException e) {
			logger.error("Exceptin In Payment Get Way", e.getMessage());
		}
		return fetchPayment;
	}

	public RazorPay chargeTheCustomer(PaymentGatewayDTO dto) {
		Order order = createRazorPayOrder(dto.getAmountPaid());
		if (order == null) {
			throw new IllegalArgumentException("Exception occured while creating the order");
		}
		String orderId = order.get("id");
		RazorPay razorPay = new RazorPay();
		razorPay.setApplicationFee(convertRupeeToPaise(dto.getAmountPaid()));
		razorPay.setCustomerName(dto.getCustomerName());
		razorPay.setCustomerEmail(dto.getCutomerEmail());
		razorPay.setMerchantName("curemetric");
		razorPay.setPurchaseDescription(dto.getDescription());
		razorPay.setRazorpayOrderId(orderId);
		razorPay.setSecretKey(this.SECRET_KEY);
		razorPay.setImageURL("/logo");
		razorPay.setTheme("#F37254");
		razorPay.setNotes("notes" + orderId);
		return razorPay;
	}

	private Order createRazorPayOrder(Double double1) {
		JSONObject options = new JSONObject();
		options.put("amount", convertRupeeToPaise(double1));
		options.put("currency", "INR");
		Order order = null;
		try {
			order = client.Orders.create(options);
		} catch (RazorpayException e) {
			logger.error("error occured while creating order" + e.getMessage());
		}
		return order;
	}

	public String convertRupeeToPaise(double paise) {
		BigDecimal b = BigDecimal.valueOf(paise);
		BigDecimal value = b.multiply(new BigDecimal(100d));
		return value.setScale(0, RoundingMode.UP).toString();
	}

	public FetchPayment getDetails(Payment payment) {
		FetchPayment fetchpaymentdata = new FetchPayment();
		fetchpaymentdata.setAmount((Integer) payment.get("amount"));
		fetchpaymentdata.setAmount_refunded((Integer) payment.get("amount_refunded"));
		// fetchpaymentdata.setCard_id((String) payment.get("card_id"));
		fetchpaymentdata.setContact((String) payment.get("contact"));
		fetchpaymentdata.setCaptured((Boolean) payment.get("captured"));
		fetchpaymentdata.setCreated_at((Date) payment.get("created_at"));
		fetchpaymentdata.setCurrency((String) payment.get("currency"));
		//fetchpaymentdata.setDescription((String) payment.get("description"));
		fetchpaymentdata.setEmail((String) payment.get("email"));
		fetchpaymentdata.setEntity((String) payment.get("entity"));
		// fetchpaymentdata.setFee((Integer) payment.get("fee"));
		fetchpaymentdata.setId((String) payment.get("id"));
		fetchpaymentdata.setInternational((Boolean) payment.get("international"));

		fetchpaymentdata.setMethod((String) payment.get("method")); //

		// fetchpaymentdata.setOrder_id((String) payment.get("order_id"));

		fetchpaymentdata.setStatus((String) payment.get("status"));
		// fetchpaymentdata.setTax((Integer) payment.get("tax"));
		if (fetchpaymentdata.getVpa() != null) {
			fetchpaymentdata.setVpa((String) payment.get("vpa"));
		}
		if (fetchpaymentdata.getWallet() != null) {
			fetchpaymentdata.setWallet((String) payment.get("wallet"));
		}

		return fetchpaymentdata;
	}

	public FetchPayment fetchPaymentsByUsingPaymentId(AppointmentDTO appointmentdto) {
		FetchPayment fetchPayment = null;
		try {
			Payment payment = client.Payments.fetch(appointmentdto.getPaymentId());
			fetchPayment = getDetails(payment);
		} catch (RazorpayException e) {
			logger.error("Exceptin In Payment Get Way", e.getMessage());
		}
		return fetchPayment;
	}

	public Refund refundToThePatient(String transactionId, String reason,double amount) {
		Refund refund=null;
		JSONObject refundRequest = new JSONObject();
		refundRequest.put("amount",amount);
		refundRequest.put("speed","normal");	
		refundRequest.put("receipt","Receipt No." +UUID.randomUUID().toString());
		              
		try {
			refund = client.Payments.refund(transactionId,refundRequest);
		} catch (RazorpayException e) {
			throw new IllegalArgumentException("Exception occured while Refund ");
			
		}
		return refund;
	}


}
