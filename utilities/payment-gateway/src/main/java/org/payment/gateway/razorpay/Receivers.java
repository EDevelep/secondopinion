package org.payment.gateway.razorpay;

import java.util.List;

public class Receivers
{
    private String id;

    private String entity;

    private String ifsc;

    private String bank_name;

    private String name;

    private List<String> notes;

    private String account_number;

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
    public void setIfsc(String ifsc){
        this.ifsc = ifsc;
    }
    public String getIfsc(){
        return this.ifsc;
    }
    public void setBank_name(String bank_name){
        this.bank_name = bank_name;
    }
    public String getBank_name(){
        return this.bank_name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setNotes(List<String> notes){
        this.notes = notes;
    }
    public List<String> getNotes(){
        return this.notes;
    }
    public void setAccount_number(String account_number){
        this.account_number = account_number;
    }
    public String getAccount_number(){
        return this.account_number;
    }
}
