package com.example.flyport.HELPERCLASSES;

import java.io.Serializable;

public class MainModel implements Serializable {
    private String denumireJob, numeCompanie, oras, tipJob,tara;
    private String descriere;
    private String minimumSalary;
    private String maximumSalary;
    private String benefits;
    private String experienceLevel;
    private String requiredEducation;
    private String languageRequirements;
    private String industry;
    private String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    private boolean favorite;

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public MainModel(){

    }

    public MainModel(String denumireJob, String numeCompanie, String oras, String tipJob, String descriere) {
        this.denumireJob = denumireJob;
        this.numeCompanie = numeCompanie;
        this.oras = oras;
        this.tipJob = tipJob;
        this.descriere= descriere;
        this.tara=tara;
    }

    public MainModel(String denumireJob, String numeCompanie, String oras, String tipJob, String descriere, String minimumSalary, String maximumSalary, String benefits, String experienceLevel, String requiredEducation, String languageRequirements, String industry, boolean favorite) {
        this.denumireJob = denumireJob;
        this.numeCompanie = numeCompanie;
        this.oras = oras;
        this.tipJob = tipJob;
        this.descriere=descriere;
        this.minimumSalary = minimumSalary;
        this.maximumSalary = maximumSalary;
        this.benefits = benefits;
        this.experienceLevel = experienceLevel;
        this.requiredEducation = requiredEducation;
        this.languageRequirements = languageRequirements;
        this.industry = industry;
        this.favorite = favorite;
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

    public String getDescriere() {
        return descriere;
    }

    public void setDescription(String descriere) {
        this.descriere=descriere;
    }

    public String getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(String minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public String getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(String maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String getRequiredEducation() {
        return requiredEducation;
    }

    public void setRequiredEducation(String requiredEducation) {
        this.requiredEducation = requiredEducation;
    }

    public String getLanguageRequirements() {
        return languageRequirements;
    }

    public void setLanguageRequirements(String languageRequirements) {
        this.languageRequirements = languageRequirements;
    }

    public String getIndustry() {
        return industry;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }


}
