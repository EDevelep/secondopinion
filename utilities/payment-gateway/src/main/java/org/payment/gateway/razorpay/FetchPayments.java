package org.payment.gateway.razorpay;

public class FetchPayments
{
    private String id;

    private String entity;

    private int amount;

    private String currency;

    private String status;

    private String order_id;

    private String invoice_id;

    private boolean international;

    private String method;

    private int amount_refunded;

    private String refund_status;

    private boolean captured;

    private String description;

    private String card_id;

    private String bank;

    private String wallet;

    private String vpa;

    private String email;

    private String contact;

    private Notes notes;

    private int fee;

    private int tax;

    private String error_code;

    private String error_description;

    private String error_source;

    private String error_step;

    private String error_reason;

    private int created_at;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setEntity(String entity){
        this.entity = entity;
    }
    public String getEntity(){
        return this.entity;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public int getAmount(){
        return this.amount;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    public String getCurrency(){
        return this.currency;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setOrder_id(String order_id){
        this.order_id = order_id;
    }
    public String getOrder_id(){
        return this.order_id;
    }
    public void setInvoice_id(String invoice_id){
        this.invoice_id = invoice_id;
    }
    public String getInvoice_id(){
        return this.invoice_id;
    }
    public void setInternational(boolean international){
        this.international = international;
    }
    public boolean getInternational(){
        return this.international;
    }
    public void setMethod(String method){
        this.method = method;
    }
    public String getMethod(){
        return this.method;
    }
    public void setAmount_refunded(int amount_refunded){
        this.amount_refunded = amount_refunded;
    }
    public int getAmount_refunded(){
        return this.amount_refunded;
    }
    public void setRefund_status(String refund_status){
        this.refund_status = refund_status;
    }
    public String getRefund_status(){
        return this.refund_status;
    }
    public void setCaptured(boolean captured){
        this.captured = captured;
    }
    public boolean getCaptured(){
        return this.captured;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setCard_id(String card_id){
        this.card_id = card_id;
    }
    public String getCard_id(){
        return this.card_id;
    }
    public void setBank(String bank){
        this.bank = bank;
    }
    public String getBank(){
        return this.bank;
    }
    public void setWallet(String wallet){
        this.wallet = wallet;
    }
    public String getWallet(){
        return this.wallet;
    }
    public void setVpa(String vpa){
        this.vpa = vpa;
    }
    public String getVpa(){
        return this.vpa;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setContact(String contact){
        this.contact = contact;
    }
    public String getContact(){
        return this.contact;
    }
    public void setNotes(Notes notes){
        this.notes = notes;
    }
    public Notes getNotes(){
        return this.notes;
    }
    public void setFee(int fee){
        this.fee = fee;
    }
    public int getFee(){
        return this.fee;
    }
    public void setTax(int tax){
        this.tax = tax;
    }
    public int getTax(){
        return this.tax;
    }
    public void setError_code(String error_code){
        this.error_code = error_code;
    }
    public String getError_code(){
        return this.error_code;
    }
    public void setError_description(String error_description){
        this.error_description = error_description;
    }
    public String getError_description(){
        return this.error_description;
    }
    public void setError_source(String error_source){
        this.error_source = error_source;
    }
    public String getError_source(){
        return this.error_source;
    }
    public void setError_step(String error_step){
        this.error_step = error_step;
    }
    public String getError_step(){
        return this.error_step;
    }
    public void setError_reason(String error_reason){
        this.error_reason = error_reason;
    }
    public String getError_reason(){
        return this.error_reason;
    }
    public void setCreated_at(int i){
        this.created_at = i;
    }
    public int getCreated_at(){
        return this.created_at;
    }
}

