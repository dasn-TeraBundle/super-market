package com.innova.smart.dao.impl;

import com.innova.smart.beans.ProductCategory;
import com.innova.smart.dao.ProductCategoryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirupam on 01-11-2018.
 */
public class ProductCategoryDAOImpl implements ProductCategoryDAO {

    private Connection conn;

    public ProductCategoryDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> suppliers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Product_Categories";
            try (PreparedStatement ps = conn.prepareCall(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        ProductCategory category = new ProductCategory(
                                rs.getString("id"),
                                rs.getString("name")
                        );
                        suppliers.add(category);
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return suppliers;
    }
}
