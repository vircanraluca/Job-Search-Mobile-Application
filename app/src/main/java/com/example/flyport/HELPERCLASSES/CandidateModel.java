package com.example.flyport.HELPERCLASSES;

import com.example.flyport.JOBSEEKER.pdfClass;

public class CandidateModel {
    String city, country, email, fullName, password, phoneNumber, url;
    private boolean hasUploaded;
    private pdfClass pdf;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public pdfClass getPdf() {
        return pdf;
    }

    public void setPdf(pdfClass pdf) {
        this.pdf = pdf;
    }


    public boolean isHasUploaded() {
        return hasUploaded;
    }

    public void setHasUploaded(boolean hasUploaded) {
        this.hasUploaded = hasUploaded;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CandidateModel(){

    }

    public CandidateModel(String city, String country, String email, String fullName, String password, String phoneNumber) {
        this.city = city;
        this.country = country;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
