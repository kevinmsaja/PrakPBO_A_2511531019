package prakpekan3;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static int inputInteger(Scanner input, String pesan) {
        while (true) {
            System.out.print(pesan);
            if (input.hasNextInt()) {
                int angka = input.nextInt();
                input.nextLine();
                return angka;
            } else {
                System.out.println("Input tidak valid! Masukkan angka.");
                input.nextLine();
            }
        }
    }

    public static double inputDouble(Scanner input, String pesan) {
        while (true) {
            System.out.print(pesan);
            if (input.hasNextDouble()) {
                double angka = input.nextDouble();
                input.nextLine();
                return angka;
            } else {
                System.out.println("Input tidak valid! Masukkan angka.");
                input.nextLine();
            }
        }
    }

    public static String inputPin(Scanner input, String pesan) {
        while (true) {
            System.out.print(pesan);
            String pin = input.nextLine();
            // PIN harus terdiri dari 6 karakter
            // dan semuanya harus berupa angka
            if (pin.matches("\\d{6}")) {
                return pin;
            } else {
                System.out.println("PIN tidak valid! PIN harus terdiri dari 6 digit angka.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Rekening> daftarRekening = new ArrayList<>();
        Rekening akunAktif = null;
        boolean isRunning = true;
        System.out.println("=== SISTEM PERBANKAN MINI ===");
        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun");
            System.out.println("6. Cetak Mutasi/Riwayat");
            System.out.println("0. Keluar");
            int pilihan = inputInteger(input,"Pilih menu: ");
            switch (pilihan) {
                case 1:
                    System.out.println("\n=== BUKA REKENING BARU ===");
                    System.out.print("Masukkan No Rekening: ");
                    String no = input.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = input.nextLine();
                    double saldoAwal;
                    while (true) {
                        saldoAwal = inputDouble(input,"Masukkan Saldo Awal "+ "(minimal Rp50.000): ");
                        if (saldoAwal >= 50000) {
                            break;
                        }
                        System.out.println("Gagal: Saldo awal minimal " + "Rp50.000,00!");
                    }
                    String pinAwal = inputPin(input,"Masukkan PIN (6 digit): ");
                    Rekening rekeningBaru = new Rekening(no,nama,saldoAwal,pinAwal);
                    daftarRekening.add(rekeningBaru);
                    akunAktif = rekeningBaru;
                    System.out.println("Rekening berhasil ditambahkan " + "ke dalam daftar.");
                    break;

                case 2:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum memiliki " + "rekening aktif!");
                    } else {
                        System.out.println("\n=== SETOR TUNAI ===");
                        double setor = inputDouble(input, "Masukkan nominal setor: Rp");
                        akunAktif.setorTunai(setor);
                    }
                    break;

                case 3:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum memiliki " + "rekening aktif!");
                    } else {
                        System.out.println("\n=== TARIK TUNAI ===");
                        String pinInput = inputPin(input, "Masukkan PIN: ");
                        if (akunAktif.otentikasi(pinInput)) {
                            System.out.println("PIN benar.");
                            double tarik = inputDouble(input,"Masukkan nominal penarikan, " + "minimal Rp10.000,00: Rp");
                            akunAktif.tarikTunai(tarik);
                        } else {
                            System.out.println("Akses Ditolak: PIN yang " + "Anda masukkan salah!");
                        }
                    }
                    break;

                case 4:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;

                case 5:
                    if (daftarRekening.isEmpty()) {
                        System.out.println("Belum ada rekening yang terdaftar.");
                    } else {
                        System.out.println("\n=== DAFTAR REKENING ===");
                        // Menampilkan semua rekening
                        for (Rekening rekening : daftarRekening) {
                            System.out.println("No. Rekening : " + rekening.getNomorRekening() + " | Nama : " + rekening.getNamaPemilik());
                        }
                        System.out.print("\nMasukkan nomor rekening "+ "yang ingin diaktifkan: ");
                        String nomorCari = input.nextLine();
                        Rekening rekeningDitemukan = null;
                        for (Rekening rekening : daftarRekening) {
                            if (rekening.getNomorRekening().equals(nomorCari)) {
                                rekeningDitemukan = rekening;
                                break;
                            }
                        }
                        if (rekeningDitemukan != null) {
                            akunAktif = rekeningDitemukan;
                            System.out.println("Berhasil mengganti "  + "akun aktif.");
                            System.out.println("Akun aktif: " + akunAktif.getNamaPemilik() + " - " + akunAktif.getNomorRekening());
                        } else {
                            System.out.println("Gagal: Nomor rekening " + "tidak ditemukan!");
                        }
                    }
                    break;

                case 6:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka " + "rekening!");
                    } else {
                        System.out.println("\n=== CETAK MUTASI ===");
                        String pinInput = inputPin(input, "Masukkan PIN: ");
                        if (akunAktif.otentikasi(pinInput)) {
                            System.out.println("PIN benar.");
                            akunAktif.cetakMutasi();
                        } else {
                            System.out.println("Akses Ditolak: PIN yang " + "Anda masukkan salah!");
                        }
                    }
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Sistem ditutup. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        input.close();
    }
}