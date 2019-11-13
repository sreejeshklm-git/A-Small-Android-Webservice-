package com.sree.lelab.smallwebservice.models;

import com.google.gson.annotations.SerializedName;
//main class for all json values
public class RetrofitGetResponse  {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("username")
    private String userName;
    @SerializedName("email")
    private String uEmail;
    @SerializedName("address")
    Address addressDtl ;
    @SerializedName("phone")
    private String uPhoneNo;
    @SerializedName("website")
    private String uWebsite;
    @SerializedName("company")
    Company companyDtl;


    public Address getAddressDtl() {
        return addressDtl;
    }
    public void setAddressDtl(Address addressDtl) {
        this.addressDtl = addressDtl;
    }
    public Company getCompanyDtl() {
        return companyDtl;
    }
    public void setCompanyDtl(Company companyDtl) {
        this.companyDtl = companyDtl;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getuEmail() {
        return uEmail;
    }
    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }
    public String getuPhoneNo() {
        return uPhoneNo;
    }
    public void setuPhoneNo(String uPhoneNo) {
        this.uPhoneNo = uPhoneNo;
    }
    public String getuWebsite() {
        return uWebsite;
    }
    public void setuWebsite(String uWebsite) {
        this.uWebsite = uWebsite;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }

}

