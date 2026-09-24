package prakpekan3;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Transaksi {
    // 1. Mengubah semua atribut menjadi private
    private String idTransaksi;
    private String jenis;
    private double nominal;

    private String formatSaldo(double nominal) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.GERMANY);
        DecimalFormat format = new DecimalFormat("#,##0.00", simbol);
        return format.format(nominal);
    }
    
    public Transaksi(String id, String jenis, double nominal) {
        this.idTransaksi = id;
        this.jenis = jenis;
        this.nominal = nominal;
    }

    // 2. Hanya menyediakan Getter (Read-Only)
    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getJenis() {
        return jenis;
    }

    public double getNominal() {
        return nominal;
    }

    public void cetakDetail() {
    	System.out.println("ID: " + idTransaksi + " | Jenis: " + jenis + " | Nominal: Rp" + formatSaldo(nominal));
    }
}