import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManajerTransaksi manajerTransaksi = new ManajerTransaksi();
        Laporan laporan = new Laporan(manajerTransaksi);

        String namaFile = "data_transaksi.json";

        // Muat data dari file JSON kalau ada
        List<Transaksi> dataSebelumnya = PenyimpananData.muatDariJSON(namaFile);
        for (Transaksi t : dataSebelumnya) {
            manajerTransaksi.tambahTransaksi(t);
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=== MENU CATATAN KEUANGAN ===");
            System.out.println("1. Tambah Pemasukan");
            System.out.println("2. Tambah Pengeluaran");
            System.out.println("3. Lihat Semua Transaksi");
            System.out.println("4. Buat Laporan Bulanan");
            System.out.println("5. Simpan Data");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu (1-6): ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahTransaksi(scanner, manajerTransaksi, true);
                    break;
                case 2:
                    tambahTransaksi(scanner, manajerTransaksi, false);
                    break;
                case 3:
                    tampilkanSemuaTransaksi(manajerTransaksi);
                    break;
                case 4:
                    System.out.print("Masukkan bulan (1-12): ");
                    int bulan = scanner.nextInt();
                    System.out.print("Masukkan tahun (contoh 2025): ");
                    int tahun = scanner.nextInt();
                    scanner.nextLine(); 
                    laporan.generateLaporanBulanan(bulan, tahun);
                    laporan.tampilkanLaporan();
                    break;
                case 5:
                    PenyimpananData.simpanKeJSON(namaFile, manajerTransaksi.getDaftarTransaksi());
                    System.out.println("Data berhasil disimpan ke " + namaFile);
                    break;
                case 6:
                    System.out.println("Terima kasih! Sampai jumpa!");
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi!");
            }
        }

        scanner.close();
    }

    private static void tambahTransaksi(Scanner scanner, ManajerTransaksi manajerTransaksi, boolean isPemasukan) {
        System.out.print("Tanggal (format YYYY-MM-DD): ");
        String tanggal = scanner.nextLine();
        System.out.print("Jumlah: ");
        double jumlah = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Kategori: ");
        String kategori = scanner.nextLine();
        System.out.print("Keterangan: ");
        String keterangan = scanner.nextLine();

        Transaksi transaksi;
        if (isPemasukan) {
            transaksi = new Pemasukan(java.time.LocalDate.parse(tanggal), jumlah, kategori, keterangan);
        } else {
            transaksi = new Pengeluaran(java.time.LocalDate.parse(tanggal), jumlah, kategori, keterangan);
        }
        manajerTransaksi.tambahTransaksi(transaksi);

        System.out.println("Transaksi berhasil ditambahkan!");
    }

    private static void tampilkanSemuaTransaksi(ManajerTransaksi manajerTransaksi) {
        System.out.println("\n=== DAFTAR SEMUA TRANSAKSI ===");
        for (Transaksi t : manajerTransaksi.getDaftarTransaksi()) {
            System.out.println(t.getTanggal() + " - " + (t instanceof Pemasukan ? "[Pemasukan]" : "[Pengeluaran]") +
                    " Rp " + t.getJumlah() + " (" + t.getKategori() + ") - " + t.getKeterangan());
        }
        System.out.println("Saldo saat ini: Rp " + manajerTransaksi.getSaldo());
    }
}
