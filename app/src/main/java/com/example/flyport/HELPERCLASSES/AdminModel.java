package com.example.flyport.HELPERCLASSES;

public class AdminModel {
    private String denumireJob, numeCompanie, oras, tipJob;
    private String description;
    private boolean favorite;

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public AdminModel() {
    }

    public AdminModel(String denumireJob, String numeCompanie, String oras, String tipJob, String description) {
        this.denumireJob = denumireJob;
        this.numeCompanie = numeCompanie;
        this.oras = oras;
        this.tipJob = tipJob;
        this.description = description;
    }

    public String getDenumireJob() {
        return denumireJob;
    }

    public void setDenumireJob(String denumireJob) {
        this.denumireJob = denumireJob;
    }

    public String getNumeCompanie() {
        return numeCompanie;
    }

    public void setNumeCompanie(String numeCompanie) {
        this.numeCompanie = numeCompanie;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getTipJob() {
        return tipJob;
    }

    public void setTipJob(String tipJob) {
        this.tipJob = tipJob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
