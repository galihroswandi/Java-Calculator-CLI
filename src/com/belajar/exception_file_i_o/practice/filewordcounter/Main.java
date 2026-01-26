package com.belajar.exception_file_i_o.practice.filewordcounter;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path TXT_DATA = DATA_DIR.resolve("file_word_count.txt");

    public static void main(String[] args) {
        try {
            if (!Files.exists(TXT_DATA)) {
                throw new FileNotFoundException("File yang anda buka tidak ditemukan!");
            }

            List<String> contents = Files.readAllLines(TXT_DATA);
            long wordCount = 0;
            long lineCount = (byte) contents.size();
            long charCount = 0;

            for (String line : contents) {
                charCount += line.length();

                String trimedLine = line.trim();
                if (trimedLine.isEmpty())
                    continue;

                String[] words = line.split("\\+s");
                wordCount += words.length;
            }

            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║          STATISTIK                    ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.printf("║ Total Kata: %-25d ║\n", wordCount);
            System.out.printf("║ Total Baris: %-24d ║\n", lineCount);
            System.out.printf("║ Total Karakter: %-21d ║\n", charCount);
            System.out.println("╚═══════════════════════════════════════╝\n");

        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Sesuatu tidak terduga terjadi: " + e.getMessage());
        }
    }
}
