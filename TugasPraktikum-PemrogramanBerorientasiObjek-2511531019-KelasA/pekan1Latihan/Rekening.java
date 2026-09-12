package pekan1Latihan;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Rekening {
	String nomorRekening;
    String namaPemilik;
    double saldo;

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
        System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp"+ formatSaldo(saldo));
    }

    // Method setor tunai
    public void setorTunai(double nominal) {
        if (nominal > 0) {
            saldo += nominal;
            System.out.println("Setor tunai Rp" + formatSaldo(nominal)+ " berhasil.");
            System.out.println("Saldo saat ini: Rp" + formatSaldo(saldo));
        } else {
            System.out.println("Gagal: Nominal setor harus lebih dari 0!");
        }
    }

    // Method tarik tunai
    public void tarikTunai(double nominal) {
        // Memastikan minimal penarikan adalah Rp10.000
        if (nominal < 10000) {
            System.out.println("Transaksi Gagal : Minimal nominal penarikan 10.000");
        } else if (nominal > saldo) {   // Memastikan saldo mencukupi
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp"+ formatSaldo(saldo));
        } else {   // Jika semua syarat terpenuhi
            saldo -= nominal;
            System.out.println("Tarik tunai Rp" + formatSaldo(nominal) + " berhasil.");
            System.out.println("Saldo saat ini: Rp" + formatSaldo(saldo));
        }
    }

    // Method cek informasi rekening
    public void cekInformasi() {
        System.out.println("\n--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + formatSaldo(saldo));
        System.out.println("---------------------");
    }
}