package com.belajar.exception_file_i_o.practice.expensetracker;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static PencatatPengeluaran pencatat = new PencatatPengeluaran("pengeluaran.csv");

    public static void main(String[] args) {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║  SELAMAT DATANG DI PENCATAT          ║");
        System.out.println("║       PENGELUARAN                     ║");
        System.out.println("╚═══════════════════════════════════════╝\n");

        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenu();

            try {
                int pilihan = getInputInt("Pilih opsi (1-8): ");
                scanner.nextLine(); // Clear buffer

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
                        System.out.println("Sampai jumpa!\n");
                        break;
                    default:
                        System.out.println("❌ Pilihan tidak valid! Silakan pilih 1-8.");
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Input tidak valid! Masukkan angka.");
                scanner.nextLine(); // Clear input tidak valid
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
            System.out.print("Masukkan kategori: ");
            String kategori = scanner.nextLine().trim();

            double jumlah = getInputDouble("Masukkan jumlah: Rp");
            scanner.nextLine(); // Clear buffer

            System.out.print("Masukkan tanggal (YYYY-MM-DD) atau Enter untuk hari ini: ");
            String strTanggal = scanner.nextLine().trim();
            LocalDate tanggal;

            if (strTanggal.isEmpty()) {
                tanggal = LocalDate.now();
                System.out.println("Menggunakan tanggal hari ini: " + tanggal);
            } else {
                try {
                    tanggal = LocalDate.parse(strTanggal);
                } catch (DateTimeParseException e) {
                    System.out.println("❌ Format tanggal tidak valid! Menggunakan tanggal hari ini.");
                    tanggal = LocalDate.now();
                }
            }

            System.out.print("Masukkan deskripsi: ");
            String deskripsi = scanner.nextLine().trim();

            pencatat.tambahPengeluaran(kategori, jumlah, tanggal, deskripsi);

        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error menambah pengeluaran: " + e.getMessage());
        }
    }

    private static void lihatBerdasarkanKategori() {
        // Tampilkan kategori yang tersedia
        ArrayList<String> kategori = pencatat.getCategory();

        if (kategori.isEmpty()) {
            System.out.println("\n❌ Tidak ada kategori yang ditemukan!");
            return;
        }

        System.out.println("\nKategori yang tersedia:");
        for (int i = 0; i < kategori.size(); i++) {
            System.out.println((i + 1) + ". " + kategori.get(i));
        }

        System.out.print("\nMasukkan nama kategori: ");
        String kat = scanner.nextLine().trim();

        pencatat.lihatBerdasarkanKategori(kat);
    }

    private static void buatLaporanBulanan() {
        try {
            int tahun = getInputInt("Masukkan tahun (contoh: 2024): ");
            int bulan = getInputInt("Masukkan bulan (1-12): ");

            if (bulan < 1 || bulan > 12) {
                System.out.println("❌ Bulan tidak valid! Harus 1-12.");
                return;
            }

            pencatat.buatLaporanBulanan(tahun, bulan);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void hapusPengeluaran() {
        if (pencatat.getJumlahPengeluaran() == 0) {
            System.out.println("\n❌ Tidak ada pengeluaran untuk dihapus!");
            return;
        }

        pencatat.lihatSemuaPengeluaran();

        try {
            int nomorPengeluaran = getInputInt("Masukkan nomor pengeluaran yang akan dihapus (0 untuk batal): ");

            if (nomorPengeluaran == 0) {
                System.out.println("❌ Penghapusan dibatalkan.");
                return;
            }

            scanner.nextLine(); // Clear buffer
            System.out.print("Apakah Anda yakin? (ya/tidak): ");
            String konfirmasi = scanner.nextLine().trim();

            if (konfirmasi.equalsIgnoreCase("ya")) {
                pencatat.hapusPengeluaran(nomorPengeluaran - 1);
            } else {
                System.out.println("❌ Penghapusan dibatalkan.");
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void tampilkanStatistik() {
        if (pencatat.getJumlahPengeluaran() == 0) {
            System.out.println("\n❌ Belum ada pengeluaran yang dicatat!");
            return;
        }

        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║          STATISTIK                    ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.printf("║ Total Pengeluaran: %-18d ║\n", pencatat.getJumlahPengeluaran());
        System.out.printf("║ Total Jumlah: Rp%-20.2f ║\n", pencatat.hitungTotalJumlah());

        double rataRata = pencatat.hitungTotalJumlah() / pencatat.getJumlahPengeluaran();
        System.out.printf("║ Rata-rata per Pengeluaran: Rp%-12.2f ║\n", rataRata);

        ArrayList<String> kategori = pencatat.getCategory();
        System.out.printf("║ Jumlah Kategori: %-20d ║\n", kategori.size());

        System.out.println("╚═══════════════════════════════════════╝\n");
    }

    // Method pembantu untuk mendapatkan input integer dengan validasi
    private static int getInputInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Mohon masukkan angka yang valid!");
                scanner.nextLine(); // Clear input tidak valid
            }
        }
    }

    // Method pembantu untuk mendapatkan input double dengan validasi
    private static double getInputDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double nilai = scanner.nextDouble();
                if (nilai <= 0) {
                    System.out.println("❌ Jumlah harus positif!");
                    continue;
                }
                return nilai;
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Mohon masukkan angka yang valid!");
                scanner.nextLine(); // Clear input tidak valid
            }
        }
    }
}