package com.innova.smart.dao.impl;

import com.innova.smart.beans.Invoice;
import com.innova.smart.beans.Product;
import com.innova.smart.dao.InvoiceDAO;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Nirupam on 03-11-2018.
 */
public class InvoiceDAOImpl implements InvoiceDAO {

    private Connection conn;

    public InvoiceDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Invoice create(Invoice invoice) throws SQLException{
        Savepoint savepoint = conn.setSavepoint();

        String inv_sql = "INSERT INTO Invoices (id, billing_date) " +
                "VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(inv_sql);
        ps.setString(1, invoice.getId());
        ps.setDate(2, new Date(invoice.getBillingDate().getTime()));
        int r = ps.executeUpdate();
        if (r != 1) {
            conn.rollback(savepoint);
            return null;
        }
        ps.close();

        /*String inv_prod_sql = "INSERT INTO Invoices_Products" +
                "(invoice_id, product_id, quantity, cost) " +
                "VALUES(?, ?, ?, ?)";
        ps = conn.prepareStatement(inv_prod_sql);*/
        Statement stmt = conn.createStatement();
        for (Product p : invoice.getProducts()) {
            String inv_prod_sql = "INSERT INTO Invoices_Products" +
                "(invoice_id, product_id, quantity, cost) " +
                "VALUES('" + invoice.getId() + "','" + p.getId() +"'," +
                    p.getQuantity() + "," + p.getQuantity() * p.getPrice() + ")";

            /*ps.setString(1, invoice.getId());
            ps.setString(2, p.getId());
            ps.setInt(3, p.getQuantity());
            ps.setFloat(4, p.getQuantity() * p.getPrice());
            ps.addBatch();*/
            stmt.addBatch(inv_prod_sql);
        }
        r = Arrays.stream(stmt.executeBatch()).sum();
        if (r != invoice.getProducts().size()) {
            conn.rollback(savepoint);
            return null;
        }
        //ps.close();
        stmt.close();

        /*String prod_upd_sql = "UPDATE Products SET quantity=quantity - ? WHERE id=?";
        ps = conn.prepareStatement(prod_upd_sql);*/
        stmt = conn.createStatement();
        for (Product p : invoice.getProducts()) {
            String prod_upd_sql = "UPDATE Products SET quantity=quantity - " +
                    p.getQuantity() + " WHERE id='" + p.getId() + "'";
            /*ps.setInt(1, p.getQuantity());
            ps.setString(2, p.getId());
            ps.addBatch();*/
            stmt.addBatch(prod_upd_sql);
        }
        r = Arrays.stream(stmt.executeBatch()).sum();
        if (r != invoice.getProducts().size()) {
            conn.rollback(savepoint);
            return null;
        }
        stmt.close();
        conn.commit();

        return invoice;
    }

    @Override
    public Invoice findByID(String id) throws SQLException {
        Invoice inv = new Invoice();
        inv.setId(id);

        String sql = "SELECT i.id, i.billing_date, " +
                "ip.quantity AS quantity, ip.cost as cost, "+
                "p.name as name " +
                "FROM Invoices i, Invoices_Products ip, Products p "+
                "WHERE i.id = ? " +
                "AND i.id = ip.invoice_id " +
                "AND ip.product_id = p.id";
        //System.out.println(sql);
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                List<Product> products = new ArrayList<>();
                while (rs.next()) {
                    inv.setBillingDate(new java.util.Date(rs.getDate("billing_date").getTime()));
                    Product p = new Product(
                            rs.getString("name"),
                            "",
                            "",
                            rs.getInt("quantity"),
                            rs.getFloat("cost"));
                    products.add(p);
                }
                if (products.size() > 0)
                    inv.setProducts(products);
            }
        }

        if (inv.getBillingDate() == null)
            return null;
        return inv;
    }
}
