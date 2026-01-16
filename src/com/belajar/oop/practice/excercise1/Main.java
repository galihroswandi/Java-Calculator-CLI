package com.belajar.oop.practice.excercise1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BankManager manager = new BankManager(100);

        boolean running = true;

        while (running) {
            System.out.println("\n╔═══════════════════════════════════╗");
            System.out.println("║ BANK ACCOUNT SYSTEM               ║");
            System.out.println("╚═══════════════════════════════════╝");
            System.out.println("1. Register");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Display account info");
            System.out.println("6. Exit");
            System.out.print("Choose option (1-2): ");

            int choice = scan.nextInt();
            scan.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.println("\n=== REGISTER NEW ACCOUNT ===");
                    System.out.print("Enter holder name: ");
                    String holderName = scan.nextLine();

                    manager.registration(holderName);
                    break;

                case 2:
                    System.out.println("\n=== DEPOSIT MONEY ===");
                    System.out.print("Enter holder name: ");
                    holderName = scan.nextLine();

                    System.out.print("Enter account number: ");
                    String accountNumber = scan.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = scan.nextDouble();

                    manager.deposit(holderName, accountNumber, amount);
                    break;

                case 3:
                    System.out.println("\n=== WITHDRAW MONEY ===");
                    System.out.print("Enter holder name: ");
                    holderName = scan.nextLine();

                    System.out.print("Enter account number: ");
                    accountNumber = scan.nextLine();

                    System.out.print("Enter amount: ");
                    double withdrawAmount = scan.nextDouble();

                    manager.withdraw(holderName, accountNumber, withdrawAmount);
                    break;

                case 4:
                    System.out.println("\n=== TRANSFER MONEY ===");
                    System.out.print("Enter holder name: ");
                    holderName = scan.nextLine();

                    System.out.print("Enter account number: ");
                    accountNumber = scan.nextLine();

                    System.out.print("Enter amount: ");
                    double transferAmount = scan.nextDouble();
                    scan.nextLine();

                    System.out.print("Receive account number: ");
                    String receiveAccountNumber = scan.nextLine();

                    manager.transfer(holderName, accountNumber, receiveAccountNumber, transferAmount);
                    break;

                case 5:
                    System.out.println("\n=== DISPLAY ACCOUNT INFORMATION ===");
                    System.out.print("Enter holder name: ");
                    holderName = scan.nextLine();

                    System.out.print("Enter account number: ");
                    accountNumber = scan.nextLine();

                    manager.displayAccountInfo(holderName, accountNumber);
                    break;

                case 6:
                    System.out.println("Thank you for using our service!");
                    running = false;
                    scan.close();
                    break;

                default:
                    System.err.println("❌ Invalid option!");
                    running = false;
                    scan.close();
                    break;
            }
        }
    }
}
