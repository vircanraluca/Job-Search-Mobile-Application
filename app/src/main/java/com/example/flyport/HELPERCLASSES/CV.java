package com.example.flyport.HELPERCLASSES;

public class CV {
    private String name;
    private String url;

    public CV() {
        // Constructor gol necesar pentru Firebase
    }

    public CV(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
