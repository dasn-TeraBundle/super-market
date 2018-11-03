package com.innova.smart.dao.impl;

import com.innova.smart.beans.Product;
import com.innova.smart.dao.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                "(id, name, category, supplier, quantity, price) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareCall(sql);
        ps.setString(1, product.getId());
        ps.setString(2, product.getName());
        ps.setString(3, product.getCategory());
        ps.setString(4, product.getSupplier());
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

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try {
            String sql = "SELECT p.id, p.name, c.name as category, s.name as supplier, " +
                    "p.quantity, p.price FROM Products p, Product_categories c, Suppliers s " +
                    "WHERE " +
                    "c.id = p.category " +
                    "AND " +
                    "s.id = p.supplier";
            try (PreparedStatement ps = conn.prepareCall(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Product p = new Product(
                                rs.getString("name"),
                                rs.getString("category"),
                                rs.getString("supplier"),
                                rs.getInt("quantity"),
                                rs.getFloat("price"));
                        p.setId(rs.getString("id"));
                        products.add(p);
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //System.out.println(products);
        return products;
    }
}
