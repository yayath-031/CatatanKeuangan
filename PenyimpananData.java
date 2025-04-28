import java.io.*;
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
                writer.write("    \"kategori\": \"" + t.getKategori() + "\",\n");
                writer.write("    \"keterangan\": \"" + t.getKeterangan() + "\"\n");
                writer.write("  }");

                if (i < transaksiList.size() - 1) {
                    writer.write(","); // Tambahkan koma kalau bukan item terakhir
                }
                writer.write("\n");
            }
            writer.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Muat transaksi dari file JSON manual
    public static List<Transaksi> muatDariJSON(String namaFile) {
        List<Transaksi> transaksiList = new java.util.ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line.trim());
            }
            String json = jsonBuilder.toString();

            // Manual parsing sederhana (tanpa library)
            json = json.substring(1, json.length() - 1); // Buang [ dan ]
            String[] entries = json.split("\\},\\{");

            for (String entry : entries) {
                entry = entry.replace("{", "").replace("}", "");

                String[] fields = entry.split(",");
                String tipe = "";
                String tanggal = "";
                double jumlah = 0;
                String kategori = "";
                String keterangan = "";

                for (String field : fields) {
                    String[] keyValue = field.split(":");
                    String key = keyValue[0].trim().replace("\"", "");
                    String value = keyValue[1].trim().replace("\"", "");

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

                if (tipe.equals("Pemasukan")) {
                    transaksiList.add(new Pemasukan(tanggal, jumlah, kategori, keterangan));
                } else if (tipe.equals("Pengeluaran")) {
                    transaksiList.add(new Pengeluaran(tanggal, jumlah, kategori, keterangan));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return transaksiList;
    }
}
