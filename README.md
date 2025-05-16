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
| **Fathir Syahban** | Pengembangan kelas `Transaksi`, `Pemasukan`, dan `Pengeluaran`         |
| **Muhammad Hairi** | Implementasi logika `ManajerTransaksi`    |
| **Akmal** | Pembuatan laporan (`Laporan.java`) dan perhitungan ringkasan bulanan   |
| **Aditya Hisbullah** | Membuat semua transaksi tersimpan kedalam JSON `PenyimpananData`       |
| **Akhmad Hidayat** | Integrasi, pengujian akhir, dan perbaikan bug sebelum demo             |

---

## 🧩 Struktur Class

```bash
## 🧱 Struktur Class

```bash
├── main
│   └── Main.java
│       - Menyediakan menu utama untuk menjalankan aplikasi.
│       - Mengatur navigasi antar fitur: tambah transaksi, lihat transaksi, laporan, simpan/muat data.
│
├── Transaksi.java
│   │   - Class induk yang mewakili transaksi umum dengan atribut:
│   │     → LocalDate tanggal
│   │     → double jumlah
│   │     → String kategori
│   │     → String keterangan
│   │   - Method:
│   │     → tampilkanDetail() [abstract]
│   │
│   ├── Pemasukan.java
│   │   - Subclass dari Transaksi untuk mencatat pemasukan.
│   │   - Override tampilkanDetail() untuk mencetak data pemasukan.
│   │
│   └── Pengeluaran.java
│       - Subclass dari Transaksi untuk mencatat pengeluaran.
│       - Override tampilkanDetail() untuk mencetak data pengeluaran.
│
├── ManajerTransaksi.java
│      - Mengelola daftar transaksi:
│        → tambahTransaksi()
│        → tampilkanSemuaTransaksi()
│        → getTotalPemasukan(), getTotalPengeluaran()
│   
├── Laporan.java
│       - Menampilkan ringkasan laporan keuangan:
│       → total pemasukan
│       → total pengeluaran
│       → saldo akhir
│
├── PenyimpananData.java
│       - Menyimpan dan membaca transaksi dari file JSON.
│       - Method:
│         → simpanTransaksi()
│         → muatTransaksi()
│
├── data_transaksi.json
│   - File penyimpanan permanen semua transaksi dalam format JSON.
