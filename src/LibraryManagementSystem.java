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

    public static void checkOutBook(String name, String surName, String tc, String bookName, String writer,String bookISBN,String date) {


        if (bookQuantity < patrons.length) {
            patrons[bookQuantity][0] = name; // isim soyisim
            patrons[bookQuantity][1] = surName;
            patrons[bookQuantity][2] = tc;
            patrons[bookQuantity][3] = bookName;
           // patrons[bookQuantity][4] = writer;
           /* patrons[bookQuantity][5] = bookISBN;*/
            bookQuantity++;

            //aranılan obje bulma /
            boolean bookkk=false;
            for (String[] book : books){
                if (book[0].equalsIgnoreCase(bookName)){
                    System.out.println(book[0]+" isimli kitap vardır.Yazarı :"+book[1]);
                    bookkk=true;

                    if (patrons.length > bookQuantity) {
                        transactions[bookQuantity][0] = patrons[2][2];
                        transactions[bookQuantity][1] = bookISBN;
                        transactions[bookQuantity][2] = date;
                        bookQuantity++;
                        System.out.println("Book purchase has been successfully recorded");

                    } else {
                        System.out.println("Adding a book did not work. Try again.");
                    }

                }
            }
            if (!bookkk){
                System.out.println(" ");
            }
        } else {
            String[][] newwpatrons = new String[patrons.length + 1][4];
            for (int i = 0; i < newwpatrons.length; i++) {
                for (int j = 0; j < 4; j++) {
                    newwpatrons[i][j] = patrons[i][j];
                }
            }
            System.out.println("Name added :" + patrons[bookQuantity][0]);
            newwpatrons[bookQuantity][0] = name;
            bookQuantity++;
            patrons[bookQuantity][0] = newwpatrons[bookQuantity][0];
        }
    }

    public static void main(String[] args) {
        checkOutBook("Emre","Aybar","564674448574","Sefiller","VH","ahskjdh4788","06/01/2024");
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


}