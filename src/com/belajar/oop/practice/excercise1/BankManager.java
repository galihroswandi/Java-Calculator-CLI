package com.belajar.oop.practice.excercise1;

import java.text.NumberFormat;
import java.util.Locale;

public class BankManager {
    private BankAccountSystem[] accounts;
    private int count;
    private NumberFormat formatter;

    public BankManager(int capacity) {
        accounts = new BankAccountSystem[capacity];
        count = 0;

        Locale locale = Locale.of("id", "ID");
        formatter = NumberFormat.getCurrencyInstance(locale);
    }

    public void registration(String holderName) {
        if (holderName.trim().isEmpty()) {
            System.out.println("❌ Holder name cannot be empty!");
            return;
        }

        int generatedAccountNumber = Math.abs((int) (Math.random() * 1000000000));

        accounts[count] = new BankAccountSystem(String.valueOf(generatedAccountNumber), holderName, 0);
        count++;
        System.out.println("✅ Account created successfully!");

        for (int i = 0; i < count; i++) {
            System.out.println(accounts[i].getHolderName() + " - " + accounts[i].getAccountNumber());
        }
    }

    public void deposit(String holderName, String accountNumber, double amount) {
        if (!basicMiddleware(holderName, accountNumber)) {
            return;
        }

        if (amount <= 0) {
            System.out.println("❌ Amount must be greater than 0!");
            return;
        }

        for (int i = 0; i < count; i++) {
            if (accounts[i].getHolderName().equals(holderName)
                    && accounts[i].getAccountNumber().equals(accountNumber)) {
                accounts[i].setBalance(amount);
                System.out.println("✅ Deposit successful!");
                System.out.println("Balance: " + formatter.format(accounts[i].getBalance()));
                return;
            }
        }
    }

    public void withdraw(String holderName, String accountNumber, double amount) {
        if (!basicMiddleware(holderName, accountNumber)) {
            return;
        }

        for (byte i = 0; i < count; i++) {
            if (accounts[i].getHolderName().equals(holderName)
                    && accounts[i].getAccountNumber().equals(accountNumber)) {
                if (accounts[i].getBalance() < amount) {
                    System.out.println("❌ Not enough balance!");
                    return;
                } else {
                    accounts[i].setBalance(accounts[i].getBalance() - amount);
                    System.out.println("✅ Withdraw successful!");
                    System.out.println("Balance: " + formatter.format(accounts[i].getBalance()));
                    return;
                }
            }
        }
    }

    public void transfer(String holderName, String accountNumber, String receiveAccountNumber, double amount) {
        if (!basicMiddleware(holderName, accountNumber)) {
            return;
        }

        if (amount <= 0) {
            System.out.println("❌ Amount must be greater than 0!");
            return;
        }

        int receiveAccount = -1;
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountNumber().equals(receiveAccountNumber)) {
                receiveAccount = i;
                break;
            }
        }

        for (int i = 0; i < count; i++) {
            if (accounts[i].getHolderName().equals(holderName)
                    && accounts[i].getAccountNumber().equals(accountNumber)) {

                if (accounts[i].getBalance() < amount) {
                    System.out.println("❌ Not enough balance!");
                    return;
                } else {
                    if (receiveAccount == -1) {
                        System.out.println("❌ Receiver account not found!");
                        return;
                    }

                    accounts[i].setBalance(accounts[i].getBalance() - amount);
                    accounts[receiveAccount].setBalance(accounts[receiveAccount].getBalance() +
                            amount);

                    System.out.println("✅ Transfer successful!");
                    System.out.println("Your balance: " + formatter.format(accounts[i].getBalance()));
                    return;
                }
            }
        }
    }

    public void displayAccountInfo(String holderName, String accountNumber) {
        if (!basicMiddleware(holderName, accountNumber)) {
            return;
        }

        for (int i = 0; i < count; i++) {
            if (accounts[i].getHolderName().equals(holderName)
                    && accounts[i].getAccountNumber().equals(accountNumber)) {

                System.out.println("\n=== Account Information ===");
                System.out.println("Account Number: " + accounts[i].getAccountNumber());
                System.out.println("Holder Name: " + accounts[i].getHolderName());
                System.out.println("Balance: " + formatter.format(accounts[i].getBalance()));
                return;
            }
        }
    }

    private boolean basicMiddleware(String holderName, String accountNumber) {
        if (holderName == null || holderName.trim().isEmpty()) {
            System.out.println("❌ Holder name cannot be empty!");
            return false;
        }

        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            System.out.println("❌ Account number cannot be empty!");
            return false;
        }

        for (int i = 0; i < count; i++) {
            if (accounts[i].getHolderName().equals(holderName)
                    && accounts[i].getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }

        System.out.println("❌ Account not found!");
        return false;
    }
}
