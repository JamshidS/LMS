import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

public class LibraryManagementSystem {
    static int INDEX = 20;
    static String[][] books = new String[INDEX][4];
    static String[][] patrons = new String[INDEX][4];
    static String[][] transactions = new String[INDEX][3];
    static int transcationQuantit = 0;
    static int patronQuantity = 0;

    public static String checkOutBook(String fullName, String tc, String eMail, String password, String bookName, String bookISBN) {
        if (patronQuantity < INDEX) {
            patrons[patronQuantity][0] = fullName.replaceAll(" ", "").toLowerCase();
            patrons[patronQuantity][1] = tc;
            patrons[patronQuantity][2] = eMail.replaceAll(" ", "").toLowerCase();
            patrons[patronQuantity][3] = password;
            patronQuantity++;

            //aranılan obje bulma

            boolean bookkk = false;
            for (String[] book : books) {
                if (book[0].equalsIgnoreCase(bookName.trim())) {
                    System.out.println(book[0] + "  adında bir kitap var. Yazar :" + book[1]);
                    bookkk = true;

                    if (patrons.length > transcationQuantit ){
                        transactions[transcationQuantit][0] = tc;
                        transactions[transcationQuantit][1] = bookISBN;
                        transactions[transcationQuantit][2] = LocalDate.now().toString();
                        transcationQuantit++;
                        System.out.println("Kitap alımı başarılı oldu.");

                        int bookIndex = -1;
                        for (int i = 0; i < books.length; i++) {
                            if (books[i][0].equalsIgnoreCase(bookName)) {
                                bookIndex = i;
                                break;
                            }
                        }
                        if (bookIndex != -1) {
                            String[][] newBooks = new String[books.length - 1][2];
                            int newIndex = 0;
                            for (int i = 0; i < books.length; i++) {
                                if (i != bookIndex) {
                                    for (int k =0;k < books.length;k++){
                                        newBooks[newIndex] = books[i];
                                    }
                                    newIndex++;
                                }
                                books = newBooks;
                            }

                            System.out.println("Liste güncellendi. ");

                        } else {
                            System.out.println("Kişi eklenemdi.");
                        }
                    } else {
                        System.out.println("Dosya boyutu aşıldı.");
                    }
                }
            }
            if (!bookkk) {
                System.out.println("Kütüphanemizde böyle bir kitap bulunmamaktadır. ");
            }
        } else {
            String[][] newwpatrons = new String[INDEX + 1][4];
            for (int i = 0; i < newwpatrons.length; i++) {
                for (int j = 0; j < 4; j++) {
                    newwpatrons[i][j] = patrons[i][j];
                }
            }
            System.out.println("Name added :" + patrons[patronQuantity][0]);
            newwpatrons[patronQuantity][0] = fullName;
            newwpatrons[patronQuantity][1] = tc;
            newwpatrons[patronQuantity][2] = eMail;
            newwpatrons[patronQuantity][3] = password;

            patrons=newwpatrons;
        }
        return "The book purchase was successful.";
    }


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


}


