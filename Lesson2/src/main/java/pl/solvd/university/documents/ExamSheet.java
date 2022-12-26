package pl.solvd.university.documents;

import pl.solvd.university.administration.ExamBoard;
import pl.solvd.university.state.SeeResults;

import java.io.IOException;
import java.util.ArrayList;

import static pl.solvd.university.Main.*;

public class ExamSheet {
    public static ArrayList<Integer> grades = ExamBoard.rate();

    public static ArrayList<Integer> getGrades() throws IOException {
        LOG.info("Exam grades from ExamSheet #2326 were received");
        applicant.changeActivity(activity, new SeeResults());
        applicant.save();
        return grades;
    }

    public static void scoreCard() {
        applicant.setValues();
        applicant.getSum();
        System.out.println("Your exam grades (0-100):");
        System.out.println(grades.toString().replace("[", "").replace("]", "") + "\n");
    }
}
