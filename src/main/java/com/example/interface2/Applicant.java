package com.example.interface2;

import java.io.Serializable;

public class Applicant implements Serializable {
    private String fullName;
    private String phoneNumber;
    private int egeScore;
    private int priority;
    private String profile;
    private boolean willGetIn;
    private boolean willNotGetIn;
    private boolean submitDocuments;
    private boolean alreadyEnrolled;
    private boolean documentsSubmitted;

    // Добавим новые поля для чекбоксов
    private boolean isOutsider; // для "иногородние"
    private boolean isTemporaryLeave; // для "временное в отъезде"
    private boolean isCallback; // для "перезвонить"
    private String comments; // для комментариев

    public Applicant(String fullName, String phoneNumber, int egeScore, int priority, String profile) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.egeScore = egeScore;
        this.priority = priority;
        this.profile = profile;
    }

    // Геттеры и сеттеры

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getEgeScore() {
        return egeScore;
    }

    public int getPriority() {
        return priority;
    }

    public String getProfile() {
        return profile;
    }

    public boolean isWillGetIn() {
        return willGetIn;
    }

    public void setWillGetIn(boolean willGetIn) {
        this.willGetIn = willGetIn;
    }

    public boolean isWillNotGetIn() {
        return willNotGetIn;
    }

    public void setWillNotGetIn(boolean willNotGetIn) {
        this.willNotGetIn = willNotGetIn;
    }

    public boolean isSubmitDocuments() {
        return submitDocuments;
    }

    public void setSubmitDocuments(boolean submitDocuments) {
        this.submitDocuments = submitDocuments;
    }

    public boolean isAlreadyEnrolled() {
        return alreadyEnrolled;
    }

    public void setAlreadyEnrolled(boolean alreadyEnrolled) {
        this.alreadyEnrolled = alreadyEnrolled;
    }

    public boolean isDocumentsSubmitted() {
        return documentsSubmitted;
    }

    public void setDocumentsSubmitted(boolean documentsSubmitted) {
        this.documentsSubmitted = documentsSubmitted;
    }

    // Геттеры и сеттеры для новых полей

    public boolean isOutsider() {
        return isOutsider;
    }

    public void setOutsider(boolean outsider) {
        isOutsider = outsider;
    }

    public boolean isTemporaryLeave() {
        return isTemporaryLeave;
    }

    public void setTemporaryLeave(boolean temporaryLeave) {
        isTemporaryLeave = temporaryLeave;
    }

    public boolean isCallback() {
        return isCallback;
    }

    public void setCallback(boolean callback) {
        isCallback = callback;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEgeScore(int egeScore) {
        this.egeScore = egeScore;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}