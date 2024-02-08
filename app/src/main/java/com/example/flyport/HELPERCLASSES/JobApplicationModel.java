package com.example.flyport.HELPERCLASSES;

public class JobApplicationModel {
    private String userId;
    private String jobId;
    private String cvUrl;

    public JobApplicationModel() {
        // Constructorul gol necesar pentru Firebase
    }

    public JobApplicationModel(String userId, String jobId, String cvUrl) {
        this.userId = userId;
        this.jobId = jobId;
        this.cvUrl = cvUrl;
    }

    // Getteri și setteri


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }
}
