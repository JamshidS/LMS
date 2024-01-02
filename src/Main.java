import java.util.Scanner;
public class Main {
    static int INDEX = 20;
    static String[][] books = new String[INDEX][4];
    static String[][] patrons = new String[INDEX][4];
    static String[][] transactions = new String[INDEX][3];
    static int bookQuantity = 0;

    public static void addBook(String title, String author, String bookPage, String ISBN) {
        if (bookQuantity < books.length) {
            books[bookQuantity][0] = title;
            books[bookQuantity][1] = author;
            books[bookQuantity][2] = bookPage;
            books[bookQuantity][3] = ISBN;
            bookQuantity++;
        } else {
            String[][] newBookList = new String[books.length + 1][4];
            for (int i = 0; i < books.length; i++) {
                for (int j = 0; j < 4; j++) {
                    newBookList[i][j] = books[i][j];
                }
            }
            newBookList[bookQuantity][0] = title;
            newBookList[bookQuantity][1] = author;
            newBookList[bookQuantity][2] = bookPage;
            newBookList[bookQuantity][3] = ISBN;

            bookQuantity++;
            books = newBookList;


        }
    }

    public static void main(String[] args) {

    }

    public static void viewAvailableBooks() {
        System.out.println("Kitap İsmi   -   Yazar İsmi   -   Sayfa Sayısı");
        for (int i = 0; i < books.length; i++) {
            for (int j = 0; j < books[i].length; j++) {
                System.out.print(books[i][j]);
                if (j < books[i].length - 1) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
    public static void searchBooks(Scanner scanner) {

        String[] kitapismi = {"Sefiller", "Hayvan Çiftliği"};
        String[] yazar = {"Victor Hugo", "George Orwell"};
        int[] isbn = {1, 2};
        int[] sayfa1 = {1724, 152};
        System.out.println("Arama yapmak için aşağıdaki seçeneklerden birini seçin:");
        System.out.println("1. Başlık ile arama");
        System.out.println("2. Yazar ile arama");
        System.out.println("3. ISBN ile arama");
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
        }
    }
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
private static int[] countTotalBooks(){
    int totalBooks = 0;
    for (int i = 0; i < bookQuantity; i++) {
        totalBooks += Integer.parseInt(books[i][3]);
    }
    System.out.println("Toplam " + totalBooks + " kitap bulunmaktadır.");

 return countTotalBooks();
}

}