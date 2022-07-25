package org.payment.gateway.razorpay;

public class FetchBankTransferPayments
{
    private String id;

    private String entity;

    private String payment_id;

    private String mode;

    private String bank_reference;

    private int amount;

    private Payer_bank_account payer_bank_account;

    private String virtual_account_id;

    private Virtual_account virtual_account;

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
    public void setPayment_id(String payment_id){
        this.payment_id = payment_id;
    }
    public String getPayment_id(){
        return this.payment_id;
    }
    public void setMode(String mode){
        this.mode = mode;
    }
    public String getMode(){
        return this.mode;
    }
    public void setBank_reference(String bank_reference){
        this.bank_reference = bank_reference;
    }
    public String getBank_reference(){
        return this.bank_reference;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public int getAmount(){
        return this.amount;
    }
    public void setPayer_bank_account(Payer_bank_account payer_bank_account){
        this.payer_bank_account = payer_bank_account;
    }
    public Payer_bank_account getPayer_bank_account(){
        return this.payer_bank_account;
    }
    public void setVirtual_account_id(String virtual_account_id){
        this.virtual_account_id = virtual_account_id;
    }
    public String getVirtual_account_id(){
        return this.virtual_account_id;
    }
    public void setVirtual_account(Virtual_account virtual_account){
        this.virtual_account = virtual_account;
    }
    public Virtual_account getVirtual_account(){
        return this.virtual_account;
    }
}
