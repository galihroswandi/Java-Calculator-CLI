package com.belajar.exception_file_i_o.practice.miniproject;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static PencatatPengeluaran pencatat = new PencatatPengeluaran("pengeluaran.csv");

    public static void main(String[] args) {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║   SELAMAT DATANG DI PENCATAT          ║");
        System.out.println("║       PENGELUARAN                     ║");
        System.out.println("╚═══════════════════════════════════════╝");

        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenu();

            try {
                int pilihan = getInputInt("Pilih opsi (1-8): ");
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        tambahPengeluaran();
                        break;

                    case 2:
                        pencatat.lihatSemuaPengeluaran();
                        break;

                    case 3:
                        lihatBerdasarkanKategori();
                        break;

                    case 4:
                        pencatat.lihatRingkasanKategori();
                        break;

                    case 5:
                        buatLaporanBulanan();
                        break;

                    case 6:
                        hapusPengeluaran();
                        break;

                    case 7:
                        tampilkanStatistik();
                        break;

                    case 8:
                        berjalan = false;
                        System.out.println("\n💾 Semua data tersimpan di pengeluaran.csv");
                        System.out.println("👋 Terima kasih telah menggunakan Pencatat Pengeluaran!");
                        System.out.println("Sampai Jumpa!\n");
                        break;

                    default:
                        System.out.println("❌ Pilihan tidak valid, Silahkan pilih (1-8)");

                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Input tidak valid! Masukan angka.");
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║    MENU PENCATAT PENGELUARAN          ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║ 1. Tambah Pengeluaran                 ║");
        System.out.println("║ 2. Lihat Semua Pengeluaran            ║");
        System.out.println("║ 3. Lihat Berdasarkan Kategori         ║");
        System.out.println("║ 4. Ringkasan Kategori                 ║");
        System.out.println("║ 5. Laporan Bulanan                    ║");
        System.out.println("║ 6. Hapus Pengeluaran                  ║");
        System.out.println("║ 7. Statistik                          ║");
        System.out.println("║ 8. Keluar                             ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }

    private static void tambahPengeluaran() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║    TAMBAH PENGELUARAN BARU            ║");
        System.out.println("╚═══════════════════════════════════════╝");

        try {
            System.out.println("\nKategori umum: Makanan, Transport, Belanja, Tagihan, Hiburan, Kesehatan, Lainnya");
            System.out.print("Masukan kategori: ");
            String kategori = scanner.nextLine().trim();

            double jumlah = getInputDouble("Masukan jumla: Rp");
            scanner.nextLine();

            System.out.println("Masukan tanggal (YYYY-MM-DD) atau Enter untuk hari ini: ");
            String strTanggal = scanner.nextLine().trim();
            LocalDate tanggal;

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
