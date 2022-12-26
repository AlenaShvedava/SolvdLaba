package pl.slvd.university;

import pl.slvd.university.administration.AdmissionsOffice;
import pl.slvd.university.administration.Deanery;
import pl.slvd.university.administration.ExamBoard;
import pl.slvd.university.departments.Faculty;
import pl.slvd.university.departments.Speciality;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.slvd.university.people.Applicant;

import java.util.*;

import static pl.slvd.university.administration.AdmissionsOffice.applicants;
import static pl.slvd.university.administration.ExamBoard.MIN_PASS_SCORE;
import static pl.slvd.university.documents.ExamSheet.grades;

public class Main {
    public static final Logger LOG = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        LOG.info("Program start");
        System.out.println("\nWelcome to the University of Arts!\nHere you can see the Faculties of the University: ");
        LOG.info("The list of Faculties is displayed");
        for (Faculty categories : Faculty.values()) {
            System.out.println(categories);
        }
        System.out.println("\nWrite what specialty you want to study: "); //make a choice of faculty and specialty
        LOG.info("The list of Specialities is displayed");
        for (Speciality type : Speciality.values()) {
            System.out.println(type);
        }
        LOG.warn("Information input is required. Possible input error");
        Scanner input = new Scanner(System.in);
        String yourSpeciality = input.next().toUpperCase();
        for (Speciality type : Speciality.values()) {
            String yourFaculty;
            if (yourSpeciality.equals(type.name())) {
                yourFaculty = String.valueOf(type.getCategory());
                LOG.info("Data on the chosen Faculty and Specialty are confirmed. List of exams issued");
                System.out.printf("Ok. Your faculty is %s. Your have to pass the exams: %s.\n", yourFaculty, type.getExams());
                AdmissionsOffice.registration(yourFaculty, yourSpeciality);
                List<Applicant> sortedList = applicants.stream().filter(e -> e.getSpeciality().equalsIgnoreCase(yourSpeciality)).toList();
                LOG.info("Applicant going to exams");
                ExamBoard.passExam();
                if (!(grades.get(0) < MIN_PASS_SCORE || grades.get(1) < MIN_PASS_SCORE || grades.get(2) < MIN_PASS_SCORE)) {
                    LOG.info("The list of applicants is transferred to the Deanery");
                    Deanery.sortByGrades(sortedList, AdmissionsOffice.firstLastName, type.getNumOfBudgetPlaces(), type.getNumOfPaidPlaces(), yourSpeciality);
                }
            }
        }
    }
}