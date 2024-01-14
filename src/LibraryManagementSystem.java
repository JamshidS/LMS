import java.sql.SQLOutput;
import java.util.Scanner;

public class LibraryManagementSystem {
    static int INDEX = 20;
    static String[][] books = new String[INDEX][4];
    static String[][] patrons = new String[INDEX][4];
    static String[][] transactions = new String[INDEX][3];
    static int bookQuantity=0;
    public static void addBook(String title,String author,String bookPage,String ISBN){
        if (bookQuantity<books.length){
            books[bookQuantity][0]=title;
            books[bookQuantity][1]=author;
            books[bookQuantity][2]=bookPage;
            books[bookQuantity][3]=ISBN;
            bookQuantity++;
        }
        else {
            String[][] newBookList=new String[books.length+1][4];
            for (int i=0; i<books.length; i++){
                for (int j=0; j<4; j++){
                    newBookList[i][j]=books[i][j];
                }
            }
            newBookList[bookQuantity][0]=title;
            newBookList[bookQuantity][1]=author;
            newBookList[bookQuantity][2]=bookPage;
            newBookList[bookQuantity][3]=ISBN;

            bookQuantity++;
            books=newBookList;


        }
    }
    public static void main(String[] args) {
        System.out.println("Merhaba");
    }

    public static  void viewAvailableBooks () {
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
        if (bookQuantity==0){
            System.out.println("Kütühanede kitap sayısı 0'dır.");
        }
        else {
            System.out.printf("%-20s %-20s %-20s %-20s%n", "Kitap İsmi", "Yazar İsmi","Kitap Sayfası", "ISBN");
            for (int i = 0; i < bookQuantity; i++) {
                System.out.printf("%-20s %-20s %-20s %-20s%n",books[i][0],books[i][1],books[i][2],books[i][3]);
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
  
    public static boolean bookAvaible(String ISBN){
        for (int i=0; i<bookQuantity; i++){
            if (books[i][3].equals(ISBN)){
                return true;
            }
        }
        return false;
    }


    public static void bookview (String bookName){

        boolean kitapBulundu = false;
        for (int i =0; i< books.length;i++){
            if(bookName.equals(books[i][0])){
                kitapBulundu = true;
                System.out.println("Kitap Adı: "+books[i][0]);
                System.out.println("Yazarı: "+books[i][1]);
                System.out.println("Sayfa Sayısı: "+books[i][2]);
                break;
            }
        }
        if (!kitapBulundu){
            System.out.println("İstediğiniz kitap kütüphanemizde bulunmamaktadır.");
        }

    }
    public static String bookReturn() {
        Scanner scr = new Scanner(System.in);
        System.out.println("İsminizi giriniz :");
        String name = scr.nextLine();

        System.out.println("Kitabın ismini giriniz :");
        String bookName = scr.nextLine();

        System.out.println("Kitabın  numarasını  giriniz :");
        int numarasi = scr.nextInt();

        int kitapIndex = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i][0] != null && books[i][3] != null) {
                if (books[i][0].equalsIgnoreCase(bookName) && books[i][3].equals(numarasi)) {
                    kitapIndex = i;
                    break;
                }
            }
        }
        if (kitapIndex != -1) {
            int currentQuantity = Integer.parseInt(books[kitapIndex][2]);
            if (currentQuantity > 0) {
                books[kitapIndex][2] = String.valueOf(currentQuantity - 1); 
                System.out.println("İşlem başarıyla tamamlandı. Kitap iade edildi.");
                 int transactionIndex = -1;
                for (int i = 0; i < transactions.length; i++) {
                    if (transactions[i] != null && transactions[i][0] != null && transactions[i][1] != null && transactions[i][2] != null) {
                        if (transactions[i][0].equalsIgnoreCase(bookName) && transactions[i][1].equals(numarasi)) {
                            transactionIndex = i;
                            break;
                        }
                    }
                }

                if (transactionIndex != -1) {
                    for (int i = transactionIndex; i < transactions.length - 1; i++) {
                        transactions[i] = transactions[i + 1];
                    }
                    transactions[transactions.length - 1] = null;
                    System.out.println("Kitap iade edildi ve ilgili işlem kaydı silindi.");
                } else {
                    System.out.println("Kitap iade edildi, ancak ilgili işlem kaydı bulunamadı.");
                }

                return "İşlem başarıyla tamamlandı. Kitap iade edildi.";

            } else {
                System.out.println("kitap iade edilemedi. kitap stokta yok.");
            }
        } else {
            System.out.println("Kitap iade edilemedi. Belirtilen isim ve numarada kitap bulunamadı veya stokta yok.");
        }
        scr.close();
        return "Kitap iade edilemedi. Belirtilen isim ve numarada kitap bulunamadı veya stokta yok.";
    }
    }
}
