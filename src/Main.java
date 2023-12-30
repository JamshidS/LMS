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
    public static void searchBooks(Scanner Scanner) {
        String[] kitapismi = {"Sefiller", "Hayvan Çiftliği"};
        String[] yazar = {"Victor Hugo", "George Orwell"};
        int[] isbn = {1, 2};
        int[] sayfa1 = {1724, 152};
        Scanner scanner = new Scanner(System.in);
        System.out.println("Arama yapmak için aşağıdaki seçeneklerden birini seçin:");
        System.out.println("1. Başlık ile arama");
        System.out.println("2. Yazar ile arama");
        System.out.println("3. ISBN ile arama");
        int secim = scanner.nextInt();
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
}