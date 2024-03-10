package com.sms.sms.entity;

public class Message {
    private String from;
    private String to;
    private String phoneNumber;
    private String body;

    public Message(){
        this("","","","");
    }

    public  Message(String body){
        this("","","",body);
    }
    public Message(String phoneNumber , String body){
        this("","",phoneNumber,body);
    }
    private Message(String from, String to, String phoneNumber, String body) {
        this.from = from;
        this.to = to;
        this.phoneNumber = phoneNumber;
        this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public Message setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public Message setTo(String to) {
        this.to = to;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Message setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Message setBody(String body) {
        this.body = body;
        return this;
    }
}
