package com.example.newlife_narok_sda_church_management_app;

public class EventClass {
    String Date;
    String Location;
    String About;

    public EventClass() {
    }

    public EventClass(String date, String location, String about) {
        Date = date;
        Location = location;
        About = about;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }
}
