package com.innova.smart.service.impl;

import com.innova.smart.beans.User;
import com.innova.smart.dao.UserDAO;
import com.innova.smart.dao.impl.UserDAOImpl;
import com.innova.smart.exceptions.UserException;
import com.innova.smart.service.UserService;
import com.innova.smart.web.security.Encrypt;

import java.sql.Connection;

/**
 * Created by Nirupam on 03-11-2018.
 */
public class UserServiceImpl implements UserService {

    private Encrypt encrypter;
    private UserDAO userDAO;

    public UserServiceImpl(Connection conn) {
        this.encrypter = Encrypt.getInstance();
        this.userDAO = new UserDAOImpl(conn);
    }

    @Override
    public User register(User user) {
        try {
            user.setUsername(user.getUsername().toLowerCase());
            user.setPassword(encrypter.hash("SHA-256", user.getPassword()));
            user = userDAO.add(user);
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new UserException("Registration Failed. Please try later");
        }
    }

    @Override
    public User login(String username, String password) {
        try {
            User user = userDAO.findByUsername(username);

            if (user == null)
                throw new Exception("");
            if (user.getPassword().equals(encrypter.hash("SHA-256", password)))
                return user;
            else {
                System.out.println("DB : " + user.getPassword());
                System.out.println("ENTERED : " + encrypter.hash("SHA-256", password));
                throw new Exception("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new UserException("Username/Password Mismatch");
        }
    }
}
