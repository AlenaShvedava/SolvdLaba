package pl.solvd.university.documents;

import pl.solvd.university.administration.ExamBoard;
import pl.solvd.university.state.SeeResults;

import java.io.IOException;
import java.util.ArrayList;

import static pl.solvd.university.Main.*;

public class ExamSheet {
    public static ArrayList<Integer> grades = ExamBoard.rate();

    public static void getGrades() throws IOException {
        LOG.info("Exam grades from ExamSheet #2326 were received");
        applicant.setActivity(new SeeResults());
        applicant.saveActivityAndApplicantInfoToStateFiles();
    }

    public static void scoreCard() {
        applicant.setValues();
        applicant.getSum();
        System.out.println("Your exam grades (0-100):");
        System.out.println(grades.toString().replace("[", "").replace("]", "") + "\n");
    }
}
