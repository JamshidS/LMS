import java.util.Scanner;
public class Arama {
    public static void searchBooks(Scanner scanner) {
        String[] kitapismi = {"Sefiller", "Hayvan Çiftliği"};
        String[] yazar = {"Victor Hugo", "George Orwell"};
        int[] isbn = {1, 2};
        int[] sayfa1 = {1724, 152};

        int secim = scanner.nextInt();
        scanner.nextLine();

        switch (secim) {
            case 1:
                System.out.print("Başlık girin: ");
                String baslikArama = scanner.nextLine();
                for (int i = 0; i < kitapismi.length; i++) {
                    if (kitapismi[i].equalsIgnoreCase(baslikArama)) {
                        System.out.println("Kitap Bulundu:");
                        System.out.println("Başlık: " + kitapismi[i]);
                        System.out.println("Yazar: " + yazar[i]);
                        System.out.println("ISBN: " + isbn[i]);
                        System.out.println("Sayfa Sayısı: " + sayfa1[i]);
                        return;
                    }
                }
                System.out.println("Kitap bulunamadı.");
                break;
            case 2:
                System.out.print("Yazar adı girin: ");
                String yazarArama = scanner.nextLine();
                for (int i = 0; i < yazar.length; i++) {
                    if (yazar[i].equalsIgnoreCase(yazarArama)) {
                        System.out.println("Kitap Bulundu:");
                        System.out.println("Başlık: " + kitapismi[i]);
                        System.out.println("Yazar: " + yazar[i]);
                        System.out.println("ISBN: " + isbn[i]);
                        System.out.println("Sayfa Sayısı: " + sayfa1[i]);
                        return;
                    }
                }
                System.out.println("Kitap bulunamadı.");
                break;

            case 3:
                System.out.print("ISBN girin: ");
                int isbnArama = scanner.nextInt();
                for (int i = 0; i < isbn.length; i++) {
                    if (isbn[i] == isbnArama) {
                        System.out.println("Kitap Bulundu:");
                        System.out.println("Başlık: " + kitapismi[i]);
                        System.out.println("Yazar: " + yazar[i]);
                        System.out.println("ISBN: " + isbn[i]);
                        System.out.println("Sayfa Sayısı: " + sayfa1[i]);
                        return;
                    }
                }
                System.out.println("Kitap bulunamadı.");
                break;

            default:
                System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
                break;
        }
    }

        
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Arama yapmak için aşağıdaki seçeneklerden birini seçin:");
        System.out.println("1. Başlık ile arama");
        System.out.println("2. Yazar ile arama");
        System.out.println("3. ISBN ile arama");



        searchBooks(scr);
    }
}

