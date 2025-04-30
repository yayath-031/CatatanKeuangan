public class Pengeluaran extends Transaksi {

    public Pengeluaran(String tanggal, double jumlah, String kategori, String keterangan) {
        super(tanggal, jumlah, kategori, keterangan);
        if (jumlah < 0) {
            throw new IllegalArgumentException("Jumlah pengeluaran tidak boleh negatif.");
        }
        this.jumlah = -Math.abs(jumlah); // Pastikan jumlah menjadi negatif
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("[PENGELUARAN] " + tanggal + " | " + kategori + " | Rp" + jumlah + " | " + keterangan);
    }
}
