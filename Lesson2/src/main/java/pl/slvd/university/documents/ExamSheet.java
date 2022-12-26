package pl.slvd.university.documents;

import pl.slvd.university.administration.ExamBoard;

import java.util.Arrays;

import static pl.slvd.university.Main.LOG;

public class ExamSheet {
    public static int[] grades = ExamBoard.rate();

    public static void getGrades() {
        System.out.println("Your exam grades (0-100): \n");
        LOG.info("Grades are given by Random");
        System.out.println(Arrays.toString(grades).replace("[", "").replace("]", ""));
    }
}