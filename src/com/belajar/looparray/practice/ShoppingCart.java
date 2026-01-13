package com.belajar.looparray.practice;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class ShoppingCart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        String[] productNames = new String[100];
        double[] productPrices = new double[100];
        Locale locale = Locale.of("id", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        double totalPrice = 0d;
        float discountPercentage = 10f;

        double discount = totalPrice * (discountPercentage / 100);
        double finalPrice = totalPrice - discount;

        int index = 0;

        while (running) {
            System.out.println("\n╔════════════════════════╗");
            System.out.println("║      SHOPPING CART     ║");
            System.out.println("╚════════════════════════╝");
            System.out.println("1. Add item (name dan price)");
            System.out.println("2. View chart");
            System.out.println("3. Remove item by index ");
            System.out.println("4. Calculate total");
            System.out.println("5. Apply discount 10%");
            System.out.println("6. Checkout");
            System.out.println("7. Clear Cart");
            System.out.println("8. Exit");
            System.out.print("Choose option (1-8): ");
            byte choice = scanner.nextByte();

            switch (choice) {
                case 1:
                    System.out.println("Enter product (format: name,price)");
                    String[] product = scanner.next().split(",");
                    if (product.length < 2) {
                        System.err.println("Invalid format: Use (name,price)");
                        continue;
                    }
                    double parsedPrice = Double.parseDouble(product[1].trim());
                    if (index < productNames.length) {
                        productNames[index] = product[0];
                        productPrices[index] = parsedPrice;
                        index++;
                    } else {
                        System.err.println("Cart sudah penuh!");
                    }
                    break;

                case 2:
                    if(index == 0){
                        System.out.println("📦 No product available!");
                        break;
                    }

                    System.out.println("\n════════ CART ════════");
                    for (byte i = 0; i < index; i++) {
                        String displayCart = String.format("Id: %d \nName: %s \nPrice: %s", i+1, productNames[i], formatter.format(productPrices[i]));
                        System.out.println(displayCart);
                        System.out.println("----------------------");
                    }
                    System.out.println("════════════════════════");
                    break;

                case 3:
                    if(index == 0){
                        System.out.println("📦 No product to delete!");
                        break;
                    }

                    System.out.println("\n════════ CART ════════");
                    for (byte i = 0; i < index; i++) {
                        String displayCart = String.format("Id: %d \nName: %s \nPrice: %s", i+1, productNames[i], formatter.format(productPrices[i]));
                        System.out.println(displayCart);
                        System.out.println("----------------------");
                    }
                    System.out.println("════════════════════════");

                    System.out.println("Enter product number to delete: ");
                    int deleteIndex = scanner.nextInt() - 1;

                    if(deleteIndex >= 0 && deleteIndex < index){
                        productNames[deleteIndex] = null;
                        productPrices[deleteIndex] = 0;
                        index--;

                        System.out.println("✅ Product deleted successfully!");
                    }else{
                        System.err.println("❌ Ivalid product number!");
                    }
                    break;

                case 4:
                    if(index == 0){
                        System.out.println("📦 No product to calculate!");
                        break;
                    }

                    double newTotalPrice = 0;
                    System.out.println("\n════════ CART ════════");
                    for (byte i = 0; i < index; i++) {
                        String displayCart = String.format("Id: %d \nName: %s \nPrice: %s", i+1, productNames[i], formatter.format(productPrices[i]));
                        System.out.println(displayCart);
                        newTotalPrice += productPrices[i];
                        System.out.println("----------------------");
                    }
                    if(newTotalPrice != totalPrice){
                        totalPrice = newTotalPrice;
                    }
                    System.out.println("Total Price: Rp."+formatter.format(totalPrice));
                    System.out.println("════════════════════════");
                    break;

                case 5:
                    if(totalPrice <= 100000){
                        System.err.println("❌ Minimum purchase of 100 thousand!");
                        break;
                    }

                    System.out.println("\n════════ CART ════════");
                    for (byte i = 0; i < index; i++) {
                        String displayCart = String.format("Id: %d \nName: %s \nPrice: %s", i+1, productNames[i], formatter.format(productPrices[i]));
                        System.out.println(displayCart);
                        System.out.println("----------------------");
                    }

                    discount = totalPrice * (discountPercentage / 100);
                    finalPrice = totalPrice - discount;

                    System.out.println(formatter.format(totalPrice));
                    System.out.println("Discount: " + discountPercentage + "%");
                    System.out.println("Total Price: " + formatter.format(finalPrice));
                    System.out.println("════════════════════════");
                    break;

                case 6:
                    if(totalPrice >= 100000){
                        discount = totalPrice * (discountPercentage / 100);
                        finalPrice = totalPrice - discount;
                    }

                    System.out.println("\n════════ CHECKOUT ════════");
                    for (int i = 0; i < index; i++) {
                        System.out.println((i+1) + ". " + productNames[i] + " - " + formatter.format(productPrices[i]));
                    }
                    System.out.println("------------------------------");
                    System.out.println("Total: " + formatter.format(totalPrice));
                    System.out.println("Discount: " + formatter.format(discount));
                    System.out.println("Final Price: " + formatter.format(finalPrice));
                    System.out.println("══════════════════════════");

                    System.out.println("Confirm checkout? (y/n)");
                    boolean confirm = scanner.next().equals("y");
                    if(confirm){
                        System.out.println("✅Checkout successful! Thank you for shopping.");
                        for(byte i = 0; i < index; i++){
                            productNames[i] = null;
                            productPrices[i] = 0;
                        }
                        index = 0;
                    }else {
                        System.out.println("Checkout cancelled.");
                    }
                    break;

                case 7:
                    if(index < 1){
                        System.err.println("📦Cart is empty!");
                        break;
                    }

                    for(int i = 0; i < index; i++){
                        productNames[i] = null;
                        productPrices[i] = 0;
                    }
                    index = 0;
                    System.out.println("✅Cart has been deleted.");
                    break;
                case 8:
                    System.out.println("\n👋 Thank you for using Shopping Cart!");
                    System.out.println("Good bye!");
                    running = false;
                    break;

                default:
                    System.err.println("❌ Invalid option! Please choose between 1-5.");
                    scanner.close();
                    break;
            }
        }
    }
}
