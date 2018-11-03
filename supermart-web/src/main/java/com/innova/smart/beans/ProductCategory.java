package com.innova.smart.beans;

/**
 * Created by Nirupam on 01-11-2018.
 */
public class ProductCategory {

    private String id, name;

    public ProductCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
