package com.innova.smart.dao;

import com.innova.smart.beans.Supplier;

import java.util.List;

/**
 * Created by Nirupam on 01-11-2018.
 */
public interface SupplierDAO {

    List<Supplier> findAll();
}
