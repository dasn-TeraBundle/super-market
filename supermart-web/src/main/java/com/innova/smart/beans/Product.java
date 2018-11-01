package com.innova.smart.beans;

/**
 * Created by Nirupam on 01-11-2018.
 */
public class Product {
    private String id, name, category, supploer;
    private int quantity;
    private float price;

    public Product(String name, String category, String supploer, int quantity, float price) {
        this.name = name;
        this.category = category;
        this.supploer = supploer;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupploer() {
        return supploer;
    }

    public void setSupploer(String supploer) {
        this.supploer = supploer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", supploer='" + supploer + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
