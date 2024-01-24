public static void checkPatronEligibilityForCheckout( int Patronid ) {
        checkBookReturnDeadline(String.valueOf(Patronid));
        String patronId = null;
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

                if (dueDate.equalsIgnoreCase("")) {
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
            System.out.println("Yeni bir iadeya uygunsunuz.");
            return;
        }
    }
