public class libraryManagementSystem {

    int customerIndex = -1;
    public void custemerToDeleted(String customerToDelete) {
        for (int i = 0; i < patrons.length; i++) {
            String customerTc = patrons[i][2];
            customerIndex = i;
            if (customerTc.equals(customerToDelete)) {
                System.out.println("The customer has been deleted successfully. TR ID:" + customerToDelete);

                String[][] newPatrons = new String[patrons.length - 1][4];
                for (int h = 0; h < customerIndex; h++) {
                    for (int j = 0; j < newPatrons[h].length; j++) {
                        newPatrons[h][j] = patrons[i][j];
                    }
                }
                for (int h = customerIndex + 1; h < patrons.length; h++) {
                    for (int j = 0; j < newPatrons[h - 1].length; j++) {
                        newPatrons[h - 1][j] = patrons[i][j];

                    }

                }
                patrons=newPatrons;

            }
        }

    }









    public static void main(String[] args) {

    }
}
