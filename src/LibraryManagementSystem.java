import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LibraryManagementSystem {
    static int INDEX = 20;
    static String[][] books = new String[INDEX][4];
    static String[][] patrons = new String[INDEX][4];
    static String[][] transactions = new String[INDEX][3];
    static int bookQuantity=0;
    static int patronQuantity = 0;
    // kitap dönüş tarihi
    private static String checkBookReturnDeadline(String bookISBN) {

        for (int i = 0; i < patrons.length; i++) {
            String bookIsbn = patrons[i][3];
            if (bookIsbn != null) {
                if (bookIsbn.equalsIgnoreCase(bookISBN)) {
                    System.out.println(bookISBN + " book with ISBN number found.");

                    LocalDate today = LocalDate.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M.yyyy");

                    System.out.println("Book purchased date           : " + format.format(today).toUpperCase());
                    LocalDate mustcome = today.plusDays(30);
                    System.out.println("Date the book should arrive : " + format.format(mustcome));

                    Scanner scan = new Scanner(System.in);
                    System.out.print("Date the book arrived (enter d.m.yyyy format): ");
                    String BookArrivalDate = scan.next();

                    try {
                        LocalDate BookArrivalDatestr = LocalDate.parse(BookArrivalDate, format);
                        System.out.println("Book purchased date        : " + format.format(BookArrivalDatestr));

                        if (BookArrivalDatestr.isAfter(mustcome)) {
                            System.out.println("The book arrived later than it was supposed to arrive!");
                            System.out.println("The user is prohibited from purchasing books for 1 month.");
                        } else {
                            System.out.println("The book was delivered before its due date.");
                            System.out.println("User can buy books.");
                        }
                    } catch (Exception e) {
                        System.out.println("Incorrect date format! Please enter a date in d.M.yyyy format.");
                    }
                } else {
                    System.out.println("No record found for this Isbn. ");
                    break;
                }
            } else
                ;
            break;
        }
        return " You can borrow the book.";
    }
    public static String checkOutBook(String nameAndSurname, String tc, String bookName,String bookISBN) {
        if (patronQuantity < INDEX) {
            patrons[patronQuantity][0] = nameAndSurname.replaceAll(" ", "").toLowerCase();
            patrons[patronQuantity][1] = tc;
            patrons[patronQuantity][2] = bookName.replaceAll(" ", "").toLowerCase();
            patrons[patronQuantity][3] = bookISBN;
            patronQuantity++;

            //aranılan obje bulma

            boolean bookkk=false;
            for (String[] book : books){
                if (book[0].equalsIgnoreCase(bookName.toLowerCase())){
                    System.out.println(book[0]+" there is a book called. The author is:"+book[1]);
                    bookkk=true;

                    if (patrons.length > patronQuantity) {
                        transactions[patronQuantity][0] = patrons[patronQuantity][0];
                        transactions[patronQuantity][1] = patrons[patronQuantity][1];
                        transactions[patronQuantity][2] = patrons[patronQuantity][3];
                        patronQuantity++;
                        System.out.println("The book purchase was successful.");
                        int bookIndex=-1;
                        for (int i =0;i<books.length;i++){
                            if (books[i][0].equalsIgnoreCase(bookName)){
                                bookIndex=i;
                                break;
                            }
                        }
                        if (bookIndex!=-1){
                            String[][] newBooks= new String[books.length-1][2];
                            int  newIndex = 0;
                            for (int i = 0 ;i< books.length;i++){
                                if (i!=bookIndex){
                                    newBooks[newIndex++]=books[i];
                                }
                            }
                            books=newBooks;
                            System.out.println("The list has been updated. ");

                        }else {
                            System.out.println("Error finding in book Try again.");
                        }
                    } else {
                        System.out.println("Error finding in book Try again.");
                    }
                }
            }
            if (!bookkk){
                System.out.println("There is no such book in our library. ");
            }
        } else {
            String[][] newwpatrons = new String[INDEX + 1][4];
            for (int i = 0; i < newwpatrons.length; i++) {
                for (int j = 0; j < 4; j++) {
                    newwpatrons[i][j] = patrons[i][j];
                }
            }
            System.out.println("Name added :" + patrons[patronQuantity][0]);
            newwpatrons[patronQuantity][0] = nameAndSurname;
            patronQuantity++;
            patrons[patronQuantity][0] = newwpatrons[patronQuantity][0];
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

}