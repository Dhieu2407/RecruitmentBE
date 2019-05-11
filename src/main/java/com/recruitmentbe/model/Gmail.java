package com.recruitmentbe.model;

public class Gmail {
    // Replace with your email here:
    public static final String MY_EMAIL = "tuyendungdatn@gmail.com";
    // Replace password!!
    public static final String MY_PASSWORD = "tuyendung2019";
    public String name;
    private String titleMai;
    private String emailCandiate;
    private String contentMail;
    private Company company;
    private Job job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitleMai() {
        return titleMai;
    }

    public void setTitleMai(String titleMai) {
        this.titleMai = titleMai;
    }

    public String getEmailCandiate() {
        return emailCandiate;
    }

    public void setEmailCandiate(String emailCandiate) {
        this.emailCandiate = emailCandiate;
    }

    public String getContentMail() {
        return contentMail;
    }

    public void setContentMail(String contentMail) {
        this.contentMail = contentMail;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
