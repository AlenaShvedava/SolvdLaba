package pl.slvd.university.documents;

import pl.slvd.university.administration.ExamBoard;

import java.util.ArrayList;

import static pl.slvd.university.Main.LOG;

public class ExamSheet {
    public static ArrayList<Integer> grades = ExamBoard.rate();

    public static void getGrades() {
        LOG.info("Exam grades from ExamSheet #2326 were shown");
    }

    public static void scoreCard() {
        System.out.println("Your exam grades (0-100):");
        System.out.println(grades + "\n");
    }
}
