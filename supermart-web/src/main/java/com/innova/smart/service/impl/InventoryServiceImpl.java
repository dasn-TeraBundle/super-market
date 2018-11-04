package com.innova.smart.service.impl;

import com.innova.smart.beans.Product;
import com.innova.smart.dao.ProductDAO;
import com.innova.smart.dao.impl.ProductDAOImpl;
import com.innova.smart.exceptions.InventoryException;
import com.innova.smart.service.InventoryService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nirupam on 03-11-2018.
 */
public class InventoryServiceImpl implements InventoryService {

    private ProductDAO productDAO;

    public InventoryServiceImpl(Connection conn) {
        productDAO = new ProductDAOImpl(conn);
    }

    @Override
    public Product add(Product product) {
        product.setId("PR_" + System.currentTimeMillis());
        try {
            return productDAO.add(product);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InventoryException(ex.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(String id) {
        try {
            Product p = productDAO.findByID(id);
            if (p == null)
                throw new InventoryException("Wrong Product ID");
            else
                return p;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InventoryException("Something went wrong. Try Later");
        }
    }

    @Override
    public Product update(Product product) {
        try {
            Product p = productDAO.update(product);
            if (p == null)
                throw new InventoryException("Wrong Product ID");
            else
                return p;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InventoryException("Something went wrong. Try Later");
        }
    }
}
