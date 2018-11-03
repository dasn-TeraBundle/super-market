package com.innova.smart.dao.impl;

import com.innova.smart.beans.User;
import com.innova.smart.dao.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "INSERT INTO USERS(NAME, ROLE, USERNAME, PASSWORD) " +
                "VALUES ('?', '?', '?', '?')";

        PreparedStatement ps = conn.prepareCall(sql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getRole().toString());
        ps.setString(3, user.getUsername());
        ps.setString(4, user.getPassword());
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
