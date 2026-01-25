package com.belajar.exception_file_i_o.practice.agevalidator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Input age: ");
            int age = scanner.nextInt();

            if (age < 0 || age > 150) {
                scanner.close();
                throw new InvalidAgeException(age);
            }

            System.out.println("Age is valid: " + age);
        } catch (InvalidAgeException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Umur yang anda masukan: " + e.getAge());
        } catch (InputMismatchException e) {
            System.err.println("Error: Input harus berupa angka integer.");
        } catch (Exception e) {
            System.err.println("Terjadi error yang tidak diketahui: " + e.getMessage());
        }
    }
}
