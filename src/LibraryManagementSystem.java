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
    static int userListİndex =-1;
    public static String updatePatronInfo(String updateSearchPatronTC, String updateName, String updateSurname, String updateTC, String updateWriter, String upadateBookISBN){

        for (int i =0;i< patrons.length;i++){
            String searchPatrons = patrons[i][2];
            userListİndex =i;
            if (searchPatrons.equalsIgnoreCase(updateSearchPatronTC)){
                System.out.println("User name            :"+patrons[userListİndex][0]);
                System.out.println("Patron surname       :"+patrons[userListİndex][1]);
                System.out.println("User found T.C       :"+patrons[userListİndex][2]);
                System.out.println("Received book        :"+patrons[userListİndex][3]);
                System.out.println("Received book writer :"+patrons[userListİndex][4]);
                System.out.println("Received bookISBN    :"+patrons[userListİndex][5]);
                System.out.println();

                System.out.println("Enter current user information.");
                patrons[userListİndex][0] = updateName;
                patrons[userListİndex][1] = updateSurname;
                patrons[userListİndex][2] = updateTC;
                patrons[userListİndex][3] = updateWriter;
                patrons[userListİndex][4] = upadateBookISBN;

                for (int j = 0; j < patrons[userListİndex].length; j++){
                    patrons[i][j]=patrons[userListİndex][j];
                }
                System.out.println("The update was successful.");
            }
        }
        return "";
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