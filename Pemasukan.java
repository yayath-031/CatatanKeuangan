public class Pemasukan extends Transaksi {

    public Pemasukan(String tanggal, double jumlah, String kategori, String keterangan) {
        super(tanggal, jumlah, kategori, keterangan);
        if (jumlah < 0) {
            throw new IllegalArgumentException("Jumlah pemasukan tidak boleh negatif.");
        }
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("[PEMASUKAN] " + tanggal + " | " + kategori + " | Rp" + jumlah + " | " + keterangan);
    }
}
