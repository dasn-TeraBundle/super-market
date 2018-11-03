package com.innova.smart.dao.impl;

import com.innova.smart.beans.Supplier;
import com.innova.smart.dao.SupplierDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirupam on 01-11-2018.
 */
public class SupplierDAOImpl implements SupplierDAO {

    private Connection conn;

    public SupplierDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Supplier> findAll() {
        List<Supplier> suppliers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Suppliers";
            try (PreparedStatement ps = conn.prepareCall(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Supplier supplier = new Supplier(
                                rs.getString("id"),
                                rs.getString("name")
                        );
                        suppliers.add(supplier);
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return suppliers;
    }
}
