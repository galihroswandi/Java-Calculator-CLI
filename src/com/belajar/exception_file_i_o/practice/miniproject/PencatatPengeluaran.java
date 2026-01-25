package com.belajar.exception_file_i_o.practice.miniproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class PencatatPengeluaran {
    private ArrayList<Expense> pengeluaran;
    private String namaFile;

    public PencatatPengeluaran(String namaFile) {
        this.pengeluaran = new ArrayList<>();
        this.namaFile = namaFile;
        muatDariFile();
    }

    // Tambah Pengeluaran
    public void tambahPengeluaran(String kategori, double jumlah, LocalDate tanggal, String deskripsi)
            throws IllegalArgumentException {
        // Validasi
        if (jumlah < 0) {
            throw new IllegalArgumentException("Jumlah harus posistif");
        }

        if (kategori == null || kategori.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori tidak boleh kosong");
        }

        if (deskripsi == null || deskripsi.trim().isEmpty()) {
            throw new IllegalArgumentException("Deskripsi tidak boleh kosong");
        }

        // Buat dan tambahkan expense
        Expense expense = new Expense(kategori, jumlah, tanggal, deskripsi);
        pengeluaran.add(expense);
        simpanKeFile();

        System.out.println("✅ Pengeluaran berhasil ditambahkan");
    }

    // Lihat semua pengeluaran
    public void lihatSemuaPengeluaran() {
        if (pengeluaran.isEmpty()) {
            System.out.println("\n🗒️ Belum ada pengeluaran yang dicatat!");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    SEMUA PENGELUARAN                               ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-4s %-15s %-12s %-14s %-20s ║\n",
                "#", "KATEGORI", "TANGGAL", "JUMLAH", "DESKRIPSI");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");

        for (int i = 0; i < pengeluaran.size(); i++) {
            System.out.printf("║ %-4d %s ║\n", (i + 1), pengeluaran.get(i).toString());
        }

        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
        System.out.printf("Total Pengeluaran: %d | Total Jumlah: Rp%.2f\n\n",
                pengeluaran.size(), hitungTotalJumlah());
    }

    // Filter berdasarkan kategori
    public void lihatBerdasarkanKategori(String kategori) {
        ArrayList<Expense> terfilter = new ArrayList<>();

        for (Expense expense : pengeluaran) {
            if (expense.getKategori().equalsIgnoreCase(kategori)) {
                terfilter.add(expense);
            }
        }

        if (terfilter.isEmpty()) {
            System.out.println("\n❌ Tidak ada pengeluaran untuk kategori: \" + kategori");
        }

        System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Kategori: " + kategori.toUpperCase());
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");

        double total = 0;
        for (int i = 0; i < terfilter.size(); i++) {
            System.out.printf("║ %-4d %s ║\\n" + (i + 1), terfilter.get(i).toString());
            total += terfilter.get(i).getJumlah();
        }

        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
        System.out.printf("Total untuk %s: Rp%.2f\n\n", kategori, total);
    }

    // Ringkasan kategori
    public void lihatRingkasanKategori() {
        if (pengeluaran.isEmpty()) {
            System.out.println("\n📋 Belum ada pengeluaran yang dicatat!");
            return;
        }

        HashMap<String, Double> totalKategori = new HashMap<>();

        // Hitung total per kategori
        for (Expense expense : pengeluaran) {
            String kategori = expense.getKategori();
            double totalSaatIni = totalKategori.getOrDefault(kategori, 0.0);
            totalKategori.put(kategori, totalSaatIni + expense.getJumlah());
        }

        // Tampilkan ringkasan
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║      RINGKASAN KATEGORI                   ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.printf("║ %-25s %-14s ║\n", "KATEGORI", "TOTAL");
        System.out.println("╠═══════════════════════════════════════════╣");

        double totalBesar = 0;
        for (HashMap.Entry<String, Double> entry : totalKategori.entrySet()) {
            System.out.printf("║ %-25s Rp%-13.2f ║\\n" + //
                    entry.getKey(), entry.getValue());
            totalBesar += entry.getValue();
        }

        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.printf("║ %-25s Rp%-13.2f ║\n", "TOTAL KESELURUHAN", totalBesar);
        System.out.println("╚═══════════════════════════════════════════╝\n");
    }

    // Laporan bulanan
    public void buatLaporanBulanan(int tahun, int bulan) {
        ArrayList<Expense> pengeluaranBulanan = new ArrayList<>();

        for (Expense expense : pengeluaran) {
            if (expense.getTanggal().getYear() == tahun && expense.getTanggal().getMonthValue() == bulan) {
                pengeluaranBulanan.add(expense);
            }
        }

        if (pengeluaranBulanan.isEmpty()) {
            System.out.println("❌ Tidak ada pengeluaran untuk bulan ini!");
            return;
        }

        String namaBulan = LocalDate.of(bulan, tahun, 1).getMonth().toString();

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║ LAPORAN BULANAN: " + namaBulan + " " + tahun);
        System.out.println("╠════════════════════════════════════════════════════════╣");

        HashMap<String, Double> totalKategori = new HashMap<>();
        double totalJumlah = 0;

        for (Expense expense : pengeluaranBulanan) {
            String kategori = expense.getKategori();
            double jumlah = expense.getJumlah();

            totalKategori.put(kategori, totalKategori.getOrDefault(kategori, 0.0) + jumlah);
            totalJumlah += jumlah;
        }

        System.out.printf("║ %-30s %-15s %-10s ║\n", "KATEGORI", "JUMLAH", "PERSENTASE");
        System.out.println("╠════════════════════════════════════════════════════════╣");

        for (HashMap.Entry<String, Double> entry : totalKategori.entrySet()) {
            double persentase = (entry.getValue() / totalJumlah) * 100;
            System.out.printf("║ %-30s Rp%-14.2f %-9.1f%% ║\n",
                    entry.getKey(), entry.getValue(), persentase);

        }

        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ TOTAL: Rp%-14.2f                              ║\n", totalJumlah);
        System.out.printf("║ Jumlah Pengeluaran: %-33d ║\n", pengeluaranBulanan.size());
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
    }

    // Hapus pengeluaran
    public void hapusPengeluaran(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= pengeluaran.size()) {
            throw new IndexOutOfBoundsException("Nomor pengeluaran tidak valid!");
        }

        pengeluaran.remove(index);
        simpanKeFile();
        System.out.println("✅ Pengeluaran berhasil dihapus!");
    }

    // Hitung total jumlah
    public double hitungTotalJumlah() {
        double total = 0;
        for (Expense expense : pengeluaran) {
            total += expense.getJumlah();
        }
        return total;
    }

    // Simpan ke file CSV
    public void simpanKeFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(namaFile))) {
            // Tulis header
            writer.println("Kategori,Tanggal,Jumlah,Deskripsi");

            // Tulis setiap expense
            for (Expense expense : pengeluaran) {
                writer.print(expense.toCSV());
            }
        } catch (IOException e) {
            System.out.println("❌ Error menyimpan ke file: " + e.getMessage());
        }
    }

    // Muat dari CSV
    public void muatDariFile() {
        File file = new File(namaFile);

        if (!file.exists()) {
            System.out.println("ℹ️ File tidak ditemukan. Mulai dari awal.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            boolean barisPertama = true;
            int terbaca = 0;
            int errorCount = 0;

            while ((baris = reader.readLine()) != null) {
                // Lewati header
                if (barisPertama) {
                    barisPertama = false;
                    continue;
                }

                // Lewati baris kosong
                if (baris.trim().isEmpty()) {
                    continue;
                }

                try {
                    Expense expense = Expense.fromCSV(baris);
                    pengeluaran.add(expense);
                    terbaca++;
                } catch (IllegalArgumentException e) {
                    System.out.println("⚠️ Melewati baris tidak valid: " + e.getMessage());
                    errorCount++;
                }
            }

            System.out.println("✅ Berhasil memuat " + terbaca + " pengeluaran dari file");
            if (errorCount > 0) {
                System.out.println("⚠️ " + errorCount + " baris tidak dapat dimuat");
            }
        } catch (FileNotFoundException e) {
            System.out.println("❌ File tidak ditemukan: " + namaFile);
        } catch (IOException e) {
            System.out.println("❌ Error memuat file: " + e.getMessage());
        }
    }

    // Dapatkan jumlah pengeluaran
    public int getJumlahPengeluaran() {
        return pengeluaran.size();
    }

    // Dapatkan semua kategori
    public ArrayList<String> getKategori() {
        ArrayList<String> kategori = new ArrayList<>();
        for (Expense expense : pengeluaran) {
            String kat = expense.getKategori();
            if (!kategori.contains(kat)) {
                kategori.add(kat);
            }
        }
        return kategori;
    }
}
