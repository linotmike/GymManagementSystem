package com.ps.application.models;

public class Instructors {
    private int instructorId;
    private int userId;
    private  String firstName;
    private  String lastName;
    private String bio;
    private String specialty;

    public Instructors (){
    }

    public Instructors(int instructorId, int userId, String firstName, String lastName, String bio, String specialty) {
        this.instructorId = instructorId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.specialty = specialty;
    }

    public int getInstructorId(){
        return instructorId;
    }
    public void setInstructorId(int instructorId){
        this.instructorId = instructorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
