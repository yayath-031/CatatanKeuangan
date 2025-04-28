import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ManajerTransaksi {
    List<Transaksi> daftarTransaksi;
    double saldo;

    // Constructor dengan parameter
    public ManajerTransaksi(List<Transaksi> daftarTransaksi, double saldo) {
        this.daftarTransaksi = daftarTransaksi;
        this.saldo = saldo;
    }

    // Constructor default
    public ManajerTransaksi() {
        this.daftarTransaksi = new ArrayList<>();
        this.saldo = 0.0;
    }

    public void tambahTransaksi(Transaksi transaksi) {
        if (transaksi.getJumlah() >= 0 || transaksi instanceof Pengeluaran) {  // Validasi jika pemasukan tidak negatif
            daftarTransaksi.add(transaksi);
            hitungSaldo();
        } else {
            System.out.println("Jumlah transaksi tidak valid!");
        }
    }

    public void hapusTransaksi(int indeks) {
        if (indeks >= 0 && indeks < daftarTransaksi.size()) {
            daftarTransaksi.remove(indeks);
            hitungSaldo();
        }
    }

    public void ubahTransaksi(int indeks, Transaksi transaksiBaru) {
        if (indeks >= 0 && indeks < daftarTransaksi.size()) {
            daftarTransaksi.set(indeks, transaksiBaru);
            hitungSaldo();
        }
    }

    public List<Transaksi> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    public List<Transaksi> filterBerdasarkanKategori(String kategori) {
        return daftarTransaksi.stream()
                              .filter(t -> t.getKategori().equalsIgnoreCase(kategori))  // Pastikan case-insensitive
                              .collect(Collectors.toList());
    }

    public List<Transaksi> filterBerdasarkanTanggal(String tanggal) {
        return daftarTransaksi.stream()
                              .filter(t -> t.getTanggal().equalsIgnoreCase(tanggal))  // Pastikan case-insensitive
                              .collect(Collectors.toList());
    }

    public List<Transaksi> filterBerdasarkanTipe(String tipe) {
        return daftarTransaksi.stream()
                              .filter(t -> (t instanceof Pemasukan && "Pemasukan".equalsIgnoreCase(tipe)) ||
                                           (t instanceof Pengeluaran && "Pengeluaran".equalsIgnoreCase(tipe)))
                              .collect(Collectors.toList());
    }

    public double getSaldo() {
        return saldo;
    }

    public void hitungSaldo() {
        saldo = daftarTransaksi.stream()
                               .mapToDouble(t -> t instanceof Pemasukan ? t.getJumlah() : -t.getJumlah())
                               .sum();
    }
}