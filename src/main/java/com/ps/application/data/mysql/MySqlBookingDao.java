package com.ps.application.data.mysql;

import com.ps.application.data.BookingDao;
import com.ps.application.models.Booking;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MySqlBookingDao extends MySqlBaseDao implements BookingDao {
    public MySqlBookingDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Booking getByBookId(int bookingId) {

        return null;
    }


    public Booking mapBooks (ResultSet resultSet) throws SQLException{
        int bookingId = resultSet.getInt("booking_id");
        int classID = resultSet.getInt("class_id");
        int memberId = resultSet.getInt("member_id");
        int userId = resultSet.getInt("user_id");
        int scheduleId = resultSet.getInt("schedule_id");
        LocalDate bookingDate = resultSet.getDate("booking_date").toLocalDate();
        Booking.Status status = Booking.Status.valueOf(resultSet.getString("status").toUpperCase());

        return new Booking(bookingId,classID,memberId,userId,scheduleId,bookingDate,status);
    }
}
