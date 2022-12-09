package pl.slvd.university;

import java.util.Random;
import java.util.Scanner;

import static pl.slvd.university.ExamSheet.grades;

public class ExamBoard {
    public static final byte MIN = 39;

    public static void passExam() {
        System.out.printf("It's time for the exams. You have 3 exams. Passing score for each exam is %d \n", ExamBoard.MIN);
        System.out.println("Are you ready to take your exams? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        switch (answer) {
            case "NO" -> passExam();
            case "YES" -> {
                ExamSheet.getGrades();
                String result = grades[0] < MIN || grades[1] < MIN || grades[2] < MIN ? "Unfortunately, you did not pass the exam and did not enter the University.\n" : "Congratulations! You have passed all the exams.\n";
                System.out.println(result);//asking for grades for our exams

            }
        }
    }

    public static int[] rate() {
        return new Random().ints(3, 37, 100).toArray();
    }
}