package pl.solvd.university.administration;

import pl.solvd.university.people.Applicant;
import pl.solvd.university.state.Payment;
import pl.solvd.university.state.ReturnDocuments;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static pl.solvd.university.Main.*;
import static pl.solvd.university.documents.Pass.show;
import static pl.solvd.university.utils.Utils.checkYesNoInput;

public class Accounting {
    public enum Bill {
        EDUCATION(2000);
        private final int price;

        Bill(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }

    static void exceptPayment() {
        System.out.printf("Accounting:\nNEW BILL:\n%s payment is %d dollars\n", Bill.EDUCATION.name(), Bill.EDUCATION.getPrice());
        System.out.println("Ready to make a payment?");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        try {
            checkYesNoInput(answer);
        } catch (Exception e) {
            System.out.println("Problem occurred: " + e);
            exceptPayment();
        }
        switch (answer.toUpperCase(Locale.ROOT)) {
            case "NO" -> {
                LOG.info("Applicant refused to pay now");
                System.out.println("Payment declined. Please try to pay later");
            }
            case "YES" -> {
                LOG.info("Payment was successful");
                System.out.println("Payment accepted");
            }
        }
    }

    static void agreement(List<Applicant> sortedApplicantsCopy) throws Exception {
        System.out.printf("Accounting:\n%s payment is %d dollars\n", Bill.EDUCATION.name(), Bill.EDUCATION.getPrice());
        System.out.print("If you decide to study on a paid basis, please,\n-submit your Pass and \n-sign a contract for Paid training\n(yes/no):\n");
        Scanner scan = new Scanner(System.in);
        String chooseDone = scan.next().toUpperCase();
        try {
            checkYesNoInput(chooseDone);
        } catch (Exception e) {
            System.out.println("Problem occurred: " + e);
            agreement(sortedApplicantsCopy);
        }
        switch (chooseDone.toUpperCase(Locale.ROOT)) {
            case "NO" -> {
                LOG.info("Refused. Applicant goes to return his documents");
                System.out.println("Ok. You have decided not to study on a Paid basis\nGo to the Admissions Office to return the documents\n");
                applicant.setActivity(new ReturnDocuments());
                applicant.saveActivityAndApplicantInfoToStateFiles();
                AdmissionsOffice.returnOfDocuments();
                sortedApplicantsCopy.remove(1);
            }
            case "YES" -> {
                LOG.info("The Applicant agrees to sign a contract for Paid education");
                show(applicant.myPass);
                System.out.println("Ok. The contract for Paid training has been signed\n");
                applicant.setActivity(new Payment());
                exceptPayment();
            }
        }
    }
}
