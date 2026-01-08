package com.belajar.java;

import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputExample {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        ZonedDateTime wakeupWIB = ZonedDateTime.now(ZoneId.of("Asia/Jakarta"));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String waktuString = wakeupWIB.format(dateFormatter) + " "+ wakeupWIB.format(timeFormatter);
        String isContinue = "yes";

        System.out.println("=====CALCULATOR CLI=====");

        while (isContinue.equals("yes")) {
            System.out.println("Enter first number:");
            double number1 = scanner.nextDouble();

            System.out.println("Enter a operator(+,-,*,/,%)");
            char operator = scanner.next().charAt(0);

            if (operator != '+' && operator != '-' && operator != '*' && operator != '/' && operator != '%') {
                throw new RuntimeException("Operator doesn't exist");
            }

            System.out.println("Enter second number:");
            double number2 = scanner.nextDouble();

            if (operator == '/' && number2 == 0) {
                throw new RuntimeException("Error: cannot divide by zero!");
            }

            double result = switch (operator) {
                case '+' -> number1 + number2;
                case '-' -> number1 - number2;
                case '*' -> number1 * number2;
                case '/' -> number1 / number2;
                case '%' -> number1 % number2;

                default -> 0;
            };

            System.out.printf("Result %.2f %c %.2f = %.2f\n", number1, operator, number2, result);

            // Writing in history file
            try {
                FileWriter writer = new FileWriter("output.txt", true);

                String res = String.format("Result %.2f %c %.2f = %.2f\n", number1, operator, number2, result);
                writer.write(waktuString + " | ");
                writer.write(res+"\n");

                writer.close();
            }catch (IOException e){
                System.err.println("Error whe writing file: "+e.getMessage());
            }

            System.out.println("\nContinue?(yes/no):");
            isContinue = scanner.next();
            System.out.println();
        }

        System.out.println("Thank you for using Calculator CLI!");
        scanner.close();
    }
}
