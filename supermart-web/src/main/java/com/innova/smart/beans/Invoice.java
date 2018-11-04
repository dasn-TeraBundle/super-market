package com.innova.smart.beans;

import java.util.Date;
import java.util.List;

/**
 * Created by Nirupam on 03-11-2018.
 */
public class Invoice {

    private String id;
    private Date billingDate;
    private List<Product> products;

    public Invoice() {
    }

    public Invoice(String id, Date billingDate) {
        this.id = id;
        this.billingDate = billingDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        return !(id != null ? !id.equals(invoice.id) : invoice.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", billingDate='" + billingDate + '\'' +
                ", products=" + products +
                '}';
    }
}
