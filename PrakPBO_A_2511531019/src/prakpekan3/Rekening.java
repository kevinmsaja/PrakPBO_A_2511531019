package prakpekan3;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import java.util.ArrayList;

public class Rekening {
    // 1. Mengunci atribut dengan 'private'
    private String nomorRekening;
    private String namaPemilik;
    private double saldo;
    private String pin; // Data sensitif!

    private ArrayList<Transaksi> riwayatTransaksi;
    
    private String formatSaldo(double nominal) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.GERMANY);
        DecimalFormat format = new DecimalFormat("#,##0.00", simbol);
        return format.format(nominal);
    }

    // 2. Modifikasi Constructor untuk menerima PIN awal
    public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
        this.nomorRekening = nomor;
        this.namaPemilik = nama;
        this.saldo = saldoAwal;

        // Validasi PIN di dalam Constructor
        if (pinAwal.length() == 6) {
            this.pin = pinAwal;
        } else {
            System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
            this.pin = "123456";
        }

        this.riwayatTransaksi = new ArrayList<>();
        System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat.");
    }

    // 3. Getter untuk atribut yang diizinkan dibaca publik
    public String getNomorRekening() { return nomorRekening; }
    public String getNamaPemilik() { return namaPemilik; }

    // 4. Method Otentikasi Internal (Validasi Enkapsulasi)
    public boolean otentikasi(String inputPin) {
        return this.pin.equals(inputPin);
    }
    
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
    
    public void cekInformasi() {
        System.out.println("--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
        System.out.println("---------------------");
    }
    
    public void cetakMutasi() {
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

    // ... (method setorTunai, tarikTunai, cekInformasi, cetakMutasi tetap dipertahankan seperti Modul 2)
}