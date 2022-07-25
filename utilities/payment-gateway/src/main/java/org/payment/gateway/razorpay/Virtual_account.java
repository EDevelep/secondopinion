package org.payment.gateway.razorpay;

public class Virtual_account
{
    private String id;

    private String name;

    private String entity;

    private String status;

    private String description;

    private int amount_expected;

    private Notes notes;

    private int amount_paid;

    private String customer_id;

    private Receivers receivers;

    private int close_by;

    private int closed_at;

    private int created_at;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setEntity(String entity){
        this.entity = entity;
    }
    public String getEntity(){
        return this.entity;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setAmount_expected(int amount_expected){
        this.amount_expected = amount_expected;
    }
    public int getAmount_expected(){
        return this.amount_expected;
    }
    public void setNotes(Notes notes){
        this.notes = notes;
    }
    public Notes getNotes(){
        return this.notes;
    }
    public void setAmount_paid(int amount_paid){
        this.amount_paid = amount_paid;
    }
    public int getAmount_paid(){
        return this.amount_paid;
    }
    public void setCustomer_id(String customer_id){
        this.customer_id = customer_id;
    }
    public String getCustomer_id(){
        return this.customer_id;
    }
    public void setReceivers(Receivers receivers){
        this.receivers = receivers;
    }
    public Receivers getReceivers(){
        return this.receivers;
    }
    public void setClose_by(int close_by){
        this.close_by = close_by;
    }
    public int getClose_by(){
        return this.close_by;
    }
    public void setClosed_at(int closed_at){
        this.closed_at = closed_at;
    }
    public int getClosed_at(){
        return this.closed_at;
    }
    public void setCreated_at(int created_at){
        this.created_at = created_at;
    }
    public int getCreated_at(){
        return this.created_at;
    }
}
