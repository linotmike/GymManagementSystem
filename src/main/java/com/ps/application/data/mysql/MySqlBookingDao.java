package com.ps.application.data.mysql;

import com.ps.application.data.BookingDao;
import com.ps.application.models.Booking;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlBookingDao extends MySqlBaseDao implements BookingDao {
    public MySqlBookingDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Booking getByBookId(int bookingId) {

        return null;
    }
}
