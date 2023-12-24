public class Main {
    final static int INDEX = 20;
    static String[][] books = new String[INDEX][4];
    static String[][] patrons = new String[INDEX][4];
    static String[][] transactions = new String[INDEX][3];
    static int bookQuantity=0;

    public static void addBook(String title,String author,String ISBN){
        if (bookQuantity<=INDEX){
            books[bookQuantity][0]=title;
            books[bookQuantity][1]=author;
            books[bookQuantity][2]=ISBN;
            bookQuantity++;
        }
        else {
            System.out.println("The library capacity is full, new books cannot be added.");
        }
    }
    public static void main(String[] args) {

    }
}