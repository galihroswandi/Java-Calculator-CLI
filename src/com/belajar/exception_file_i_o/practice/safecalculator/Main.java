//// My Code
// package com.belajar.exception_file_i_o.practice.safecalculator;

// import java.util.Scanner;

// public class Main {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         boolean running = true;

//         while (running) {
//             System.out.println("==== SAFE CALCULATOR ====");

//             System.out.print("Input firt number: ");
//             String num1 = scanner.nextLine();

//             System.out.print("Operator (+, -, *, /): ");
//             String operator = scanner.nextLine();

//             System.out.print("Input second number: ");
//             String num2 = scanner.nextLine();

//             switch (operator) {
//                 case "+":
//                     int convertedNum1 = convertToInt(num1);
//                     int convertedNum2 = convertToInt(num2);

//                     if (convertedNum1 != -1 && convertedNum2 != -1) {
//                         int result = convertedNum1 + convertedNum2;
//                         System.out.println("Hasil: " + result);
//                     }
//                     break;

//                 case "-":
//                     convertedNum1 = convertToInt(num1);
//                     convertedNum2 = convertToInt(num2);

//                     if (convertedNum1 != -1 && convertedNum2 != -1) {
//                         int result = convertedNum1 - convertedNum2;
//                         System.out.println("Hasil: " + result);
//                     }
//                     break;

//                 case "*":
//                     convertedNum1 = convertToInt(num1);
//                     convertedNum2 = convertToInt(num2);

//                     if (convertedNum1 != -1 && convertedNum2 != -1) {
//                         int result = convertedNum1 * convertedNum2;
//                         System.out.println("Hasil: " + result);
//                     }
//                     break;

//                 case "/":
//                     double num1Double = convertToDouble(num1);
//                     double num2Double = convertToDouble(num2);

//                     if (num1Double != -1 && num2Double != -1) {
//                         double result = validatePembagianNol(num1Double, num2Double);
//                         if (result != -1) {
//                             System.out.println("Hasil: " + result);
//                         }
//                     }
//                     break;

//                 default:
//                     System.out.println("Operator tidak valid.");
//                     System.out.println("Silahkan coba lagi.");
//                     continue;
//             }

//             System.out.print("Apakah Anda ingin melanjutkan? (y/n): ");
//             String input = scanner.nextLine();

//             if (input.equalsIgnoreCase("n")) {
//                 running = false;
//                 scanner.close();
//             }
//         }
//     }

//     public static int convertToInt(String input) {
//         try {
//             return Integer.parseInt(input);
//         } catch (NumberFormatException e) {
//             System.err.println("Input harus berupa angka: " + e.getMessage());
//             return -1;
//         }
//     }

//     public static double convertToDouble(String input) {
//         try {
//             return Double.parseDouble(input);
//         } catch (NumberFormatException e) {
//             System.err.println("Input harus berupa angka: " + e.getMessage());
//             return -1;
//         }
//     }

//     public static double validatePembagianNol(double num1, double num2) {
//         try {
//             if (num2 == 0 || num1 == 0) {
//                 throw new IllegalArgumentException("Pembagian dengan nol tidak dapat dilakukan");
//             }

//             return num1 / num2;
//         } catch (IllegalArgumentException e) {
//             System.err.println("Pembagian dengan nol tidak dapat dilakukan: " + e.getMessage());
//             return -1;
//         }
//     }
// }

package com.belajar.exception_file_i_o.practice.safecalculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                System.out.println("==== SAFE CALCULATOR ====");

                System.out.print("Input first number: ");
                // Langsung parse ke double, jika gagal akan ditangkap oleh catch
                double num1 = Double.parseDouble(scanner.nextLine());

                System.out.print("Operator (+, -, *, /): ");
                String operator = scanner.nextLine();

                System.out.print("Input second number: ");
                // Langsung parse ke double, jika gagal akan ditangkap oleh catch
                double num2 = Double.parseDouble(scanner.nextLine());

                double result = 0;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        // Pengecekan pembagian dengan nol
                        if (num2 == 0) {
                            // Lemparkan exception yang sesuai
                            throw new ArithmeticException("Error: Pembagian dengan nol tidak diizinkan.");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        // Lemparkan exception untuk operator tidak valid
                        throw new IllegalArgumentException("Operator tidak valid. Gunakan +, -, *, /");
                }

                System.out.println("Hasil: " + result);

            } catch (NumberFormatException e) {
                // Menangkap error jika input bukan angka
                System.err.println("Error: Input harus berupa angka yang valid. Silakan coba lagi.");
            } catch (ArithmeticException e) {
                // Menangkap error pembagian dengan nol
                System.err.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                // Menangkap error operator tidak valid
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                // Menangkap semua error tak terduga lainnya
                System.err.println("Terjadi error tak terduga: " + e.getMessage());
            }

            // Bagian ini akan selalu berjalan meskipun ada error
            System.out.print("\nApakah Anda ingin melanjutkan? (y/n): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("n")) {
                running = false;
            }
            System.out.println(); // Memberi baris baru untuk tampilan lebih rapi
        }

        scanner.close();
        System.out.println("Terima kasih telah menggunakan kalkulator!");
    }
}
