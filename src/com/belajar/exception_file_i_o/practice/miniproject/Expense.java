package com.belajar.exception_file_i_o.practice.miniproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {
    private String kategori;
    private double jumlah;
    private LocalDate tanggal;
    private String deskripsi;

    public Expense(String kategori, double jumlah, LocalDate tanggal, String deskripsi) {
        this.kategori = kategori;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
    }

    // Getter
    public String getKategori() {
        return kategori;
    }

    public double getJumlah() {
        return jumlah;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    // Setter
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    // Konversi ke format CSV: Kategori,Tanggal,Jumlah,Deskripsi
    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("%s,%s,%.2f,%s", kategori, tanggal.format(formatter), jumlah, deskripsi.replace(",", ";"));
    }

    // Paste dari baris CSV
    public static Expense fromCSV(String lineCSV) throws IllegalArgumentException {
        String[] bagian = lineCSV.split(",", 4);

        if (bagian.length != 4) {
            throw new IllegalArgumentException("Format CSV tidak valid: " + lineCSV);
        }

        try {
            String kategori = bagian[0].trim();
            LocalDate tanggal = LocalDate.parse(bagian[1].trim());
            double jumlah = Double.parseDouble(bagian[2].trim());
            String deskripsi = bagian[3].trim();

            return new Expense(kategori, jumlah, tanggal, deskripsi);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing CSV: " + e.getMessage());
        }
    }

    // Format tampilan
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("%-15s %-12s Rp%-10.2f %s", kategori, tanggal.format(formatter), jumlah, deskripsi);
    }
}
