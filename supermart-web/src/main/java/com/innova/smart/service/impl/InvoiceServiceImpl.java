package com.innova.smart.service.impl;

import com.innova.smart.beans.Invoice;
import com.innova.smart.beans.Product;
import com.innova.smart.dao.InvoiceDAO;
import com.innova.smart.dao.ProductDAO;
import com.innova.smart.dao.impl.InvoiceDAOImpl;
import com.innova.smart.dao.impl.ProductDAOImpl;
import com.innova.smart.service.InvoiceService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nirupam on 03-11-2018.
 */
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceDAO invoiceDAO;
    private ProductDAO productDAO;

    public InvoiceServiceImpl(Connection con) {
        invoiceDAO = new InvoiceDAOImpl(con);
        productDAO = new ProductDAOImpl(con);
    }

    @Override
    public synchronized Invoice create(List<Product> products) {
        List<Product> allowedProducts = new ArrayList<>();
        Map<String, Product> p1Map = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        try {
            Map<String, Product> p2Map = productDAO.findAllIn(
                    products.stream()
                    .map(Product::getId).collect(Collectors.toList()))
                    .stream()
                    .collect(Collectors.toMap(Product::getId, p -> p));

            p1Map.forEach((id, p) -> {
                Product db_p = p2Map.get(id);
                if (p.getQuantity() <= db_p.getQuantity()) {
                    p.setPrice(db_p.getPrice());
                    allowedProducts.add(p);
                }
            });

            if (allowedProducts.size() != products.size()) {
                System.err.println("Some Products Quantity is more than avaialble in Stock");
                return null;
            }

            Invoice inv = new Invoice("OD" + System.currentTimeMillis(), new Date());
            inv.setProducts(products);
            inv = invoiceDAO.create(inv);
            return inv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
