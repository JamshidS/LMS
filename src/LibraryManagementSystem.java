import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class LibraryManagementSystem {
    static int INDEX = 20;
    static String[][] books = new String[INDEX][4];
    static String[][] patrons = new String[INDEX][4];
    static String[][] transactions = new String[INDEX][3];
    static int bookQuantity = 0;
    static int transactionQuantity = 0;
    static int patronQuantity = 0;

    public static void login() {
        System.out.println("""
                \nEn iyi ödüllü kitap, yazar ve çok daha fazlası burada.
                Almaya hazır mısınız? Üye olmak ya da hesabınıza tekrar ulaşmak için tek yapmanız gereken kullanıcı adınız ve şifrenizi girmek.""");
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\nGiriş yapınız.");
            System.out.println("""
                    1-Hesap oluştur
                    2-Hesabınıza giriş.
                    3-Çıkış""");//
            System.out.print("Seçimi Giriniz :");
            int select = scan.nextInt();
            scan.nextLine();
            boolean isAuthenticated = false;
            String fullName = "";
            String identityNumber = "";
            String email = "";
            String password = "";

            switch (select) {
                case 1:
                    System.out.print("Kullanıcı adı    :");
                    fullName = scan.nextLine();
                    System.out.print("T.C              :");
                    identityNumber = scan.nextLine();
                    System.out.print("E-mail           :");
                    email = scan.nextLine();
                    System.out.print("Şifre            :");
                    password = scan.nextLine();

                    patrons[patronQuantity][0] = fullName;
                    patrons[patronQuantity][1] = identityNumber;
                    patrons[patronQuantity][2] = email;
                    patrons[patronQuantity][3] = password;
                    patronQuantity++;

                    break;

                case 2:
                    System.out.print("Kullanıcı e-mail :");
                    String userEmail = scan.nextLine();
                    System.out.print("Şifre         :");
                    String userPassword = scan.nextLine();
                    for (String[] patron : patrons) {
                        if (userEmail.equalsIgnoreCase(patron[2]) && userPassword.equalsIgnoreCase(patron[3])) {
                            System.out.println("Giriş başarılı..");
                            fullName = patron[0];
                            identityNumber = patron[1];
                            isAuthenticated = true;
                            break;

                        }
                    }
                    if (!isAuthenticated) {
                        System.out.println("Hatalı giriş.Kayıtlı olan e-mail'inizi ve şifrenizi kontrol ediniz.");
                        break;
                    }
                    break;
                case 3:
                    System.exit(0);
                    System.out.println("Çıkış yapılıyor...");
                    break;
            }
            if (isAuthenticated) {
                userMenu(fullName, identityNumber, password, email);
            }

        }

    }

    public static String[][] patronPlus() {
        String[][] newBooks = new String[books.length + 1][4];
        for (int i = 0; i < books.length; i++) {
            for (int j = 0; j < 4; j++) {
                newBooks[i][j] = books[i][j];
            }
            books=newBooks;
        }
        return books;

    }

    public static String[][] transactionsPlus() {
        String[][] newTransacions = new String[transactions.length + 1][4];
        for (int i = 0; i < transactions.length; i++) {
            for (int j = 0; j < 4; j++) {
                newTransacions[i][j] = transactions[i][j];
            }
        transactions=newTransacions;
        }

        return transactions;
    }


    private static String deleteUser(String patronsTC) {
        int bookIndex = -1;

        for (int i = 0; i < patrons.length; i++) {
            if (patrons[i][1].equalsIgnoreCase(patronsTC)) {
                bookIndex = i;
                break;
            }
        }
        if (bookIndex != -1) {
            String[][] newpatrons = new String[patrons.length - 1][4];
            int newIndex = 0;
            for (int i = 0; i < patrons.length; i++) {
                if (bookIndex != i) {
                    for (int k = 0; k < 4; k++) {
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

    private static String checkBookReturnDeadline(String customerTc) {

        for (int i = 0; i < patrons.length; i++) {
            String kullaniciTC = patrons[i][1];
            if (kullaniciTC != null) {
                if (kullaniciTC.equalsIgnoreCase(customerTc)) {
                    System.out.println(customerTc + " T.C numarasına sahip kişi bulundu.");

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

    private static String updatePatronInfo(String updateSearchPatronTC, String fullName, String updateTC, String eMail, String password) {

        int userListİndex = -1;
        for (int i = 0; i < patrons.length; i++) {
            String searchPatrons = patrons[i][1];
            userListİndex = i;
            if (searchPatrons.equalsIgnoreCase(updateSearchPatronTC)) {
                System.out.println("Aranılan kişi bulundu.\n");
                break;

            } else {
                System.out.println("Aranılan kişi bulunamadı.");
            }
        }
        System.out.println("Arama tamamlandı.");
        System.out.println("Patronun tam ismi: " + patrons[userListİndex][0]);
        System.out.println("Patron T.C       : " + patrons[userListİndex][1]);
        System.out.println("Patron E-posta   : " + patrons[userListİndex][2]);
        System.out.println("Patron şifre     : " + patrons[userListİndex][3] + "\n");

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
            books=newBooks;
        }
        return newBooks;

    }

    public static void userMenu(String fullName, String tc, String password, String email) {
        Scanner scanner = new Scanner(System.in);
        LibraryManagementSystem lms = new LibraryManagementSystem();
        String author;
        while (true) {
            System.out.println("1.  Mevcut Kitapları Görüntüle");
            System.out.println("2.  Kitap Ekle");
            System.out.println("3.  Kitap Sil");
            System.out.println("4.  Kitap Ara");
            System.out.println("5.  Kitap Görüntüle");
            System.out.println("6.  Kitap Ödünç Al");
            System.out.println("7.  Kitap Rezerve Et");
            System.out.println("8.  Kitap İadesi Yap");
            System.out.println("9.  Kitap İade Kontrol");
            System.out.println("10. Raporları Görüntüle");
            System.out.println("11. Kitap Tavsiyeleri Oluştur");
            System.out.println("12. Üye Bilgilerini Güncelle");
            System.out.println("13. Kitap Bilgisi Güncelle");
            System.out.println("14. Kullanıcı Sil");
            System.out.println("15. Çıkış");

            System.out.print("Seçiminizi Girin: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    lms.viewAvailableBooks();
                    break;
                case "2":
                    System.out.print("Kitap başlığını girin: ");
                    String title = scanner.next();
                    System.out.print("Yazarı girin: ");
                    author = scanner.next();
                    System.out.print("Sayfa sayısını girin: ");
                    String page = scanner.next();
                    System.out.print("ISBN'yi girin: ");
                    String isbn = scanner.next();
                    lms.addBook(title, author, page, isbn);
                    break;
                case "3":
                    System.out.print("Silmek istediğiniz kitabın ISBN'sini girin: ");
                    String deleteISBN = scanner.next();
                    lms.deleteBook(deleteISBN);
                    break;
                case "4":
                    System.out.print("Görüntülemek istediğiniz kitabın adını girin: ");
                    String viewBookName = scanner.next();
                    lms.bookview(viewBookName);
                    break;
                case "5":
                    System.out.print("Kitabın ISBN'sini girin: ");
                    String searcBookISBN = scanner.next();
                    lms.searchBooks(searcBookISBN);
                    break;
                case "6":
                    System.out.print("Kitap ismini girin: ");
                    String bookName = scanner.next();
                    System.out.print("Kitabın ISBN'sini girin: ");
                    String bookISBN = scanner.next();
                    lms.checkOutBook(tc, bookName, bookISBN);
                    break;
                case "7":
                    System.out.print("Kitabın ISBN'sini girin: ");
                    String reserveBookISBN = scanner.next();
                    System.out.println("Rezervasyon süresini girin: ");
                    int reserveBookDay = scanner.nextInt();
                    lms.reserveBook(fullName, tc, reserveBookISBN, reserveBookDay);
                    break;
                case "8":
                    System.out.print("Kitabın ISBN'sini girin: ");
                    String returnBookISBN = scanner.next();
                    lms.bookReturn(tc, returnBookISBN);
                    break;
                case "9":
                    System.out.print("Ödünç alınan kitabın ISBN'sini girin: ");
                    String checkDeadlineISBN = scanner.next();
                    lms.checkBookReturnDeadline(checkDeadlineISBN);
                    break;
                case "10":
                    lms.generateReports();
                    break;
                case "11":
                    lms.generateBookRecommendations(tc);
                    break;
                case "12":
                    System.out.print("Yeni tam adı girin: ");
                    String fullNames = scanner.next();
                    System.out.print("Yeni TC girin      : ");
                    String updatedTC = scanner.next();
                    System.out.print("Yeni E-posta girin : ");
                    String updatedEmail = scanner.next();
                    System.out.print("Yeni şifre girin   : ");
                    String updatedPassword = scanner.next();
                    lms.updatePatronInfo(tc, fullNames, updatedTC, updatedEmail, updatedPassword);
                    break;
                case "13":
                    System.out.print("Güncellemek istediğiniz kitabın ISBN'sini girin: ");
                    String updateISBN = scanner.next();
                    System.out.print("Yeni kitap başlığını girin: ");
                    String updateTitle = scanner.next();
                    System.out.print("Yeni yazar adını girin: ");
                    String updateAuthor = scanner.next();
                    System.out.print("Yeni sayfa sayısını girin: ");
                    String updatePage = scanner.next();
                    lms.updateBook(updateISBN, updateTitle, updateAuthor, updatePage);
                    System.out.println("Kitap bilgileri güncellendi.");
                    break;
                case "14":
                    System.out.print("Silmek istediğiniz kullanıcının TC'sini girin: ");
                    String deletePatronTC = scanner.next();
                    lms.deleteUser(deletePatronTC);
                    break;
                case "15":
                    System.out.println("Kütüphane Yönetim Sisteminden çıkılıyor. Hoşça kal!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Geçersiz seçim. Lütfen geçerli bir seçenek girin.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Merhaba");
        login();
    }

    public static void viewAvailableBooks() {
        boolean kitapVar = false;
        for (int i = 0; i < books.length; i++) {
            boolean nulll=true;
            for (int j = 0; j < books[i].length; j++) {
                if (books[i][j] != null) {
                    nulll = false;
                    break;
                }
            }
            if (!nulll) {
                kitapVar = true;
                System.out.println("\nKitabın ismi          : " + books[i][0]);
                System.out.println("Kitabın yazarı        : " + books[i][1]);
                System.out.println("Kitabın sayfası sayısı: " + books[i][2]);
                System.out.println("Kitabın ISBN numarası : " + books[i][3]);
            }
        }

        if (!kitapVar) {
            System.out.println("Kütüphanede kitap yoktur.");
        }
        System.out.println();
    }


    public static void generateReports() {
        int totalBookss = 0;
        if (bookQuantity == 0) {
            System.out.println("Kütühanede kitap sayısı 0'dır.");
        } else {
            System.out.printf("%-20s %-20s %-20s %-20s%n", "Kitap İsmi", "Yazar İsmi", "Kitap Sayfası", "ISBN");
            for (int i = 0; i < bookQuantity; i++) {
                System.out.printf("%-20s %-20s %-20s %-20s%n", books[i][0], books[i][1], books[i][2], books[i][3]);
                totalBookss++;
            }
            System.out.println();
            System.out.println("Toplam kitap sayısı : " + totalBookss);
        }
    }


    public static void deleteBook(String ISBN) {
        int isfindIndex = -1;
        if (bookQuantity == 0) {
            System.out.println("Kütühanede kitap sayısı 0'dır.");
        }
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][3].equals(ISBN)) {
                isfindIndex = i;
                break;
            }
        }
        if (isfindIndex != -1) {
            String[][] newDeleteBook = new String [books.length-1][4];
            int Index=-1;
            for (int i = 0; i < books.length; i++) {
                if (isfindIndex!=i){
                    for (int j = 0;j<books[j].length;j++){
                        if (books[i]!=null){
                            newDeleteBook[Index]=books[i];
                        }
                    }
                    Index++;
                }

            }
            books=newDeleteBook;
            System.out.println("Kitap Listesi Güncellendi. ");
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

    public static void reserveBook(String fullName, String patronTC, String bookISBN, int reservationDays) {
        boolean isBookAvailable = bookAvaible(bookISBN);
        if (isBookAvailable) {
            LocalDate reservationDate = LocalDate.now();
            LocalDate dueDate = reservationDate.plusDays(reservationDays);
            System.out.println("Kullanıcı ismi: " + fullName);
            System.out.println("Kullanıcı TC: " + patronTC);
            System.out.println("Kitap rezerve edildi. Lütfen " + dueDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                    " tarihine kadar alınız.");
        } else {
            System.out.println("Belirtilen ISBN'ye sahip kitap bulunamadı.");
        }
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

    public static void hataliGiris(String id, String sifre) {
        boolean girisBasarili = false;
        for (int i = 0; i < patrons.length; i++) {
            if (id.equals(patrons[i][2]) && sifre.equals(patrons[i][3])) {
                girisBasarili = true;
                System.out.println("Giriş Başarılı");
                break;
            }
        }
        if (!girisBasarili) {
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

    public static String checkOutBook(String tc, String bookName, String bookISBN) {
        //aranılan obje bulma

        boolean bookkk = false;
        for (String[] book : books) {
            if (book[0] != null && book[0].equalsIgnoreCase(bookName.trim())) {
                System.out.println(book[0] + "  adında bir kitap var. Yazar :" + book[1]);
                bookkk = true;

                if (patrons.length > transactionQuantity) {
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
                                for (int k = 0; k < books.length; k++) {
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

    public static void bookReturn(String tcNo, String bookISBN) {
        int transactionIndex = -1;
        for (int i = 0; i < transactions.length; i++) {
            if (transactions[i][0].equalsIgnoreCase(tcNo) && transactions[i][1].equals(bookISBN)) {
                transactionIndex = i;
                break;
            }
        }
        if (transactionIndex != -1) {
            for (int i = transactionIndex; i < transactions.length - 1; i++) {
                transactions[i] = transactions[i + 1];
            }
            transactions[transactions.length - 1] = null;
            System.out.println("Kitap iade edildi ve ilgili işlem kaydı silindi.");
        } else {
            System.out.println("Kitap iade edilemedi, ilgili işlem kaydı bulunamadı.");
        }
    }

    public static void requestBook(String bookName, String authorName) {
        int page = randomPage();
        int bookId = randomBookId();
        System.out.println("Kitap talebiniz alındı.");
        System.out.println("Kitap Adı: " + bookName);
        System.out.println("Kitap Yazarı: " + authorName);
        System.out.println("Kitap Sayfa Sayısı: " + page);
        System.out.println("Kitap Id: " + bookId);
    }

    public static int randomPage() {
        return (int) (Math.random() * 901) + 100;
    }

    public static int randomBookId() {
        return (books.length + 1) * 5 + 100;
    }

    public static void searchBooks(String searchCriteria) {

        boolean toFind = false;

        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][0].equalsIgnoreCase(searchCriteria) || books[i][1].equalsIgnoreCase(searchCriteria) || books[i][2].equalsIgnoreCase(searchCriteria)) {
                System.out.println("Kitap Bulundu!");
                System.out.println("Başlık: " + books[i][0]);
                System.out.println("Yazar: " + books[i][1]);
                System.out.println("ISBN: " + books[i][2]);
                toFind = true;
                break;
            }
        }
        if (!toFind) {
            System.out.println("Kitap bulunamadı.");
        }
    }

    public static void checkPatronEligibilityForCheckout(String patronId) {
        checkBookReturnDeadline(patronId);
        String lastBookId = null;
        for (int i = 0; i < patronQuantity; i++) {
            if (patrons[i][0].equals(patronId)) {
                lastBookId = patrons[i][3];
                break;
            }
        }

        if (lastBookId == null) {
            System.out.println("Üzgünüz, patron id bulunamadı.");
            return;
        }

        for (int j = 0; j < transactionQuantity; j++) {
            if (transactions[j][0].equals(lastBookId)) {
                String dueDate = transactions[j][3];

                if (dueDate.compareTo(dueDate) <= 0) {
                    System.out.println("Üzgünüz, kitap iade edene kadar yeni bir kitap alamazsınız.");
                    return;
                }

                LocalDate bookDueDate = LocalDate.parse(dueDate);
                LocalDate currentDate = LocalDate.now();

                if (currentDate.isAfter(bookDueDate)) {
                    System.out.println("Üzgünüz, gecikmiş kitabı iade edene kadar yeni bir kitap alamazsınız.");
                    return;
                }
            }
        }
        System.out.println("Yeni bir iadeya uygunsunuz.");
    }
}

