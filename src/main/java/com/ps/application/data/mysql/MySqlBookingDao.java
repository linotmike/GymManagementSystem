package com.ps.application.data.mysql;

import com.ps.application.data.BookingDao;
import com.ps.application.models.Booking;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
@Component
public class MySqlBookingDao extends MySqlBaseDao implements BookingDao {
    public MySqlBookingDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Booking getByBookingId(int bookingId) {
        String query = "SELECT * FROM bookings WHERE booking_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1,bookingId);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while(resultSet.next()){
                    return mapBooks(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
