package com.learnbygemini.day11modernio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ExpenseManagerNio {
    // Gunakan Path, bukan File (Point 2)
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path CSV_FILE = DATA_DIR.resolve("expenses.csv"); // data/expenses.csv

    // Point 6: Setup folder otomatis
    public void init() {
        try {
            if (!Files.exists(DATA_DIR)) {
                Files.createDirectories(DATA_DIR);
                System.out.println("Folder data dibuat.");
            }
        } catch (IOException e) {
            System.err.println("Critical: Gagal membuat folder database.");
        }
    }

    public void saveExpense(String category, String amountStr, String date) {
        // Point 7 & 4: Validation & Option
        try {
            // Validasi input (Sanitization)
            if (category.contains(","))
                throw new IllegalArgumentException("Kategori dilarang mengandung koma");
            double amount = Double.parseDouble(amountStr);

            String csvLine = category + "," + amount + "," + date + System.lineSeparator();

            // POint 8: Atomic operation
            // Files.writeString menjamin data ditulis utuh atau tidak sama sekali
            // Amana dari corrupt data jika crash di tengah penulisan
            Files.writeString(CSV_FILE, csvLine, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (NumberFormatException e) {
            System.err.println("Input Error: Amount harus angka! (" + amountStr + ")");
        } catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Disk Error: Gagal menulis ke file.");
        }
    }

    public void readAllExpenses() {
        // Point 5: Memory warning (hanya untuk file kecil/menengah)
        try {
            if (!Files.exists(CSV_FILE)) {
                System.out.println("Belum ada data.");
                return;
            }

            // Point 3: One-linear read (UTF-8 Default)
            List<String> lines = Files.readAllLines(CSV_FILE);

            System.out.println("--- DAFTAR PENGELUARAN ---");
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty())
                    continue; // skip baris kosong

                String[] parts = line.split(",");

                // Point 7: Defensive check
                if (parts.length != 3) {
                    System.err.println("Corrupt data at line " + (i + 1) + ": " + line);
                    continue;
                }

                System.out.println((i + 1) + ". " + parts[0] + " | Rp " + parts[1] + " | " + parts[2]);
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca database.");
        }
    }
}