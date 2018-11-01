package com.innova.smart.dao.impl;

import com.innova.smart.beans.Product;
import com.innova.smart.dao.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Nirupam on 01-11-2018.
 */
public class ProductDAOImpl implements ProductDAO {

    private Connection conn;

    public ProductDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Product add(Product product) throws SQLException {
        String sql = "INSERT INTO Products " +
                "(id, name, category, supplier, expiry, quantity, price) " +
                "VALUES (PRD_SEQ.nextval, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareCall(sql);
        ps.setString(1, product.getName());
        ps.setString(2, product.getCategory());
        ps.setString(3, product.getSupploer());
        ps.setInt(5, product.getQuantity());
        ps.setFloat(6, product.getPrice());
        int r = ps.executeUpdate();
        conn.commit();

        if (r == 1)
            return product;
        return null;
    }

    @Override
    public Product update(Product product) throws SQLException {
        return null;
    }
}
