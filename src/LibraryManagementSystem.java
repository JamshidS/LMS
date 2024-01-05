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

    private static String[] bookTitles = {"Les Misérables", "Animal Farm"};
    private static String[] authors = {"Victor Hugo", "George Orwell"};
    private static int[] isbns = {1, 2};
    private static int[] pageCounts = {1724, 152};

    public static void searchBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose one of the options below for searching:");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.println("3. Search by ISBN");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter title: ");
                String titleSearch = scanner.nextLine();
                for (int i = 0; i < bookTitles.length; i++) {
                    if (bookTitles[i].equalsIgnoreCase(titleSearch)) {
                        System.out.println("Book Found:");
                        System.out.println("Title: " + bookTitles[i]);
                        System.out.println("Author: " + authors[i]);
                        System.out.println("ISBN: " + isbns[i]);
                        System.out.println("Page Count: " + pageCounts[i]);
                        return;
                    }
                }
                System.out.println("Book not found.");
                break;
            case 2:
                System.out.print("Enter author name: ");
                String authorSearch = scanner.nextLine();
                for (int i = 0; i < authors.length; i++) {
                    if (authors[i].equalsIgnoreCase(authorSearch)) {
                        System.out.println("Book Found:");
                        System.out.println("Title: " + bookTitles[i]);
                        System.out.println("Author: " + authors[i]);
                        System.out.println("ISBN: " + isbns[i]);
                        System.out.println("Page Count: " + pageCounts[i]);
                        return;
                    }
                }
                System.out.println("Book not found.");
                break;
            case 3:
                System.out.print("Enter ISBN: ");
                int isbnSearch = scanner.nextInt();
                for (int i = 0; i < isbns.length; i++) {
                    if (isbns[i] == isbnSearch) {
                        System.out.println("Book Found:");
                        System.out.println("Title: " + bookTitles[i]);
                        System.out.println("Author: " + authors[i]);
                        System.out.println("ISBN: " + isbns[i]);
                        System.out.println("Page Count: " + pageCounts[i]);
                        return;
                    }
                }
                System.out.println("Book not found.");
                break;

            default:
        }
    }
}