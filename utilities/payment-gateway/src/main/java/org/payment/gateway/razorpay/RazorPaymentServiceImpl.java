/*
 * package org.payment.gateway.razorpay;
 * 
 * import java.math.BigDecimal; import java.math.RoundingMode; import
 * java.util.Date;
 * 
 * import org.json.JSONObject; import org.payment.gateway.IPaymentGateway;
 * import org.payment.gateway.PaymentGatewayDTO; import org.slf4j.Logger; import
 * org.slf4j.LoggerFactory;
 * 
 * import com.razorpay.BankTransfer; import com.razorpay.Order; import
 * com.razorpay.Payment; import com.razorpay.RazorpayClient; import
 * com.razorpay.RazorpayException;
 * 
 * public class RazorPaymentServiceImpl implements
 * IPaymentGateway<PaymentGatewayDTO, Object> { private static final Logger
 * logger = LoggerFactory.getLogger(RazorPaymentServiceImpl.class); private
 * static final String SECRET_ID = "rzp_live_Ia7ua7hM2FB54a"; private static
 * final String SECRET_KEY = "50mY9jsOK8sh562xyMcKQN3E";
 * 
 * private RazorpayClient client; private String secretKey;
 * 
 * public RazorPaymentServiceImpl() { try { this.client = new
 * RazorpayClient(SECRET_ID, SECRET_KEY); } catch (RazorpayException e) { throw
 * new IllegalArgumentException("Exception caused because of " + e.getMessage(),
 * e); } }
 * 
 * private Order createRazorPayOrder(double amount) { JSONObject options = new
 * JSONObject(); options.put("amount", convertRupeeToPaise(amount));
 * options.put("currency", "INR"); options.put("receipt", "txn_123456");
 * options.put("payment_capture", 1); Order order = null; try { order =
 * client.orders.create(options); } catch (RazorpayException e) {
 * logger.error("error occured while creating order" + e.getMessage()); } return
 * order; }
 * 
 * @Override public RazorPay chargeTheCustomer(PaymentGatewayDTO dto) { Order
 * order = createRazorPayOrder(dto.getAmountPaid()); if (order == null) { throw
 * new IllegalArgumentException("Exception occured while creating the order"); }
 * String orderId = order.get("id"); RazorPay razorPay = new RazorPay();
 * razorPay.setApplicationFee(convertRupeeToPaise(dto.getAmountPaid()));
 * razorPay.setCustomerName(dto.getCustomerName());
 * razorPay.setCustomerEmail(dto.getCutomerEmail());
 * razorPay.setMerchantName("curemetric");
 * razorPay.setPurchaseDescription("TEST PURCHASES");
 * razorPay.setRazorpayOrderId(orderId); razorPay.setSecretKey(this.secretKey);
 * razorPay.setImageURL("/logo"); razorPay.setTheme("#F37254");
 * razorPay.setNotes("notes" + orderId); return razorPay; }
 * 
 * public JSONObject capturePaymentData(CapturePaymentRequest
 * capturePaymentRequest) { JSONObject captureRequest = new JSONObject();
 * captureRequest.put("amount",
 * convertRupeeToPaise(capturePaymentRequest.getAmount()));
 * captureRequest.put("currency", capturePaymentRequest.getCurrency()); return
 * captureRequest; }
 * 
 * public Response getResponse(RazorPay razorPay, int statusCode) { Response
 * response = new Response(); response.setStatusCode(statusCode);
 * response.setRazorPay(razorPay); return response; }
 * 
 * public String convertRupeeToPaise(double paise) { BigDecimal b =
 * BigDecimal.valueOf(paise); BigDecimal value = b.multiply(new
 * BigDecimal(100d)); return value.setScale(0, RoundingMode.UP).toString(); }
 * 
 * public FetchPayments getDetails(Payment payment) { FetchPayments
 * fetchpaymentdata = new FetchPayments(); if (fetchpaymentdata.getError_code()
 * != null) { fetchpaymentdata.setError_code((String)
 * payment.get("error_code")); fetchpaymentdata.setError_description((String)
 * payment.get("error_description")); fetchpaymentdata.setError_reason((String)
 * payment.get("error_reason")); if (fetchpaymentdata.getError_source() != null)
 * { fetchpaymentdata.setError_source((String) payment.get("error_source")); }
 * if (fetchpaymentdata.getError_step() != null) {
 * fetchpaymentdata.setError_step((String) payment.get("error_step")); } } else
 * { fetchpaymentdata.setAmount((Integer) payment.get("amount"));
 * fetchpaymentdata.setAmount_refunded((Integer)
 * payment.get("amount_refunded")); if (fetchpaymentdata.getBank() != null) {
 * fetchpaymentdata.setBank((String) payment.get("bank")); }
 * fetchpaymentdata.setCard_id((String) payment.get("card_id"));
 * fetchpaymentdata.setContact((String) payment.get("contact"));
 * fetchpaymentdata.setCaptured((Boolean) payment.get("captured"));
 * fetchpaymentdata.setCreated_at((int) payment.get("created_at"));
 * fetchpaymentdata.setCurrency((String) payment.get("currency"));
 * fetchpaymentdata.setDescription((String) payment.get("description"));
 * fetchpaymentdata.setEmail((String) payment.get("email"));
 * fetchpaymentdata.setEntity((String) payment.get("entity"));
 * fetchpaymentdata.setFee((Integer) payment.get("fee"));
 * fetchpaymentdata.setId((String) payment.get("id"));
 * fetchpaymentdata.setInternational((Boolean) payment.get("international")); if
 * (fetchpaymentdata.getInvoice_id() != null) {
 * fetchpaymentdata.setInvoice_id((String) payment.get("invoice_id")); }
 * fetchpaymentdata.setMethod((String) payment.get("method")); //
 * fetchpaymentdata.setNotes((Notes) payment.get("notes"));
 * fetchpaymentdata.setOrder_id((String) payment.get("order_id")); if
 * (fetchpaymentdata.getRefund_status() != null) {
 * fetchpaymentdata.setRefund_status((String) payment.get("refund_status")); }
 * fetchpaymentdata.setStatus((String) payment.get("status"));
 * fetchpaymentdata.setTax((Integer) payment.get("tax")); if
 * (fetchpaymentdata.getVpa() != null) { fetchpaymentdata.setVpa((String)
 * payment.get("vpa")); } if (fetchpaymentdata.getWallet() != null) {
 * fetchpaymentdata.setWallet((String) payment.get("wallet")); } } return
 * fetchpaymentdata; }
 * 
 * public FetchBankTransferPayments getBankTransferDetails(BankTransfer
 * bankTransfer) { FetchBankTransferPayments fetchbankpaymentdata = new
 * FetchBankTransferPayments(); fetchbankpaymentdata.setAmount((Integer)
 * bankTransfer.get("amount")); if (fetchbankpaymentdata.getBank_reference() !=
 * null) { fetchbankpaymentdata.setBank_reference((String)
 * bankTransfer.get("bank_reference")); } if (fetchbankpaymentdata.getEntity()
 * != null) { fetchbankpaymentdata.setEntity((String)
 * bankTransfer.get("entity")); } if (fetchbankpaymentdata.getId() != null) {
 * fetchbankpaymentdata.setId((String) bankTransfer.get("id")); } if
 * (fetchbankpaymentdata.getMode() != null) {
 * fetchbankpaymentdata.setMode((String) bankTransfer.get("mode")); } if
 * (fetchbankpaymentdata.getPayer_bank_account() != null) {
 * fetchbankpaymentdata.setPayer_bank_account(bankTransfer.get(
 * "payer_bankaccount")); } if (fetchbankpaymentdata.getPayment_id() != null) {
 * fetchbankpaymentdata.setPayment_id((String) bankTransfer.get("payment_id"));
 * } if (fetchbankpaymentdata.getVirtual_account() != null) {
 * fetchbankpaymentdata.setVirtual_account(bankTransfer.get("virtual_account"));
 * } if (fetchbankpaymentdata.getVirtual_account_id() != null) {
 * fetchbankpaymentdata.setVirtual_account_id((String)
 * bankTransfer.get("virtual_account_id")); } return fetchbankpaymentdata; }
 * 
 * public CapturePayment getPaymentDetails(Payment payment) { CapturePayment
 * capturepayment = new CapturePayment(); Notes notes = new Notes(); if
 * (capturepayment.getError_code() != null) {
 * capturepayment.setError_code((String) payment.get("error_code")); if
 * (capturepayment.getError_description() != null) {
 * capturepayment.setError_description((String)
 * payment.get("error_description")); } if (capturepayment.getError_reason() !=
 * null) { capturepayment.setError_reason((String) payment.get("error_reason"));
 * } if (capturepayment.getError_source() != null) {
 * capturepayment.setError_source((String) payment.get("error_source")); } if
 * (capturepayment.getError_step() != null) {
 * capturepayment.setError_step((String) payment.get("error_step")); }
 * capturepayment.setAmount((Integer) payment.get("amount"));
 * capturepayment.setAmount_refunded((Integer) payment.get("amount_refunded"));
 * if (capturepayment.getBank() != null) { capturepayment.setBank((String)
 * payment.get("bank")); } } else { capturepayment.setCard_id((String)
 * payment.get("card_id")); capturepayment.setContact((String)
 * payment.get("contact")); capturepayment.setCaptured((Boolean)
 * payment.get("captured")); capturepayment.setCreated_at((Date)
 * payment.get("created_at")); capturepayment.setCurrency((String)
 * payment.get("currency")); capturepayment.setDescription((String)
 * payment.get("description")); capturepayment.setEmail((String)
 * payment.get("email")); capturepayment.setEntity((String)
 * payment.get("entity")); capturepayment.setFee((Integer) payment.get("fee"));
 * capturepayment.setId((String) payment.get("id"));
 * capturepayment.setInternational((Boolean) payment.get("international")); if
 * (capturepayment.getInvoice_id() != null) {
 * capturepayment.setInvoice_id((String) payment.get("invoice_id")); }
 * capturepayment.setMethod((String) payment.get("method"));
 * capturepayment.setNotes((Notes) payment.get("notes"));
 * capturepayment.setOrder_id((String) payment.get("order_id")); if
 * (capturepayment.getRefund_status() != null) {
 * capturepayment.setRefund_status((String) payment.get("refund_status")); }
 * capturepayment.setStatus((String) payment.get("status"));
 * capturepayment.setTax((Integer) payment.get("tax")); if
 * (capturepayment.getVpa() != null) { capturepayment.setVpa((String)
 * payment.get("vpa")); } if (capturepayment.getWallet() != null) {
 * capturepayment.setWallet((String) payment.get("wallet")); } } return
 * capturepayment; }
 * 
 * @Override public Object refundToTheCustomer(PaymentGatewayDTO input) { return
 * input; // // TODO Auto-generated method stub return null; }
 * 
 * } }
 */