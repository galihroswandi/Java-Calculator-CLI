package com.belajar.collectionframework.practice.inventorysystem;

import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class ProductManager {
    private HashMap<String, Product> products = new HashMap<>();
    private Locale local = Locale.of("id", "ID");
    private NumberFormat formatter = NumberFormat.getCurrencyInstance(local);

    public void showProducts() {
        System.out.println("\n==== LIST PRODUCTS ====");
        for (Map.Entry<String, Product> product : products.entrySet()) {
            System.out.println("UUID: " + product.getKey());
            System.out.println("Name: " + product.getValue().getName());
            System.out.println("Price: " + formatter.format(product.getValue().getPrice()));
            System.out.println("Quantity: " + product.getValue().getQty());
            System.out.println("=======================\n");
        }
    }

    public void addProduct(String name, double price, int qty) {
        if (name.trim().isEmpty()) {
            System.err.println("Name not empty!");
            return;
        }

        products.put(generateRandomUUID().toString(), new Product(name, price, qty));
        System.out.println("Product has been added!\n");
    }

    public void updateProduct(String uuid, String newName, double newPrice, int newQty) {
        if (!products.containsKey(uuid)) {
            System.err.println("Product with uuid (" + uuid + ") not found!");
            return;
        }

        products.get(uuid).setName(newName);
        products.get(uuid).setPrice(newPrice);
        products.get(uuid).setQuantity(newQty);
        System.out.println("Product has been updated!\n");
    }

    public void removeProduct(String uuid) {
        if (!products.containsKey(uuid)) {
            System.err.println("Product with uuid (" + uuid + ") not found!");
            return;
        }

        products.remove(uuid);
        System.out.println("Product has been removed!\n");
    }

    public void searchById(String uuid) {
        if (!products.containsKey(uuid)) {
            System.err.println("Product with uuid (" + uuid + ") not found!");
            return;
        }

        System.out.println("==== PRODUCT DETAIL ====");
        System.out.println("UUID: " + uuid);
        System.out.println("Name: " + products.get(uuid).getName());
        System.out.println("Price: " + formatter.format(products.get(uuid).getPrice()));
        System.out.println("Quantity: " + products.get(uuid).getQty());
        System.out.println("=======================\n");
    }

    public void checkAlertQuantity() {
        for (Map.Entry<String, Product> product : products.entrySet()) {
            if (product.getValue().getQty() < 10) {
                System.err.println("\n⚠️  Product with name " + product.getValue().getName() + " has low stock! ⚠️\n");
            }
        }
    }

    public void generateInventoryReport() {
        try {
            // Data Dummy
            String reportID = "INV-2026-001";
            String date = "2026-01-21";
            String operator = "Galih_Roswandi";
            FileWriter writer = new FileWriter("report_inventory.txt", true);

            // Header Laporan
            writer.write(
                    "====================================================================================\n");
            writer.write("                       INVENTORY STATUS REPORT (V1.0)                          \n");
            writer.write("                       Blockchain-Verified Integrity                           \n");
            writer.write(
                    "====================================================================================\n");
            writer.write("Report ID : " + reportID + "\n");
            writer.write("Date      : " + date + "\n");
            writer.write("Operator  : " + operator + "\n");
            writer.write(
                    "------------------------------------------------------------------------------------\n");

            // Table Header
            writer.write(String.format("| %-36s | %-15s | %-8s | %-12s |%n", "ID", "Item Name", "Stock", "Harga"));
            writer.write(
                    "------------------------------------------------------------------------------------\n");

            // Table Content (Contoh Loop Data)
            for (Map.Entry<String, Product> product : products.entrySet()) {
                writer.write(String.format("| %-36s | %-15s | %-8s | %-12s |%n", product.getKey(),
                        product.getValue().getName(),
                        product.getValue().getQty(), formatter.format(product.getValue().getPrice())));
            }

            writer.write(
                    "------------------------------------------------------------------------------------\n");

            // Footer / Metadata Blockchain
            // Ini adalah bagian "Tools" yang kamu impikan: mencatat hash untuk keamanan
            // data
            String mockHash = "0000x8f2d1299e...a122";
            writer.write("\n[BLOCKCHAIN METADATA]\n");
            writer.write("Status      : VERIFIED\n");
            writer.write("Prev Hash   : " + mockHash + "\n");
            writer.write("Timestamp   : " + System.currentTimeMillis() + "\n");
            writer.write(
                    "====================================================================================\n");

            writer.close();

            System.err.println("Generate inventory report success!");
        } catch (IOException e) {
            System.err.println("Error whe writing file: " + e.getMessage());
        }
    }

    private UUID generateRandomUUID() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID;
    }

}
