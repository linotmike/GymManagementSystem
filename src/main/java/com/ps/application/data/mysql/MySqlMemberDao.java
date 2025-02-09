package com.ps.application.data.mysql;

import com.ps.application.data.MemberDao;
import com.ps.application.models.Member;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlMemberDao extends MySqlBaseDao implements MemberDao {
    public MySqlMemberDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Member getMemberById(int memberId) {
        String query = "SELECT * FROM members WHERE member_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, memberId);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                if (resultSet.next()) {
                    return mapMember(resultSet);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        String query = "SELECT * FROM members";
        List<Member> members = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
                ) {
            while (resultSet.next()){
                Member member = mapMember(resultSet);
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Member createMember(Member member) {
        return null;
    }

    @Override
    public Member updateMember(Member member, int memberId) {
        return null;
    }

    @Override
    public Member deleteMember(int memberId) {
        return null;
    }

    public Member mapMember(ResultSet resultSet) throws SQLException {
        int memberId = resultSet.getInt("member_id");
        int userId = resultSet.getInt("user_id");
        int membershipTypeId = resultSet.getInt("membership_type_id");
        int pilatesPricingId = resultSet.getInt("pilates_pricing_id");
//        LocalDate startDate = resultSet.getDate("")
        LocalDate startDate = null;
        java.sql.Date dateStart = resultSet.getDate("start_date");
        if (dateStart != null) {
            startDate = dateStart.toLocalDate();
        }
        LocalDate endDate = null;
        java.sql.Date dateEnd = resultSet.getDate("end_date");
        if (dateEnd != null) {
            endDate = dateEnd.toLocalDate();
        }
//        LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
//        LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
        String status = resultSet.getString("status").trim().toUpperCase();
        Member.Status memStatus = null;
        if (status != null) {
            try {
                memStatus = Member.Status.valueOf(status);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return new Member(memberId, userId, membershipTypeId, pilatesPricingId, startDate, endDate, memStatus);

    }
}
