package com.example.flyport.HELPERCLASSES;

public class UserHelperClass {
    String name, email, password, city, country,phoneNumber;

    public UserHelperClass(String name, String email, String password, String city, String country, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public UserHelperClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
