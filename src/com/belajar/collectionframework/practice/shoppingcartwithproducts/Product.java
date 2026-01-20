package com.belajar.collectionframework.practice.shoppingcartwithproducts;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int newId) {
        if (newId < 0) {
            System.err.println("ID cannot be zero or below 1");
            return;
        }
        this.id = newId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Name is not empty!");
            return;
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.err.println("Price cannot below 1");
            return;
        }
        this.price = price;
    }

    public void setQty(int qty) {
        if (qty < 0) {
            System.err.println("Minimum price: 0");
        }
        this.quantity = qty;
    }
}
