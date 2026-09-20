package pekan2Latihan;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Transaksi {
    String idTransaksi;
    String jenis;
    double nominal;

    private String formatSaldo(double nominal) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.GERMANY);
        DecimalFormat format = new DecimalFormat("#,##0.00", simbol);
        return format.format(nominal);
    }
    
    // Constructor
    public Transaksi(String id, String jenis, double nominal) {
        this.idTransaksi = id;
        this.jenis = jenis;
        this.nominal = nominal;
    }

    // Method untuk mencetak detail transaksi
    public void cetakDetail() {
        System.out.println("ID: " + idTransaksi + " | Jenis: " + jenis + " | Nominal: Rp" + formatSaldo(nominal));
    }
}
