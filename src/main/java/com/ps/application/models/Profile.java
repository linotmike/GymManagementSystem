package com.ps.application.models;

import java.time.LocalDate;

public class Profile {
    private int profileId;
    private int userId;
    private String bio;
    private String imageUrl;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private LocalDate dateOfBirth;
    private String fitnessGoals;
    private String healthCondition;

    public Profile() {
    }

    public Profile(int profileId, int userId, String bio, String imageUrl, String phone, String email, String address, String city, String state, String zip, LocalDate dateOfBirth, String fitnessGoals, String healthCondition) {
        this.profileId = profileId;
        this.userId = userId;
        this.bio = bio;
        this.imageUrl = imageUrl;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.dateOfBirth = dateOfBirth;
        this.fitnessGoals = fitnessGoals;
        this.healthCondition = healthCondition;
    }

    public int getProfileId() {
        return profileId;
    }
    public void setProfileId(int profileId){
        this.profileId = profileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFitnessGoals() {
        return fitnessGoals;
    }

    public void setFitnessGoals(String fitnessGoals) {
        this.fitnessGoals = fitnessGoals;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }
}
