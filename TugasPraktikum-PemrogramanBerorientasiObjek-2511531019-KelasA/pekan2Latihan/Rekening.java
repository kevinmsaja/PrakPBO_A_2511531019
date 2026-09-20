package pekan2Latihan;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.ArrayList;
public class Rekening {
    String nomorRekening;
    String namaPemilik;
    double saldo;

    // Menyimpan seluruh riwayat transaksi rekening
    ArrayList<Transaksi> riwayatTransaksi;
    
    // Method untuk membuat format angka menjadi 50.000,00
    private String formatSaldo(double nominal) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.GERMANY);
        DecimalFormat format = new DecimalFormat("#,##0.00", simbol);
        return format.format(nominal);
    }
    
    // Constructor
    public Rekening(String nomor, String nama, double saldoAwal) {
        nomorRekening = nomor;
        namaPemilik = nama;
        saldo = saldoAwal;

        // Membuat ArrayList untuk menyimpan transaksi
        riwayatTransaksi = new ArrayList<>();

        System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + formatSaldo(saldo));
    }

    // METHOD SETOR TUNAI
    public void setorTunai(double nominal) {
        if (nominal > 0) {
            // Menambahkan nominal ke saldo
            saldo += nominal;
            System.out.println("Setor tunai Rp" + formatSaldo(nominal)+ " berhasil.");
            System.out.println("Saldo saat ini: Rp" + formatSaldo(saldo));

            // Membuat ID transaksi
            String idTrx = "TRX-S-" + System.currentTimeMillis();
            // Membuat objek Transaksi
            Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
            // Menyimpan transaksi ke riwayat
            riwayatTransaksi.add(trxBaru);
        } else {
            System.out.println("Gagal: Nominal setor harus lebih dari 0!");
        }
    }

    // METHOD TARIK TUNAI
    public void tarikTunai(double nominal) {
        // Memeriksa apakah nominal valid
        if (nominal < 10000) {
        	System.out.println("Transaksi Gagal : Minimal nominal penarikan 10.000");
        } else if (nominal > saldo) {  // Memeriksa apakah saldo mencukupi
        	System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp"+ formatSaldo(saldo));
        } else {
            // Mengurangi saldo
            saldo -= nominal;
            System.out.println("Tarik tunai Rp" + formatSaldo(nominal) + " berhasil.");
            System.out.println("Saldo saat ini: Rp" + formatSaldo(saldo));

            // Membuat ID transaksi
            String idTrx = "TRX-T-" + System.currentTimeMillis();
            // Membuat objek Transaksi
            Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
            // Menyimpan transaksi ke riwayat
            riwayatTransaksi.add(trxBaru);
        }
    }

    // METHOD CEK INFORMASI
    public void cekInformasi() {
        System.out.println("--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + formatSaldo(saldo));
        System.out.println("---------------------");
    }

    // METHOD MENAMPILKAN RIWAYAT TRANSAKSI
    public void tampilkanRiwayatTransaksi() {
        System.out.println("\n=== RIWAYAT TRANSAKSI ===");
        if (riwayatTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            for (Transaksi transaksi : riwayatTransaksi) {
                transaksi.cetakDetail();
            }
        }
        System.out.println("=========================");
    }
}
