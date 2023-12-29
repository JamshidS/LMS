

public class Main {



    final static int INDEX = 20;
    private static String[][] books = {
            {"Araba sevdası", " Recaizade mahmut ekrem"},
            {"Ateşten gömlek", "Halide Edib Adıvar"},
            {"Bir ömür nasıl yaşanır ", "İlber ortaylı",},
            {"İnsan geleceğini nasıl kurar", "İlber ortaylı"},
            {"Türkiyenin yakın tarihi", "İlber ortaylı"}};
    static String[][] patrons = new String[INDEX][4];
    static String[][] transactions = new String[INDEX][3];

    static int increasee = 0;
    public void checkOutBook(String name, String surName, String tc, String bookName, String writer) {
        if (increasee < patrons.length) {
            patrons[increasee][0] = name;
            patrons[increasee][1] = surName;
            patrons[increasee][2] = tc;
            patrons[increasee][3] = bookName;
            patrons[increasee][4] = writer;
            increasee++;
            System.out.println("Name added ");
        } else {
            String[][] newwpatrons = new String[patrons.length + 1][4];
            for (int i = 0; i < newwpatrons.length; i++) {
                for (int j = 0; j < 4; j++) {
                    newwpatrons[i][j] = patrons[i][j];
                }
            }
            System.out.println("Name added :" + patrons[increasee][0]);
            newwpatrons[increasee][0] = name;
            increasee++;
            patrons[increasee][0] = newwpatrons[increasee][0];
        }
    }

    public String checkOutBook(String bookName, String wtiter) {
        boolean bookkk = false;
        for (String[] book : books) {
            if (book[0].equalsIgnoreCase(bookName) || book[1].equalsIgnoreCase(wtiter)) {
                bookkk = true;
                System.out.println("The book you are looking for is available : " + book[0] + " - Writer : " + book[1]);

            }
        }
        if (!bookkk) {
            System.out.println("The book you are looking for is not available");

        }
        return bookName;
    }
    int increase = 0;
    public void checkOutBook(String Tc, String bookISBN, String history) {

        if (patrons.length > increase) {
            transactions[increase][0] = patrons[2][2];
            transactions[increase][1] = bookISBN;
            transactions[increase][2] = history;
            increase++;
            System.out.println("Book purchase has been successfully recorded");
        }else {
            System.out.println("Adding a book did not work. Try again.");
        }


    }


    public static void main(String[] args) {


    }
}










