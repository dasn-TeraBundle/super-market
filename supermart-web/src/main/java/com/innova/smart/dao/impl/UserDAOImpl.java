package com.innova.smart.dao.impl;

import com.innova.smart.beans.User;
import com.innova.smart.dao.UserDAO;

import java.sql.*;

/**
 * Created by Nirupam on 03-11-2018.
 */
public class UserDAOImpl implements UserDAO {

    private Connection conn;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public User add(User user) throws SQLException {
        String sql = "INSERT INTO USERS(NAME, SURNAME, ROLE, ADDRESS, SALARY, BIRTHDAY, EMPLOYMENT_DATE, PHONE, USERNAME, PASSWORD)  " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareCall(sql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getSurname());
        ps.setString(3, user.getRole().toString());
        ps.setString(4, user.getAddress());
        ps.setFloat(5, user.getSalary());
        ps.setDate(6, new Date(user.getBirthday().getTime()));
        ps.setDate(7, new Date(user.getEmploymentDate().getTime()));
        ps.setString(8, "NA");
        ps.setString(9, user.getUsername());
        ps.setString(10, user.getPassword());
        int r = ps.executeUpdate();
        conn.commit();
        ps.close();

        if (r == 1)
            return user;

        return null;
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username=?";
        User user = null;

        try (PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    user = new User(
                            rs.getString("name"),
                            rs.getString("role"),
                            rs.getString("username"),
                            rs.getString("password"));
            }
        }

        return user;
    }
}
