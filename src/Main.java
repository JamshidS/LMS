
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    final static int INDEX = 20;
    private static String[][] books = {
            {"Araba sevdası", " Recaizade mahmut ekrem"},
            {"Ateşten gömlek", "Halide Edib Adıvar"},
            {"Bir ömür nasıl yaşanır ", "İlber ortaylı",},
            {"İnsan geleceğini nasıl kurar", "İlber ortaylı"},
            {"Türkiyenin yakın tarihi", "İlber ortaylı"}};
    static String[][] patrons = new String[INDEX][6];
    static String[][] transactions = new String[INDEX][3];


    private static void  checkBookReturnDeadline(String  bookISBN){


        for (int i =0;i<transactions.length;i++){
            String bookIsbn =transactions[i][1];
            if (bookIsbn.equalsIgnoreCase(bookISBN)){
                System.out.println(bookISBN+" book with ISBN number found.");

                LocalDate today = LocalDate.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M.yyyy");

                System.out.println("Book purchased date           : " + format.format(today).toUpperCase());
                LocalDate mustcome = today.plusDays(30);
                System.out.println("Date the book should arrive : " + format.format(mustcome));


                Scanner scan = new Scanner(System.in);
                System.out.print("Date the book arrived (enter d.m.yyyy format): ");
                String BookArrivalDate=scan.next();

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
            }else {
                System.out.println("No book record found.");

            }
        }




    }








        public static void main(String[] args) {





            }}












