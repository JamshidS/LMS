import java.util.Scanner;

public class KütüphaneSistemi {

    Scanner scan = new Scanner(System.in);

    private static int[] ındex = new int[100];
    private static String[][] bookNames = new String[ındex.length][4];
    private static String[][] patrons = new String[ındex.length][bookNames.length];
    private static String[][] transcation = new String[bookNames.length][4];


    int totalbooks = 0;

    public void checkOutBook(String bookName, String author, String numberOfPages, String idNumber) {

        for (int i = 0; i < bookNames.length; i++) {
            for (int j = 0; j < bookNames[i].length; j++) {
                bookNames[i][j] = bookName;
                bookNames[i][j+1] = author;
                bookNames[i][j+2] = numberOfPages;
                bookNames[i][j+3] = idNumber;
            }
        }
        totalbooks++;
    }
    private void totalBooks() {
        System.out.println("Total book :" + totalbooks);
    }

    public static void main(String[] args) {

        KütüphaneSistemi kütüphaneSistemi = new KütüphaneSistemi();

        Scanner scan = new Scanner(System.in);
        System.out.println("*Welcome to Library*\n");
        while (true) {
            System.out.println("\n Selection section by number : ");
            System.out.println("1. If you want to add a book :");
            System.out.println("2. Total number of books :");
            System.out.println("3. Exit.\n");

            System.out.print("Enter the number :");
            int number = scan.nextInt();
            if (number == 3) {
                System.out.println("Exit. Good bye..");
                System.exit(0);
            } else {
                switch (number) {
                    case 1:
                        scan.nextLine();
                        System.out.print("Books Name: ");
                        String bookName = scan.nextLine().toUpperCase();
                        System.out.print("enter the author of the book : ");
                        String author = scan.nextLine().toUpperCase();
                        System.out.print("Enter the number of pages of the book :");
                        String numberOfPages = scan.nextLine().toUpperCase();
                        System.out.print("Enter the ID number of the book:");
                        String ıdNumber = scan.nextLine().toUpperCase();
                        System.out.println("\nyour book has been added : " + bookName);
                        System.out.println("The author of the book :" + author);
                        System.out.println("number of pages of the book:" + numberOfPages);
                        System.out.println("ID number of the book:" + ıdNumber);
                        kütüphaneSistemi.checkOutBook(bookName, author, numberOfPages, ıdNumber);

                        break;
                    case 2:
                        kütüphaneSistemi.totalBooks();
                        break;
                    case 3:
                        System.out.println("You are leaving the system..");
                        break;
                    default:
                        System.out.println("Error.");

                }
            }

        }


    }
    }








