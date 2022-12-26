package pl.slvd.university.administration;

import java.util.Locale;
import java.util.Scanner;

import static pl.slvd.university.Main.LOG;
import static pl.slvd.university.documents.Pass.show;

public class Accounting {
    public enum Bill {
        EDUCATION(2000), LOST_BOOK(15), RETAKE_EXAM(30), HOSTEL(100), SCHOLARSHIP(200);
        private final int price;

        Bill(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }

    static void exceptPayment(Bill category) throws Exception {
        System.out.printf("Accounting:\nNEW BILL:\n%s payment is %d dollars.\n", category.name(), category.getPrice());
        System.out.println("Ready to make a payment?");
        LOG.warn("Information requested. Possible input error");
        Scanner answer = new Scanner(System.in);
        String chooseDone = answer.next();
        if ((!(chooseDone.toUpperCase(Locale.ROOT).equals("YES")) && (!(chooseDone.toUpperCase(Locale.ROOT).equals("NO"))))) {
            LOG.error("Exception: Invalid input");
            throw new Exception("You entered an invalid value");
        } else {
            switch (chooseDone.toUpperCase(Locale.ROOT)) {
                case "NO" -> {
                    LOG.info("Refused");
                    System.out.println("Payment declined. Please try to pay later");
                }
                case "YES" -> {
                    LOG.info("Confirmed");
                    System.out.println("Payment accepted");
                }
            }
        }
    }

    static void agreement(Bill category) throws Exception {
        System.out.printf("Accounting:\n%s payment is %d dollars\n", category.name(), category.getPrice());
        System.out.print("If you decide to study on a paid basis, please,\n-submit your Pass and \n-sign a contract for Paid training\n(yes/no):\n");
        LOG.warn("Information requested. Possible input error");
        Scanner answer = new Scanner(System.in);
        String chooseDone = answer.next();
        if ((!(chooseDone.toUpperCase(Locale.ROOT).equals("YES")) && (!(chooseDone.toUpperCase(Locale.ROOT).equals("NO"))))) {
            LOG.error("Exception: Invalid input");
            agreement(Accounting.Bill.EDUCATION);
            throw new Exception("You entered an invalid value");
        } else {
            switch (chooseDone.toUpperCase(Locale.ROOT)) {
                case "NO" -> {
                    LOG.info("Refused");
                    System.out.println("Ok. You have decided not to study on a Paid basis\nGo to the Admissions Office to return the documents\n");
                    AdmissionsOffice.returnOfDocuments();
                }
                case "YES" -> {
                    LOG.info("Confirmed");
                    show();
                    System.out.println("Ok. The contract for Paid training has been signed. You are enrolled in the University!\n");
                }
            }
        }
    }

    private void toPay() {
    }
}