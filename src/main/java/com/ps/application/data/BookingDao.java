package com.ps.application.data;

import com.ps.application.models.Booking;

public interface BookingDao {
    Booking getByBookingId(int bookingId);

}
