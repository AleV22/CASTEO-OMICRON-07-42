package com.example.alejandroveronesi.omicron742.Model.POJO;



public class Event {

    private String eventName;
    private Integer eventTime;
    private Integer phone;
    private String contact;
    private String position;


    //constructor
    public Event(String eventName, Integer eventTime, Integer phone, String contact) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.phone = phone;
        this.contact = contact;
    }

    //getters, setters
    public String getEventName() {
        return eventName;
    }

    public Integer getEventTime() {
        return eventTime;
    }

    public String getContact() {
        return contact;
    }

    public String getPosition() {
        return position;
    }

    public Integer getPhone() {
        return phone;
    }
}
