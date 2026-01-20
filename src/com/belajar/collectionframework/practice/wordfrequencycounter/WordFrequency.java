package com.belajar.collectionframework.practice.wordfrequencycounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class WordFrequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==== WORD FREQUENCY COUNTER ====\n");
        System.out.print("Enter a text or paragraph: ");
        ArrayList<String> words = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));

        HashMap<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 1);
            }
        }

        System.out.println("\n╔════════════════════════╗");
        System.out.println("║ WORD FREQUENCY COUNTER ║");
        System.out.println("╠════════════════════════╣");
        System.out.printf("║ %-10s %-5s \n", "STRING", "COUNT");
        System.out.println("╠════════════════════════╣");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.printf("║ %-10s %-5d \n", entry.getKey(), entry.getValue());
        }
        System.out.println("╚════════════════════════╝");

        scanner.close();
    }
}