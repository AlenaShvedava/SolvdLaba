package pl.slvd.university.administration;

import pl.slvd.university.documents.ExamSheet;
import pl.slvd.university.state.SaveLoadFiles;

import java.util.*;

import static pl.slvd.university.Main.applicant;
import static pl.slvd.university.documents.ExamSheet.grades;
import static pl.slvd.university.Main.LOG;

public class ExamBoard {
    public static final byte MIN_PASS_SCORE = 39;
    static Random random = new Random();

    public static void passExam() throws Exception {
        System.out.printf("\nWell. It's time for the exams.\nYou have 3 exams. Passing score for each exam is %d \n", ExamBoard.MIN_PASS_SCORE);
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
                     applicant.changeActivity();
                     applicant.go();
                    SaveLoadFiles.load("Lesson2/src/main/resources/state.bin");
                    String result = grades.stream().anyMatch(grade -> grade < MIN_PASS_SCORE) ? "Exam Board:\nUnfortunately, you did not pass the exam and did not enter the University." : "Exam Board:\nCongratulations! You have passed all the exams.";
                    System.out.println(result);//asking for grades for our exams
                    ExamSheet.scoreCard();
                    if (grades.stream().anyMatch(grade -> grade < MIN_PASS_SCORE)) {
                        LOG.info("The Applicant goes to return his documents");
                        applicant.changeActivity();
                        applicant.go();
                        SaveLoadFiles.load("Lesson2/src/main/resources/state.bin");
                        AdmissionsOffice.returnOfDocuments();

                    }
                }
            }
        }
    }
    public static ArrayList<Integer> rate() {
        //return new Random().ints(3,37,100).allMatch();
        ArrayList<Integer> numbersGenerated = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            numbersGenerated.add(random.nextInt(37)+62);
        }return numbersGenerated;
    }
}
