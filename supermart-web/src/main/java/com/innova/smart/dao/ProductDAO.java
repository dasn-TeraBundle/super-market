package com.innova.smart.dao;

import com.innova.smart.beans.Product;

import java.sql.SQLException;

/**
 * Created by Nirupam on 01-11-2018.
 */
public interface ProductDAO {
    Product add(Product product) throws SQLException;
    Product update(Product product) throws SQLException;
}
