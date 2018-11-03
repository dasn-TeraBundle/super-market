package com.innova.smart.dao;

import com.innova.smart.beans.ProductCategory;

import java.util.List;

/**
 * Created by Nirupam on 03-11-2018.
 */
public interface ProductCategoryDAO {

    List<ProductCategory> findAll();
}
