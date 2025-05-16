import java.time.LocalDate;

public class Pemasukan extends Transaksi {
    public Pemasukan(LocalDate tanggal, double jumlah, String kategori, String keterangan) {
        super(tanggal, jumlah, kategori, keterangan);
    }

    public void tampilkanDetail() {
        System.out.println("[PEMASUKAN]");
        System.out.println("Tanggal   : " + getTanggal());
        System.out.println("Jumlah    : Rp" + getJumlah());
        System.out.println("Kategori  : " + getKategori());
        if (getKeterangan() != null && !getKeterangan().isEmpty()) {
            System.out.println("Keterangan: " + getKeterangan());
        }
        System.out.println("----------------------------");
    }
}
