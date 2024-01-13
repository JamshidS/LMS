import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class LibraryManagementSystem {
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

    private static String updatePatronInfo(String updateSearchPatronTC,String fullName,String updateTC,String eMail,String password) {
        
        int userListİndex = -1;

        for (int i = 0; i < patrons.length; i++) {
            String searchPatrons = patrons[i][1];
            userListİndex = i;
            if (searchPatrons.equalsIgnoreCase(updateSearchPatronTC)) {
                System.out.println("Aranılan kişi bulundu.\n");
                break;

            }else {
                System.out.println("Aranılan kişi bulunamadı.");
            }
        }
        System.out.println("Arama tamamlandı.");
        System.out.println("Patronun tam ismi: " + patrons[userListİndex][0]);
        System.out.println("Patron T.C       : " + patrons[userListİndex][1]);
        System.out.println("Patron E-posta   : " + patrons[userListİndex][2]);
        System.out.println("Patron şifre     : " + patrons[userListİndex][3]+"\n");

        patrons[userListİndex][0] = fullName.toLowerCase();
        patrons[userListİndex][1] = updateTC;
        patrons[userListİndex][2] = eMail.replaceAll(" ", "").toLowerCase();
        patrons[userListİndex][3] = password;


        return "güncellendi";
    }

    public static String[][] extendBooksArrayOnAddition() {
        String[][] newBooks = new String[books.length + 1][4];
        for (int i = 0; i < books.length; i++) {
            for (int j = 0; j < 4; j++) {
                newBooks[i][j] = books[i][j];
            }
        }
        return newBooks;

    }

    public static void main(String[] args) {
        System.out.println("Merhaba");
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

    public static void generateReports() {
        int totalBooks = 0;
        if (bookQuantity == 0) {
            System.out.println("Kütühanede kitap sayısı 0'dır.");
        } else {
            System.out.printf("%-20s %-20s %-20s %-20s%n", "Kitap İsmi", "Yazar İsmi", "Kitap Sayfası", "ISBN");
            for (int i = 0; i < bookQuantity; i++) {
                System.out.printf("%-20s %-20s %-20s %-20s%n", books[i][0], books[i][1], books[i][2], books[i][3]);
                totalBooks++;
            }
            System.out.println();
            System.out.println("Toplam kitap sayısı : " + totalBooks);
        }
    }


    public static void deleteBook(String ISBN) {
        int findIndex = -1;
        if (bookQuantity == 0) {
            System.out.println("Kütühanede kitap sayısı 0'dır.");
        }
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][3].equals(ISBN)) {
                findIndex = i;
                break;
            }
        }
        if (findIndex != -1) {
            for (int i = findIndex; i < bookQuantity - 1; i++) {
                books[i] = books[i + 1];
            }
            bookQuantity--;
            String[][] newBooks = new String[books.length][4];
            for (int i = 0; i < books.length; i++) {
                for (int j = 0; j < books[i].length; j++) {
                    newBooks[i][j] = books[i][j];
                }
            }
            books = newBooks;
            System.out.println("Kitap Silinmiştir.");
        } else {
            System.out.println("Silmek isteğiniz kitap bulunmamaktadır.");

        }
    }

    public static boolean bookAvaible(String ISBN) {
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][3].equals(ISBN)) {
                return true;
            }
        }
        return false;
    }


    public static void bookview(String bookName) {

        boolean kitapBulundu = false;
        for (int i = 0; i < books.length; i++) {
            if (bookName.equals(books[i][0])) {
                kitapBulundu = true;
                System.out.println("Kitap Adı: " + books[i][0]);
                System.out.println("Yazarı: " + books[i][1]);
                System.out.println("Sayfa Sayısı: " + books[i][2]);
                break;
            }
        }
        if (!kitapBulundu) {
            System.out.println("İstediğiniz kitap kütüphanemizde bulunmamaktadır.");
        }

    }
    public static void hataliGiris (String id, String sifre){
        boolean girisBasarili = false;
        for (int i=0; i<patrons.length;i++) {
            if (id.equals(patrons[i][2]) && sifre.equals(patrons[i][3])) {
                girisBasarili = true;
                System.out.println("Giriş Başarılı");
                break;
            }
        }
        if (!girisBasarili){
            System.out.println("Giriş Başarısız");
        }

    }
    public static void generateBookRecommendations(String tc) {
        String bookISBN = null;
        for (int i = 0; i < transactionsQuantity; i++) {
            if (transactions[i][0].equals(tc) && transactions[i][1] != null) {
                bookISBN = transactions[i][1];
                break;
            }
        }
        if (bookISBN == null) {
            Random random = new Random();
            int randomIndex = random.nextInt(bookQuantity);
            String recomTitle = books[randomIndex][0];
            String recomAuthor = books[randomIndex][1];
            String recomPageCount = books[randomIndex][2];
            String recomISBN = books[randomIndex][3];
            System.out.printf("Size önerilen kitap: \nBaşlık: %s, Yazar: %s, Sayfa Sayısı: %s, ISBN: %s",
                    recomTitle, recomAuthor, recomPageCount, recomISBN);
        } else {
            String bookAuthor = null;
            for (int i = 0; i < bookQuantity; i++) {
                if (books[i][3].equals(bookISBN)) {
                    bookAuthor = books[i][1];
                    break;
                }
            }
            System.out.println("Daha önce aldığınız kitaplara göre önerilen kitaplar : ");
            for (int j = 0; j < bookQuantity; j++) {
                if (books[j][1].equals(bookAuthor)) {
                    System.out.println("Başlık: " + books[j][0] +
                            ", Yazar: " + books[j][1] +
                            ", Sayfa Sayısı: " + books[j][2] +
                            ", ISBN: " + books[j][3]);
                }
            }
        }
    }


    public static void updateBook(String ISBN, String title, String author, String bookPage) {
        boolean kitapBulundu = false;
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][3].equals(ISBN)) {
                kitapBulundu = true;
                books[i][0] = title;
                books[i][1] = author;
                books[i][2] = bookPage;
                System.out.println("Kitap Bilgileri Güncellenmiştir.");
                break;
            }
        }
        if (!kitapBulundu) {
            System.out.println("Güncellemek istediğiniz kitap bulunmamaktadır.");
        }
    }
    public static int countTotalBooks() {

        return bookQuantity;

    }
}


