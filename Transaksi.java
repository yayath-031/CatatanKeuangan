public abstract class Transaksi {
    protected java.time.LocalDate tanggal;
    protected double jumlah;
    protected String kategori;
    protected String keterangan;

    public Transaksi(java.time.LocalDate tanggal, double jumlah, String kategori, String keterangan) {
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.kategori = kategori;
        this.keterangan = keterangan;
    }
    public java.time.LocalDate getTanggal() {
        return tanggal;
    }

    public double getJumlah() {
        return jumlah;
    }

    public String getKategori() {
        return kategori;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
