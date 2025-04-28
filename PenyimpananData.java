import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PenyimpananData {

    // Simpan transaksi ke JSON manual
    public static void simpanKeJSON(String namaFile, List<Transaksi> transaksiList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            writer.write("[\n");
            for (int i = 0; i < transaksiList.size(); i++) {
                Transaksi t = transaksiList.get(i);
                writer.write("  {\n");
                writer.write("    \"tipe\": \"" + (t instanceof Pemasukan ? "Pemasukan" : "Pengeluaran") + "\",\n");
                writer.write("    \"tanggal\": \"" + t.getTanggal() + "\",\n");
                writer.write("    \"jumlah\": " + t.getJumlah() + ",\n");
                writer.write("    \"kategori\": \"" + escapeString(t.getKategori()) + "\",\n");
                writer.write("    \"keterangan\": \"" + escapeString(t.getKeterangan()) + "\"\n");
                writer.write("  }");
                if (i < transaksiList.size() - 1) {
                    writer.write(",\n");
                }
            }
            writer.write("\n]");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file JSON: " + e.getMessage());
        }
    }

    // Muat transaksi dari file JSON manual
    public static List<Transaksi> muatDariJSON(String namaFile) {
        List<Transaksi> transaksiList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line.trim());
            }

            String json = jsonBuilder.toString();
            if (json.isEmpty()) return transaksiList; // Jika file kosong

            json = json.substring(1, json.length() - 1); // Buang [ dan ]
            String[] entries = json.split("\\},\\{");

            for (String entry : entries) {
                entry = entry.replace("{", "").replace("}", "");

                String[] fields = entry.split("\",\"");
                String tipe = "", tanggal = "", kategori = "", keterangan = "";
                double jumlah = 0;

                for (String field : fields) {
                    field = field.replace("\"", ""); // Buang semua "
                    String[] keyValue = field.split(":", 2);
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    switch (key) {
                        case "tipe":
                            tipe = value;
                            break;
                        case "tanggal":
                            tanggal = value;
                            break;
                        case "jumlah":
                            jumlah = Double.parseDouble(value);
                            break;
                        case "kategori":
                            kategori = value;
                            break;
                        case "keterangan":
                            keterangan = value;
                            break;
                    }
                }

                LocalDate tanggalParsed = LocalDate.parse(tanggal);

                if (tipe.equals("Pemasukan")) {
                    transaksiList.add(new Pemasukan(tanggalParsed, jumlah, kategori, keterangan));
                } else if (tipe.equals("Pengeluaran")) {
                    transaksiList.add(new Pengeluaran(tanggalParsed, jumlah, kategori, keterangan));
                }
            }

        } catch (IOException e) {
            System.out.println("Gagal membaca file JSON: " + e.getMessage());
        }

        return transaksiList;
    }

    // Fungsi tambahan untuk escape karakter khusus dalam string JSON
    private static String escapeString(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}