import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Laporan {
    private ManajerTransaksi manajerTransaksi;
    private double totalPemasukan;
    private double totalPengeluaran;
    private Map<String, Double> kategoriSummary;

    // Constructor lengkap
    public Laporan(ManajerTransaksi manajerTransaksi, double totalPemasukan, double totalPengeluaran,
            Map<String, Double> kategoriSummary) {
        this.manajerTransaksi = manajerTransaksi;
        this.totalPemasukan = totalPemasukan;
        this.totalPengeluaran = totalPengeluaran;
        this.kategoriSummary = kategoriSummary;
    }

    // Constructor sederhana
    public Laporan(ManajerTransaksi manajerTransaksi) {
        this.manajerTransaksi = manajerTransaksi;
        this.totalPemasukan = 0.0;
        this.totalPengeluaran = 0.0;
        this.kategoriSummary = new HashMap<>();
    }

    public void generateLaporanBulanan(int bulan, int tahun) {
        List<Transaksi> semuaTransaksi = manajerTransaksi.getDaftarTransaksi();

        // Reset nilai sebelum generate laporan baru
        totalPemasukan = 0.0;
        totalPengeluaran = 0.0;
        kategoriSummary.clear();

        for (Transaksi transaksi : semuaTransaksi) {
            int tahunTransaksi = transaksi.getTanggal().getYear();
            int bulanTransaksi = transaksi.getTanggal().getMonthValue();

            if (tahunTransaksi == tahun && bulanTransaksi == bulan) {
                if (transaksi instanceof Pemasukan) {
                    totalPemasukan += transaksi.getJumlah();
                } else if (transaksi instanceof Pengeluaran) {
                    totalPengeluaran += transaksi.getJumlah();
                }

                kategoriSummary.put(
                        transaksi.getKategori(),
                        kategoriSummary.getOrDefault(transaksi.getKategori(), 0.0) + transaksi.getJumlah());
            }
        }
    }

    public void tampilkanLaporan() {
        System.out.println("\n===== LAPORAN BULANAN =====");
        System.out.printf("Total Pemasukan  : Rp %.2f\n", totalPemasukan);
        System.out.printf("Total Pengeluaran: Rp %.2f\n", totalPengeluaran);
        System.out.printf("Saldo Akhir      : Rp %.2f\n", manajerTransaksi.getSaldo());
        System.out.println("\nRangkuman per Kategori:");
        System.out.println("----------------------------");
        for (Map.Entry<String, Double> entry : kategoriSummary.entrySet()) {
            System.out.printf("%-20s : Rp %.2f\n", entry.getKey(), entry.getValue());
        }
        System.out.println("----------------------------\n");
    }

    // Getter tambahan jika nanti dibutuhkan
    public double getTotalPemasukan() {
        return totalPemasukan;
    }

    public double getTotalPengeluaran() {
        return totalPengeluaran;
    }

    public Map<String, Double> getKategoriSummary() {
        return kategoriSummary;
    }
}