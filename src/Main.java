public class Main {
    final static int INDEX = 20;
    static String[][] books = new String[INDEX][4];
    static String[][] patrons = new String[INDEX][4];
    static String[][] transactions = new String[INDEX][3];
    static int kitapSayisi=0;

    public static void addBook(String title,String author,String ISBN,String quantity){
        books[kitapSayisi][0]=title;
        books[kitapSayisi][1]=author;
        books[kitapSayisi][2]=ISBN;
        books[kitapSayisi][3]=quantity;
        kitapSayisi++;

    }
    public static void main(String[] args) {

    }
}