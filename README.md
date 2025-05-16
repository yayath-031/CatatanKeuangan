# 💰 Catatan Keuangan Pribadi

Aplikasi berbasis Java untuk membantu pengguna mencatat pemasukan dan pengeluaran harian, menampilkan laporan keuangan bulanan, serta menyimpan data secara lokal dalam format JSON.

---

## ✨ Fungsi Utama Aplikasi

Aplikasi ini dibuat untuk:
- Mencatat transaksi keuangan harian (pemasukan dan pengeluaran).
- Menampilkan laporan keuangan bulanan.
- Menyimpan dan memuat data transaksi secara lokal menggunakan file JSON.

---

## ⚙️ Fitur-Fitur

- ✅ Input transaksi pemasukan dan pengeluaran
- ✅ Penghitungan total saldo saat ini
- ✅ Laporan ringkasan bulanan
- ✅ Penyimpanan otomatis dalam file `data_transaksi.json`
- ✅ Validasi data transaksi

---

## 🔍 Implementasi OOP

Aplikasi ini dibangun menggunakan prinsip **Object-Oriented Programming (OOP)**:

| Konsep OOP        | Implementasi                                                                 |
|-------------------|------------------------------------------------------------------------------|
| **Encapsulation** | Kelas seperti `Transaksi`, `ManajerTransaksi`, dan `PenyimpananData` menyembunyikan detail implementasi dan hanya menampilkan metode yang relevan. |
| **Inheritance**   | `Pemasukan` dan `Pengeluaran` merupakan subclass dari `Transaksi`.           |
| **Polymorphism**  | `Transaksi` digunakan sebagai tipe umum untuk menangani `Pemasukan` dan `Pengeluaran` secara seragam. |
| **Interface**     | Interface `TransaksiInterface` mendefinisikan kontrak dasar yang harus diimplementasikan oleh kelas transaksi. |

---

## 👥 Pembagian Tugas Kelompok

| Anggota | Tugas Utama                                                                 |
|---------|------------------------------------------------------------------------------|
| **Anggota 1** | Pengembangan kelas `Transaksi`, `Pemasukan`, dan `Pengeluaran`         |
| **Anggota 2** | Implementasi logika `ManajerTransaksi`    |
| **Anggota 3** | Pembuatan laporan (`Laporan.java`) dan perhitungan ringkasan bulanan   |
| **Anggota 4** | Membuat semua transaksi tersimpan kedalam JSON `PenyimpananData`       |
| **Anggota 5** | Integrasi, pengujian akhir, dan perbaikan bug sebelum demo             |

---

## 🧩 Struktur Class

```bash
├── Main.java                # Entry point aplikasi
├── ManajerTransaksi.java   # Manajemen data transaksi (pemasukan/pengeluaran)
├── Pemasukan.java          # Representasi objek transaksi pemasukan
├── Pengeluaran.java        # Representasi objek transaksi pengeluaran
├── Laporan.java            # Menghasilkan ringkasan pemasukan dan pengeluaran
├── PenyimpananData.java    # Simpan dan muat data dari/ke file JSON
├── Transaksi.java          # Kelas induk umum untuk pemasukan dan pengeluaran
├── data_transaksi.json     # File penyimpanan data transaksi
└── README.md               # Dokumentasi proyek
