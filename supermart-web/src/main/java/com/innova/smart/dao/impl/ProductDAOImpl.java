package com.innova.smart.dao.impl;

import com.innova.smart.beans.Product;
import com.innova.smart.dao.ProductDAO;

import java.sql.*;
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
        String sql = "UPDATE Products SET " +
                "name = ?, category = ?, supplier = ?, quantity = ?, price = ? " +
                "WHERE id = ?";

        PreparedStatement ps = conn.prepareCall(sql);
        ps.setString(1, product.getName());
        ps.setString(2, product.getCategory());
        ps.setString(3, product.getSupplier());
        ps.setInt(4, product.getQuantity());
        ps.setFloat(5, product.getPrice());
        ps.setString(6, product.getId());
        int r = ps.executeUpdate();
        conn.commit();

        if (r == 1)
            return product;
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

    @Override
    public List<Product> findAllIn(List<String> pIds) {
        List<Product> products = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM Products WHERE id IN (");
            for (int i = 0; i < pIds.size(); i++) {
                sql.append("?");
                if (i < pIds.size() - 1)
                    sql.append(",");
            }
            sql.append(")");

            try (PreparedStatement ps = conn.prepareCall(sql.toString())) {
                for (int i = 0; i < pIds.size(); i++)
                    ps.setString(i + 1, pIds.get(i));

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

        return products;
    }

    @Override
    public Product findByID(String id) throws SQLException {
        Product p = null;
        String sql = "SELECT * FROM Products WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Product(
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getString("supplier"),
                            rs.getInt("quantity"),
                            rs.getFloat("price"));
                    p.setId(rs.getString("id"));
                }
            }
        }

        return p;
    }
}
