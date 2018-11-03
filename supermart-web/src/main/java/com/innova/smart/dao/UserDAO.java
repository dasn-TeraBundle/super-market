package com.innova.smart.dao;

import com.innova.smart.beans.User;

import java.sql.SQLException;

/**
 * Created by Nirupam on 03-11-2018.
 */
public interface UserDAO {

    User add(User user) throws SQLException;
    User findByUsername(String username) throws SQLException;
}
