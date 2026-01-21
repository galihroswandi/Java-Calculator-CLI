package com.belajar.collectionframework.practice.inventorysystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager manager = new ProductManager();

        boolean running = true;

        while (running) {
            manager.checkAlertQuantity();

            System.out.println("\n==== INVENTORY SYSTEM ====");
            System.out.println("1. Add product");
            System.out.println("2. Show products");
            System.out.println("3. Update product");
            System.out.println("4. Remove Product");
            System.out.println("5. Search by id");
            System.out.println("6. Generate report inventory");
            System.out.println("4. Exit");
            System.out.print("Choose option (16): ");

            byte choice = scanner.nextByte();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String nameAdd = scanner.nextLine();

                    System.out.print("Enter product price: ");
                    double priceAdd = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter product quantity: ");
                    int qty = scanner.nextInt();
                    scanner.nextLine();

                    manager.addProduct(nameAdd, priceAdd, qty);
                    break;

                case 2:
                    manager.showProducts();
                    break;

                case 3:
                    System.out.print("Enter UUID: ");
                    String upUUID = scanner.nextLine();

                    System.out.print("Enter name: ");
                    String upName = scanner.nextLine();

                    System.out.print("Enter price: ");
                    double upPrice = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter quantity: ");
                    int upQty = scanner.nextInt();
                    scanner.nextLine();

                    manager.updateProduct(upUUID, upName, upPrice, upQty);
                    break;

                case 4:
                    System.out.print("Enter UUID: ");
                    String rmUUID = scanner.nextLine();

                    System.out.print("Are you sure ? (y/n)");
                    boolean confirmation = scanner.nextLine().contentEquals("y");

                    if (confirmation) {
                        manager.removeProduct(rmUUID);
                    }
                    break;

                case 5:
                    System.out.print("Enter UUID: ");
                    String schUUID = scanner.nextLine();

                    manager.searchById(schUUID);
                    break;

                case 6:
                    System.out.print("Are you sure generate report inventory? (y/n): ");
                    boolean report = scanner.nextLine().contentEquals("y");

                    if (report) {
                        manager.generateInventoryReport();
                    }
                    break;

                case 7:
                    running = false;
                    scanner.close();
                    System.err.println("Goodbye thank you for using inventory system, Have a good day 😊");
                    break;

                default:
                    running = false;
                    scanner.close();
                    System.err.println("Invalid option, please try again!");
                    break;
            }
        }
    }
}
