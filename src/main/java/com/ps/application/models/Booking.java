package com.ps.application.models;

import java.time.LocalDate;
import java.util.Date;

public class Booking {
    private int bookingId;
    private int classId;
    private int memberId;
    private int userId;
    private int scheduleId;
    private LocalDate bookingDate;
    public enum Status {
        BOOKED, CANCELLED, COMPLETED
    }
    private Status status;

    public Booking (int bookingId, int classId, int memberId, int userId, int scheduleId, LocalDate bookingDate, Status status){
        this.bookingId = bookingId;
        this.classId = classId;
        this.memberId = memberId;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public int getBookingId(){
        return bookingId;
    }
    public void setBookingId(int bookingId){
        this.bookingId = bookingId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
