package pl.slvd.university.administration;

import pl.slvd.university.documents.ExamSheet;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import static pl.slvd.university.documents.ExamSheet.grades;
import static pl.slvd.university.Main.LOG;

public class ExamBoard {
    public static final byte MIN_PASS_SCORE = 39;

    public static void passExam() throws Exception {
        System.out.printf("It's time for the exams. You have 3 exams. Passing score for each exam is %d \n", ExamBoard.MIN_PASS_SCORE);
        System.out.println("Are you ready to take your exams? (yes/no)");
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
                    passExam();
                }
                case "YES" -> {
                    LOG.info("Confirmed. The Examination Board starts the exam");
                    ExamSheet.getGrades();
                    LOG.info("Examination grades are checked for a passing score");
                    String result = grades[0] < MIN_PASS_SCORE || grades[1] < MIN_PASS_SCORE || grades[2] < MIN_PASS_SCORE ? "Unfortunately, you did not pass the exam and did not enter the University.\n" : "Congratulations! You have passed all the exams.\n";
                    System.out.println(result);//asking for grades for our exams
                    if (grades[0] < MIN_PASS_SCORE || grades[1] < MIN_PASS_SCORE || grades[2] < MIN_PASS_SCORE) {
                        LOG.info("Exiting the program");
                    }
                }
            }
        }
    }

    public static int[] rate() {
        return new Random().ints(3, 37, 100).toArray();
    }
}