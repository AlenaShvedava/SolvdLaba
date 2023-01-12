package pl.solvd.university.documents;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

import static pl.solvd.university.Main.*;
import static pl.solvd.university.utils.Utils.checkYesNoInput;

public class Pass implements Serializable {
    private final String name;

    private Pass(String name) {
        this.name = name;
    }

    public static void givePassToApplicant(String ownerName) {
        applicant.myPass = new Pass(ownerName);
        LOG.info("\nApplicant is issued a pass");
        System.out.println("\nAdmission Office:\nSIGN THE CONTRACT:\nYou are going to leave your documents with us.\nIn return, you will be issued an Applicant Pass.\nSign that the documents have been exchanged (yes/no)\n");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        try {
            checkYesNoInput(answer);
        } catch (Exception e) {
            System.out.println("Problem occurred: " + e);
            givePassToApplicant(applicant.getFirstLastName());
        }
        switch (answer) {
            case "NO" -> {
                LOG.info("The Applicant does not want to give his documents. To pass the exams, you must leave your documents at the Admissions Office");
                System.out.println("Signature required");
                givePassToApplicant(applicant.getFirstLastName());
            }
            case "YES" -> {
                LOG.info("The Applicant agrees to give his documents in exchange for Pass to the exams");
                System.out.println("\nYou received an Applicant's pass");
                show(applicant.myPass);
            }
        }
    }

    public static void takePassFromAnApplicant() {
        System.out.println("Admission Office:\nYou handed over the Applicant's pass");
    }

    public static String center(String string, int length, char pad) {
        StringBuilder sb = new StringBuilder(length);
        sb.setLength((length - string.length() + 1) / 2);
        sb.append(string);
        sb.setLength(length);
        return sb.toString().replace('\0', pad);
    }

    public static void show(Pass myPass) {
        System.out.println(" " + (center("", 25, '_') + " "));
        System.out.println("|" + (center("P A S S", 25, ' ') + "|"));
        System.out.println("|" + (center(myPass.name, 25, ' ') + "|"));
        System.out.println("|" + (center("", 25, '_') + "|"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pass pass = (Pass) o;
        return Objects.equals(name, pass.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
