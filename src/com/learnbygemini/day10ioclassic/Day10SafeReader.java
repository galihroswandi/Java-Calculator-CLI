package com.learnbygemini.day10ioclassic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day10SafeReader {
    public static void main(String[] args) {
        String filePath = "data/user_data.txt";

        // Point 6: Try-with-resources (Auto-close)
        // Point 4 & 7: Nesting streams & Explicit UTF-8
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            String line;
            int lineNumber = 1;

            // Point 8: Loop Pattern
            while ((line = reader.readLine()) != null) {
                System.out.println("Line " + lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            // Point 10: Specific handling
            System.err.println("File tidak ditemukan! Cek path: " + filePath);
        } catch (IOException e) {
            System.err.println("Gagal membaca file: " + e.getMessage());
        }
    }
}
