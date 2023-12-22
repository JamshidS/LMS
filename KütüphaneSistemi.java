import java.util.Scanner;

public class KütüphaneSistemi {


    private static int[] KİTAP = new int[100];

    private String[] kitabınİsimleri = new String[KİTAP.length];
    private String[] yazarınİsmi = new String[KİTAP.length];

    private int kitapSayisi = 0;

    private  void kitapEkleme(String yazar, String kitabınismi) {
        for (int i = 0; i < kitapSayisi; i++) {
            if (kitabınİsimleri[i].equalsIgnoreCase(kitabınismi) && yazarınİsmi[i].equalsIgnoreCase(yazar)) {
                System.out.println("Bu kitap daha önce  bulunmaktadır.");
                kitapSayisi++;
                System.out.println("Kitap sayısı güncellendi.Toplam kitap sayısı :" + kitapSayisi);
                return ;
            }
        }
        if (kitapSayisi < KİTAP.length) {
            kitabınİsimleri[kitapSayisi] = kitabınismi;
            yazarınİsmi[kitapSayisi] = yazar;
            kitapSayisi++;
            System.out.println("Kitabınız eklendi.");
        } else {
            System.out.println("Kitabınız eklenmedi, raf dolu.");

        }
    }
    private void toplamKitap() {
        System.out.println("Toplam kitap sayısı :" + kitapSayisi);
    }


    public static void main(String[] args) {

        KütüphaneSistemi kütüphaneSistemi = new KütüphaneSistemi();

        Scanner scan = new Scanner(System.in);
        System.out.println("*Kütüphanemize Hoş Geldiniz*\n");
        while (true) {
            System.out.println("\n Numaraya göre seçim kısmı : ");
            System.out.println("1. Kitap eklemek istiyorsanız. ");
            System.out.println("2. Kitap toplam sayısı için .");
            System.out.println("3. Çıkış .\n");

            System.out.print("Numarayı Giriniz :");
            int number = scan.nextInt();
            if (number == 3) {
                System.out.println("Çıkış. İiyi günler...");
                System.exit(0);
            } else {
                switch (number) {
                    case 1:
                        scan.nextLine();
                        System.out.print("Kitabın ismini giriniz: ");
                        String kitabınismi = scan.nextLine().toUpperCase();
                        System.out.print("Yazarı giriniz: ");
                        String yazar = scan.nextLine().toUpperCase();
                        System.out.println("kitabınız eklendi : " + kitabınismi);
                        System.out.println("Kitabın yazarı :" + yazar);
                        kütüphaneSistemi.kitapEkleme(yazar, kitabınismi);
                        break;
                    case 2:
                        kütüphaneSistemi.toplamKitap();
                        break;
                    case 3:
                        System.out.println("Sistemden ayrılıyorsunuz..");
                    default:
                        System.out.println("Hatalı giriş yapıldı .");
                }
            }

        }
    }

}



