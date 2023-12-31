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
}