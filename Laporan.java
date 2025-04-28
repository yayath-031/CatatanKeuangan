import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Laporan {
    private ManajerTransaksi manajerTransaksi;

    public Laporan(ManajerTransaksi manajerTransaksi, double totalPemasukan, double totalPengeluaran,
            Map<String, Double> kategoriSummary) {
        this.manajerTransaksi = manajerTransaksi;
        this.totalPemasukan = totalPemasukan;
        this.totalPengeluaran = totalPengeluaran;
        this.kategoriSummary = kategoriSummary;
    }

    public Laporan(ManajerTransaksi manajerTransaksi) {
        this.manajerTransaksi = manajerTransaksi;
    }

    public void generateLaporanBulanan(int bulan, int tahun) {
        List<Transaksi> semuaTransaksi = manajerTransaksi.getTransaksi();

        double totalPemasukan = 0;
        double totalPengeluaran = 0;
        Map<String, Double> kategoriSummary = new HashMap<>();

        for (Transaksi transaksi : semuaTransaksi) {
            // Asumsi format tanggal: "YYYY-MM-DD"
            String[] parts = transaksi.getTanggal().split("-");
            int tahunTransaksi = Integer.parseInt(parts[0]);
            int bulanTransaksi = Integer.parseInt(parts[1]);

            if (tahunTransaksi == tahun && bulanTransaksi == bulan) {
                if (transaksi instanceof Pemasukan) {
                    totalPemasukan += transaksi.getJumlah();
                } else if (transaksi instanceof Pengeluaran) {
                    totalPengeluaran += transaksi.getJumlah();
                }

                // Rangkuman per kategori
                kategoriSummary.put(
                        transaksi.getKategori(),
                        kategoriSummary.getOrDefault(transaksi.getKategori(), 0.0) + transaksi.getJumlah());
            }
        }

        // Simpan hasil di variabel agar bisa ditampilkan
        this.totalPemasukan = totalPemasukan;
        this.totalPengeluaran = totalPengeluaran;
        this.kategoriSummary = kategoriSummary;
    }

    private double totalPemasukan;
    private double totalPengeluaran;
    private Map<String, Double> kategoriSummary;

    public void tampilkanLaporan() {
        System.out.println("===== LAPORAN BULANAN =====");
        System.out.printf("Total Pemasukan : Rp %.2f\n", totalPemasukan);
        System.out.printf("Total Pengeluaran : Rp %.2f\n", totalPengeluaran);
        System.out.printf("Saldo Akhir : Rp %.2f\n", manajerTransaksi.getSaldo());
        System.out.println("\nRangkuman per Kategori:");

        System.out.println("----------------------------");
        for (Map.Entry<String, Double> entry : kategoriSummary.entrySet()) {
            System.out.printf("%-20s : Rp %.2f\n", entry.getKey(), entry.getValue());
        }
        System.out.println("----------------------------");
    }
}
