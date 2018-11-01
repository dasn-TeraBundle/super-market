package com.innova.smart.beans;

/**
 * Created by Nirupam on 01-11-2018.
 */
public class Supplier {
    private String id, name;

    public Supplier(String id, String name) {
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
