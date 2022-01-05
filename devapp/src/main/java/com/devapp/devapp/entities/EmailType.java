package com.devapp.devapp.entities;

public enum EmailType {
    PASSWORD("resetpassword","rest password", 864_000_000),
    CONFIRAMTION("confiramtionaccount","comfirm account", 864_000_000);

    private final String path;
    private final String subject;
    private final long expirationTokenTime;

    EmailType(String path, String subject, long expirationTokenTime) {
        this.path =path;
        this.subject =subject;
        this.expirationTokenTime =expirationTokenTime;
    }

    public String getPath(){
        return path;
    }
    public String getSubject(){
        return subject;
    }
    public Long getExpirationTokenTime(){
        return expirationTokenTime;
    }
}
