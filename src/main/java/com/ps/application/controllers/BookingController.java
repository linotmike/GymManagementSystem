package com.ps.application.controllers;

import com.ps.application.data.BookingDao;
import com.ps.application.data.ClassDao;
import com.ps.application.data.MemberDao;
import com.ps.application.data.ScheduleDao;
import com.ps.application.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private BookingDao bookingDao;
    private ClassDao classDao;
    private MemberDao memberDao;
    private ScheduleDao scheduleDao;

    @Autowired
    public BookingController(BookingDao bookingDao, ClassDao classDao, MemberDao memberDao, ScheduleDao scheduleDao){
        this.bookingDao = bookingDao;
        this.classDao = classDao;
        this.memberDao = memberDao;
        this.scheduleDao = scheduleDao;
    }


    @GetMapping("{bookingId}")
    public Booking getById(@PathVariable int bookingId){

        return bookingDao.getByBookingId(bookingId);
    }

}
