package com.belajar.collectionframework.practice.inventorysystem;

public class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQty(){
        return quantity;
    }

    public boolean setName(String name){
        if(name.trim().isEmpty()){
            return false;
        }

        this.name = name;
        return true;
    }
    public boolean setPrice(double  price){
        if(price < 0){
            return false;
        }

        this.price = price;
        return true;
    }
    public boolean setQuantity(int qty){
        if(qty < 0){
            return false;
        }

        quantity = qty;
        return true;
    }
}
