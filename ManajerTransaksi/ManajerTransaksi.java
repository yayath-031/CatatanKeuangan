import java.util.*;
import java.util.stream.Collectors;

public class ManajerTransaksi {
    List<Transaksi> transaksiList;
    double saldo;

    public ManajerTransaksi(List<Transaksi> transaksiList, double saldo) {
        this.transaksiList = transaksiList;
        this.saldo = saldo;
    }

    public void tambahTransaksi(Transaksi t) {
        if (t.getJumlah() >= 0 || t instanceof Pengeluaran) {  // Validasi jika pemasukan tidak negatif
            transaksiList.add(t);
            hitungSaldo();
        } else {
            System.out.println("Jumlah transaksi tidak valid!");
        }
    }

    public void hapusTransaksi(int index) {
        if (index >= 0 && index < transaksiList.size()) {
            transaksiList.remove(index);
            hitungSaldo();
        }
    }

    public void editTransaksi(int index, Transaksi baru) {
        if (index >= 0 && index < transaksiList.size()) {
            transaksiList.set(index, baru);
            hitungSaldo();
        }
    }

    public List<Transaksi> getTransaksi() {
        return transaksiList;
    }

    public List<Transaksi> filterByKategori(String kategori) {
        return transaksiList.stream()
                             .filter(t -> t.getKategori().equalsIgnoreCase(kategori))  // Pastikan case-insensitive
                             .collect(Collectors.toList());
    }

    public List<Transaksi> filterByTanggal(String tanggal) {
        return transaksiList.stream()
                             .filter(t -> t.getTanggal().equalsIgnoreCase(tanggal))  // Pastikan case-insensitive
                             .collect(Collectors.toList());
    }

    public List<Transaksi> filterByType(String tipe) {
        return transaksiList.stream()
                             .filter(t -> (t instanceof Pemasukan && "Pemasukan".equalsIgnoreCase(tipe)) ||
                                          (t instanceof Pengeluaran && "Pengeluaran".equalsIgnoreCase(tipe)))
                             .collect(Collectors.toList());
    }

    public double getSaldo() {
        return saldo;
    }

    public void hitungSaldo() {
        saldo = transaksiList.stream()
                             .mapToDouble(t -> t instanceof Pemasukan ? t.getJumlah() : -t.getJumlah())
                             .sum();
    }
}



// Kode masih error, menunggu kelas di anggota lain
