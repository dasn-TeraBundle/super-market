package com.innova.smart.service;

import com.innova.smart.beans.User;

/**
 * Created by Nirupam on 03-11-2018.
 */
public interface UserService {

    User register(User user);
    User login(String username, String password);
}
