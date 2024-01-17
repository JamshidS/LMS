 public static String bookReturn(String name, String bookName, String bookISBN) {
        int kitapIndex = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i][0] != null && books[i][3] != null) {
                if (books[i][0].equalsIgnoreCase(bookName) && books[i][3].equals(bookISBN)) {
                    kitapIndex = i;
                    break;
                }
            }
        }
        if (kitapIndex != -1) {
            int currentQuantity = Integer.parseInt(books[kitapIndex][2]);
            if (currentQuantity > 0) {
                books[kitapIndex][2] = String.valueOf(currentQuantity - 1);
                System.out.println("İşlem başarıyla tamamlandı. Kitap iade edildi.");

                int transactionIndex = -1;
                for (int i = 0; i < transactions.length; i++) {
                    if (transactions[i] != null && transactions[i][0] != null && transactions[i][1] != null && transactions[i][2] != null) {
                        if (transactions[i][0].equalsIgnoreCase(bookName) && transactions[i][1].equals(bookISBN)) {
                            transactionIndex = i;
                            break;
                        }
                    }
                }

                if (transactionIndex != -1) {
                    for (int i = transactionIndex; i < transactions.length - 1; i++) {
                        transactions[i] = transactions[i + 1];
                    }
                    transactions[transactions.length - 1] = null;
                    System.out.println("Kitap iade edildi ve ilgili işlem kaydı silindi.");
                } else {
                    System.out.println("Kitap iade edildi, ancak ilgili işlem kaydı bulunamadı.");
                }
                System.out.println( "İşlem başarıyla tamamlandı. Kitap iade edildi.");
            } else {
                System.out.println("kitap iade edilemedi. kitap stokta yok.");
            }
        } else {
            System.out.println("Kitap iade edilemedi. Belirtilen isim ve numarada kitap bulunamadı veya stokta yok.");
        }
        return "Kitap iade edilemedi. Belirtilen isim ve numarada kitap bulunamadı veya stokta yok.";
    }


