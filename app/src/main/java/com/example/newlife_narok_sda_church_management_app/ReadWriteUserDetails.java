package com.example.newlife_narok_sda_church_management_app;

public class ReadWriteUserDetails {
    public  String doB, gender, mobile;

    // Constructor
    public ReadWriteUserDetails(){};

    public ReadWriteUserDetails(String textDoB, String textGender, String textMobile){
        this.doB = textDoB;
        this.gender = textGender;
        this.mobile = textMobile;
    }
}
