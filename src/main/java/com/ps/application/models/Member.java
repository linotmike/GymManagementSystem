package com.ps.application.models;

import java.time.LocalDate;

public class Member {
    private int memberId;
    private int userId;
    private int memberShipId;
    private int pilatesPricingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;

    public enum Status {
        ACTIVE,PAUSED,EXPIRED
    }
    public Member(){}

    public Member(int memberId,int userId, int memberShipId, int pilatesPricingId, LocalDate startDate, LocalDate endDate, Status status){
        this.memberId = memberId;
        this.userId = userId;
        this.memberShipId = memberShipId;
        this.pilatesPricingId = pilatesPricingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    public int getMemberId (){
        return memberId;
    }
    public void setMemberId(int memberId){
        this.memberId = memberId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMemberShipId() {
        return memberShipId;
    }

    public void setMemberShipId(int memberShipId) {
        this.memberShipId = memberShipId;
    }

    public int getPilatesPricingId() {
        return pilatesPricingId;
    }

    public void setPilatesPricingId(int pilatesPricingId) {
        this.pilatesPricingId = pilatesPricingId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}

