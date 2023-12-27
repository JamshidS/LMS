import java.util.Scanner;
public class kitapİadeİşlemi {
    private static String[] kitapIsimleri = {"no2", "no2", "no2", "no2", "no2"};
    private static int[] kitapMiktarlari = {1, 2, 3, 4, 5};
    public static String kitapİadeİşlemi() {
        Scanner scr = new Scanner(System.in);

        String[] kitapismi = {"no2", "no2", "no2", "no2", "no2"};
        System.out.println("İsminizi giriniz :");
        String name = scr.nextLine();

        System.out.println("Kitabın ismini giriniz :");
        String bookname = scr.nextLine();

        System.out.println("Kitabın  numarasını  giriniz :");
        int numarası = scr.nextInt();

        int kitapIndex = -1;
        for (int i = 0; i < kitapismi.length; i++) {
            if (kitapIsimleri[i].equalsIgnoreCase(bookname) && i + 1 == numarası) {
                kitapIndex = i;
                break;
            }
        }
        if (kitapIndex != -1 && kitapMiktarlari[kitapIndex] > 0) {
            kitapMiktarlari[kitapIndex]--;
            System.out.println("İşlem başarıyla tamamlandı. Kitap iade edildi.");
        } else {
            System.out.println("Kitap iade edilemedi. Belirtilen isim ve numarada kitap bulunamadı veya stokta yok.");
        }
        return kitapİadeİşlemi();
    }


    public static void main(String[] args) {
        kitapİadeİşlemi();
    }
}

