package pl.slvd.university.documents;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static pl.slvd.university.Main.LOG;
import static pl.slvd.university.administration.AdmissionsOffice.firstLastName;

public class Pass {
    private final String name;

    public Pass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void give() throws Exception {
        Pass myPass = new Pass(firstLastName);
        System.out.println("\nAdmission Office:\nSIGN THE CONTRACT:\nYou are going to leave your documents with us.\nIn return, you will be issued an Applicant Pass.\nSign that the documents have been exchanged (yes/no)\n");
        LOG.warn("Information requested. Possible input error");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        if ((!(answer.toUpperCase(Locale.ROOT).equals("YES")) && (!(answer.toUpperCase(Locale.ROOT).equals("NO"))))) {
            LOG.error("Exception: Invalid input. The program is closed");
            throw new Exception("You entered an invalid value");
        } else {
            switch (answer) {
                case "NO" -> {
                    LOG.info("Refused");
                    System.out.println("Signature required");
                    give();
                }
                case "YES" -> {
                    LOG.info("Confirmed");
                    System.out.println("\nYou received an Applicant's pass");
                    show();
                }
            }
        }
    }

    public static void take() {
        System.out.println("Admission Office:\nYou handed over the Applicant's pass");

    }

    public static String center(String string, int length, char pad) {
        StringBuilder sb = new StringBuilder(length);
        sb.setLength((length - string.length() + 1) / 2);
        sb.append(string);
        sb.setLength(length);
        return sb.toString().replace('\0', pad);
    }

    public static void show() {

        System.out.println(" " + (center("", 25, '_') + " "));
        System.out.println("|" + (center("P A S S", 25, ' ') + "|"));
        System.out.println("|" + (center(firstLastName, 25, ' ') + "|"));
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