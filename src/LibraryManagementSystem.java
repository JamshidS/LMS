
        public static void  searchBooks() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Arama kriteri giriniz: ");
            String searchCriteria = scanner.next();

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
  
