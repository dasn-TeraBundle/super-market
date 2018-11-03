package com.innova.smart.dao;

import com.innova.smart.beans.Invoice;

import java.sql.SQLException;

/**
 * Created by Nirupam on 03-11-2018.
 */
public interface InvoiceDAO {

    Invoice create(Invoice invoice) throws SQLException;
}
