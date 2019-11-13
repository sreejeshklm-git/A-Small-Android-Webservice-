package com.sree.lelab.smallwebservice.models;

import com.google.gson.annotations.SerializedName;
//class for company details
public class Company{
    @SerializedName("name")
    private String cname;
    @SerializedName("catchPhrase")
    private String cCatchPhrase;
    @SerializedName("bs")
    private String cBs;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getcCatchPhrase() {
        return cCatchPhrase;
    }

    public void setcCatchPhrase(String cCatchPhrase) {
        this.cCatchPhrase = cCatchPhrase;
    }

    public String getcBs() {
        return cBs;
    }

    public void setcBs(String cBs) {
        this.cBs = cBs;
    }
}
