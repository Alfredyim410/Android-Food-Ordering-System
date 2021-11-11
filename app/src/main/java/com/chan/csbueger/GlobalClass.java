package com.chan.csbueger;

import android.app.Application;

public class GlobalClass extends Application {

    public String MemberID ;
    public String FullName ;
    public String EmailAdd ;
    public String MobileNo ;
    public String MemberType ;

    public String getMemberID()
    {
        return MemberID;
    }

    public void setMemberID (String str)
    {
        MemberID= str;
    }

    public String getFullName()
    {
        return FullName;
    }

    public void setFullName (String str)
    {
        FullName= str;
    }

    public String getEmailAdd()
    {
        return EmailAdd;
    }

    public void setEmailAdd (String str)
    {
        EmailAdd= str;
    }

    public String getMobileNo()
    {
        return MobileNo;
    }

    public void setMobileNo (String str)
    {
        MobileNo= str;
    }

    public String getMemberType()
    {
        return MemberType;
    }

    public void setMemberType(String str)
    {
        MemberType= str;
    }

}
