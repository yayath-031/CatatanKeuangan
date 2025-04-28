import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManajerTransaksi {
    private List<Transaksi> daftarTransaksi;
    private double saldo;

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
        if (transaksi instanceof Pemasukan && transaksi.getJumlah() < 0) {
            System.out.println("Jumlah pemasukan tidak boleh negatif!");
            return;
        }
        daftarTransaksi.add(transaksi);
        hitungSaldo();
    }

    public void hapusTransaksi(int indeks) {
        if (indeks >= 0 && indeks < daftarTransaksi.size()) {
            daftarTransaksi.remove(indeks);
            hitungSaldo();
        } else {
            System.out.println("Indeks tidak valid!");
        }
    }

    public void ubahTransaksi(int indeks, Transaksi transaksiBaru) {
        if (indeks >= 0 && indeks < daftarTransaksi.size()) {
            daftarTransaksi.set(indeks, transaksiBaru);
            hitungSaldo();
        } else {
            System.out.println("Indeks tidak valid!");
        }
    }

    public List<Transaksi> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    public List<Transaksi> filterBerdasarkanKategori(String kategori) {
        return daftarTransaksi.stream()
                              .filter(t -> t.getKategori().equalsIgnoreCase(kategori))
                              .collect(Collectors.toList());
    }

    public List<Transaksi> filterBerdasarkanTanggal(LocalDate tanggal) {
        return daftarTransaksi.stream()
                              .filter(t -> t.getTanggal().equals(tanggal))
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

    private void hitungSaldo() {
        saldo = daftarTransaksi.stream()
                               .mapToDouble(t -> t instanceof Pemasukan ? t.getJumlah() : -t.getJumlah())
                               .sum();
    }
}