public abstract class Transaksi {
    protected String tanggal;
    protected double jumlah;
    protected String kategori;
    protected String keterangan;

    public Transaksi(String tanggal, double jumlah, String kategori, String keterangan) {
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.kategori = kategori;
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void editDetail(String tanggal, double jumlah, String kategori, String keterangan) {
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.kategori = kategori;
        this.keterangan = keterangan;
    }

    public abstract void tampilkanDetail();
}
