package com.belajar.collectionframework.practice.shoppingcartwithproducts;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

public class ProductManager {
    // private ArrayList<Product> products = new ArrayList<>();
    private final HashMap<Integer, Product> products = new HashMap<>();
    private final Locale locale = Locale.of("id", "ID");
    private final NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

    public void addProduct(String name, double price, int qty){
        products.put(products.size()+1, new Product(products.size()+1, name, price, qty));
    }

    public void addChart(int productId){
        if(!products.containsKey(productId)){
            System.err.println("Product with id "+productId+" not found!");
            return;
        }

        products.put(products.size() + 1, products.get(productId));
        System.out.println("Product has been added!");
    }

    public void updateQty(int productId, int newQty){
        if(!products.containsKey(productId)){
            System.err.println("Product with id "+productId+" not found!");
            return;
        }

        for(Product product: products.values()){
            if(product.getId() == productId){
                product.setQty(newQty);
            }
        }
    }

    public void removeCart(int productId){
        if(!products.containsKey(productId)){
            System.err.println("Product with id "+productId+" not found!");
            return;
        }

        products.remove(productId);
        System.out.println("Product has been deleted!");
    }

    public void calculateTotal(){
        double total = 0;
        for(Product product: products.values()){
            total = product.getQuantity() * product.getPrice();
        }

        System.out.println("Total price: "+ formatter.format(total));
    }
}
