package pl.slvd.university;

import java.util.Arrays;

public class ExamSheet {
    static int[] grades = ExamBoard.rate();

    public static void getGrades() {
        System.out.println("Your exam grades (0-100): \n");
        System.out.println(Arrays.toString(grades));
    }
}