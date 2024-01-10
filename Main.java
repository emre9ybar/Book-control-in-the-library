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
        static String[][] patrons = new String[INDEX][4];
        static String[][] transactions = new String[INDEX][3];
        static int transcationQuantit = 0;
    static int patronQuantity = 0;
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

                    if (patrons.length > patronQuantity) {
                        transactions[patronQuantity][0] = tc;
                        transactions[patronQuantity][1] = bookISBN;
                        transactions[patronQuantity][2] = LocalDate.now().toString();
                        patronQuantity++;
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
                                        newBooks[newIndex++] = books[i];
                                    }
                                }
                            }
                            books = newBooks;
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


    public static void main(String[] args) {


    }
}