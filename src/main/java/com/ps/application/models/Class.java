package com.ps.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Class {

    private int classId;
    private String name;
    @JsonProperty("type")
    private Type type;
    private String description;
    private DifficultyLevel difficultyLevel;
    private int maxParticipants;

    public enum Type {
        MAT,REFORMER,PRENATAL,POSTPARTUM
    }
    public enum DifficultyLevel{
        BEGINNER,INTERMEDIATE,ADVANCED
    }
    public Class(){}


    public Class(int classId, String name, Type type, String description, DifficultyLevel difficultyLevel, int maxParticipants) {
        this.classId = classId;
        this.name = name;
        this.type = type;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.maxParticipants = maxParticipants;
    }

    public int getClassId (){
        return classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setClassId(int classId){
        this.classId = classId;
    }
}
