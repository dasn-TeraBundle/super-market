package com.innova.smart.service;

import com.innova.smart.beans.Invoice;
import com.innova.smart.beans.Product;

import java.util.List;

/**
 * Created by Nirupam on 03-11-2018.
 */
public interface InvoiceService {

    Invoice create(List<Product> products);
    Invoice find(String id);
}
