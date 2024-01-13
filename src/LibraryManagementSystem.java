import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class LibraryManagementSystem {
    static int INDEX = 20;
    static String[][] books = new String[INDEX][4];
    static String[][] patrons = new String[INDEX][4];
    static String[][] transactions = new String[INDEX][3];
    static int bookQuantity=0;
    static int transactionQuantity = 0;
    static int patronQuantity = 0;

    private static String userdeleteddd(String patronsTC) {
        int bookIndex = -1;

        for (int i = 0; i < patrons.length; i++) {
            if (patrons[i][1].equalsIgnoreCase(patronsTC)) {
                bookIndex = i;
                break;
            }
        }
        if (bookIndex != -1) {
            String[][] newpatrons = new String[patrons.length - 1][2];
            int newIndex = 0;
            for (int i = 0; i < patrons.length; i++) {
                if (bookIndex != i) {
                    for (int k = 0; k < 2; k++) {
                        if (patrons[i][k] != null) {
                            newpatrons[newIndex] = patrons[i];
                            ///
                        }
                    }
                    newIndex++;
                }
            }
            patrons = newpatrons;


        }

        return "Liste güncellendi kullanıcı silindi";

    }
  
    private static String checkBookReturnDeadline(String bookISBN) {

        for (int i = 0; i < patrons.length;i++) {
            String kullaniciTC = patrons[i][1];
            if (kullaniciTC != null) {
                if (kullaniciTC.equalsIgnoreCase(bookISBN)) {
                    System.out.println(bookISBN + " T.C numarasına sahip kişi bulundu.");

                    LocalDate bugun = LocalDate.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

                    System.out.println("Kitabın ödünç alındığı tarih      : " + format.format(bugun).toUpperCase());
                    LocalDate teslimTarihi = bugun.plusDays(10);
                    System.out.println("Kitabın getirilmesi gereken tarih : " + format.format(teslimTarihi));

                    Scanner scan = new Scanner(System.in);
                    System.out.print("Kitabın geldiği tarih (gg.aa.yyyy biçimini girin): ");
                    String kitapGelisTarihiStr = scan.next();
                    LocalDate kitapGelisTarihi = LocalDate.parse(kitapGelisTarihiStr, format);

                    if (kitapGelisTarihi.isAfter(teslimTarihi)) {
                        System.out.println("Kitap beklenenden geç geldi!");
                        System.out.println("Kullanıcının 1 ay süreyle kitap ödünç alması yasaktır.");

                        break;
                    } else {
                        System.out.println("Kitap son teslim tarihinden önce teslim edildi.");
                        System.out.println("Kullanıcı kitap ödünç alabilir.");
                    }
                } else {
                    System.out.println("Bu T.C'ye ait kayıt bulunamadı.");
                    break;
                }
            } else {
                break;
            }

        }
        return "Kullanıcı kitap ödünç alabilir. ";
    }

    public static void addBook(String title, String author, String bookPage, String ISBN) {
        if (bookQuantity < books.length) {
            books[bookQuantity][0] = title;
            books[bookQuantity][1] = author;
            books[bookQuantity][2] = bookPage;
            books[bookQuantity][3] = ISBN;
            bookQuantity++;
        } else {
            String[][] newBookList = new String[books.length + 1][4];
            for (int i = 0; i < books.length; i++) {
                for (int j = 0; j < 4; j++) {
                    newBookList[i][j] = books[i][j];
                }
            }
            newBookList[bookQuantity][0] = title;
            newBookList[bookQuantity][1] = author;
            newBookList[bookQuantity][2] = bookPage;
            newBookList[bookQuantity][3] = ISBN;

            bookQuantity++;
            books = newBookList;
        }
    }

    private static String updatePatronInfo(String updateSearchPatronTC,String fullName,String updateTC,String eMail,String password) {
        
        int userListİndex = -1;

        for (int i = 0; i < patrons.length; i++) {
            String searchPatrons = patrons[i][1];
            userListİndex = i;
            if (searchPatrons.equalsIgnoreCase(updateSearchPatronTC)) {
                System.out.println("Aranılan kişi bulundu.\n");
                break;

            }else {
                System.out.println("Aranılan kişi bulunamadı.");
            }
        }
        System.out.println("Arama tamamlandı.");
        System.out.println("Patronun tam ismi: " + patrons[userListİndex][0]);
        System.out.println("Patron T.C       : " + patrons[userListİndex][1]);
        System.out.println("Patron E-posta   : " + patrons[userListİndex][2]);
        System.out.println("Patron şifre     : " + patrons[userListİndex][3]+"\n");

        patrons[userListİndex][0] = fullName.toLowerCase();
        patrons[userListİndex][1] = updateTC;
        patrons[userListİndex][2] = eMail.replaceAll(" ", "").toLowerCase();
        patrons[userListİndex][3] = password;


        return "güncellendi";
    }

    public static String[][] extendBooksArrayOnAddition() {
        String[][] newBooks = new String[books.length + 1][4];
        for (int i = 0; i < books.length; i++) {
            for (int j = 0; j < 4; j++) {
                newBooks[i][j] = books[i][j];
            }
        }
        return newBooks;

    }

    public static void main(String[] args) {
        System.out.println("Merhaba");
    }


    public static void viewAvailableBooks() {
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
        if (bookQuantity == 0) {
            System.out.println("Kütühanede kitap sayısı 0'dır.");
        } else {
            System.out.printf("%-20s %-20s %-20s %-20s%n", "Kitap İsmi", "Yazar İsmi", "Kitap Sayfası", "ISBN");
            for (int i = 0; i < bookQuantity; i++) {
                System.out.printf("%-20s %-20s %-20s %-20s%n", books[i][0], books[i][1], books[i][2], books[i][3]);
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

    public static boolean bookAvaible(String ISBN) {
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][3].equals(ISBN)) {
                return true;
            }
        }
        return false;
    }


    public static void bookview(String bookName) {

        boolean kitapBulundu = false;
        for (int i = 0; i < books.length; i++) {
            if (bookName.equals(books[i][0])) {
                kitapBulundu = true;
                System.out.println("Kitap Adı: " + books[i][0]);
                System.out.println("Yazarı: " + books[i][1]);
                System.out.println("Sayfa Sayısı: " + books[i][2]);
                break;
            }
        }
        if (!kitapBulundu) {
            System.out.println("İstediğiniz kitap kütüphanemizde bulunmamaktadır.");
        }

    }
    public static void hataliGiris (String id, String sifre){
        boolean girisBasarili = false;
        for (int i=0; i<patrons.length;i++) {
            if (id.equals(patrons[i][2]) && sifre.equals(patrons[i][3])) {
                girisBasarili = true;
                System.out.println("Giriş Başarılı");
                break;
            }
        }
        if (!girisBasarili){
            System.out.println("Giriş Başarısız");
        }

    }
    public static void generateBookRecommendations(String tc) {
        String bookISBN = null;
        for (int i = 0; i < transactionQuantity; i++) {
            if (transactions[i][0].equals(tc) && transactions[i][1] != null) {
                bookISBN = transactions[i][1];
                break;
            }
        }
        if (bookISBN == null) {
            Random random = new Random();
            int randomIndex = random.nextInt(bookQuantity);
            String recomTitle = books[randomIndex][0];
            String recomAuthor = books[randomIndex][1];
            String recomPageCount = books[randomIndex][2];
            String recomISBN = books[randomIndex][3];
            System.out.printf("Size önerilen kitap: \nBaşlık: %s, Yazar: %s, Sayfa Sayısı: %s, ISBN: %s",
                    recomTitle, recomAuthor, recomPageCount, recomISBN);
        } else {
            String bookAuthor = null;
            for (int i = 0; i < bookQuantity; i++) {
                if (books[i][3].equals(bookISBN)) {
                    bookAuthor = books[i][1];
                    break;
                }
            }
            System.out.println("Daha önce aldığınız kitaplara göre önerilen kitaplar : ");
            for (int j = 0; j < bookQuantity; j++) {
                if (books[j][1].equals(bookAuthor)) {
                    System.out.println("Başlık: " + books[j][0] +
                            ", Yazar: " + books[j][1] +
                            ", Sayfa Sayısı: " + books[j][2] +
                            ", ISBN: " + books[j][3]);
                }
            }
        }
    }

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

                    if (patrons.length > transactionQuantity){
                        transactions[transactionQuantity][0] = tc;
                        transactions[transactionQuantity][1] = bookISBN;
                        transactions[transactionQuantity][2] = LocalDate.now().toString();
                        transactionQuantity++;
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
    public static int countTotalBooks() {

        return bookQuantity;

    }
}


