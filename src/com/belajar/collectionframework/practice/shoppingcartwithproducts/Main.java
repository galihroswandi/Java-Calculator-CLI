package com.belajar.collectionframework.practice.shoppingcartwithproducts;

import java.util.Scanner;

public class Main {
        static void main() {
        ProductManager pmInstance = new ProductManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while(running){
            System.out.println("\n╔═════════════════════╗");
            System.out.println("║ SHOPPING CART       ║");
            System.out.println("╚═════════════════════╝");
            System.out.println("1. Add product to cart");
            System.out.println("2. Update quantity");
            System.out.println("3. Remove product");
            System.out.println("4. Calculate total");
            System.out.println("5. Apply discount code");
            System.out.print("Choose option (1-5): ");
            byte choice = scanner.nextByte();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.print("Enter product id: ");
                    int productIdToCart = scanner.nextInt();
                    scanner.nextLine();

                    pmInstance.addProduct("Product 1", 5000d, 5);
                    pmInstance.addChart(productIdToCart);
                    break;

                case 2:
                    System.out.print("Enter product id: ");
                    int qtyProductId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("New quantity: ");
                    int newQty = scanner.nextInt();
                    scanner.nextLine();

                    pmInstance.updateQty(qtyProductId, newQty);
                    break;

                case 3:
                    System.out.print("Enter product id: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Are you sure? (y/n)");
                    boolean isConfirm = scanner.nextLine().equals("y");

                    if(isConfirm){
                        pmInstance.removeCart(deleteId);
                    }
                    break;

                case 4:
                    System.out.println("==== Calculate total ====");
                    pmInstance.calculateTotal();
                    break;

                default:
                    System.err.println("Invalid choice!");
                    running = false;
                    scanner.close();
                    break;
            }
        }
    }
}
