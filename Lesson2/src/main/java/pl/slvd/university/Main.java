package pl.slvd.university;

import pl.slvd.university.administration.AdmissionsOffice;
import pl.slvd.university.administration.Deanery;
import pl.slvd.university.administration.ExamBoard;
import pl.slvd.university.departments.Speciality;
import org.apache.logging.log4j.Level;
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
        List<Speciality> specialities = new ArrayList<>();
        System.out.println("\nWelcome to the University of the Arts!\nHere you can see the Faculties of the University: ");
        specialities.add(new Speciality("INSTRUMENT", "CONCERT_PERFORMER", 2, 1, "Instrument, Solfeggio and Sight Reading"));
        specialities.add(new Speciality("VOCAL", "ACADEMIC_SINGER", 2, 1, "Singing, Solfeggio and Piano"));
        specialities.add(new Speciality("THEATRE", "MUSICAL_DIRECTOR", 1, 1, "Acting, Directing Composition Presentation and Musical Literature"));
        specialities.add(new Speciality("CHOREOGRAPHY", "CHOREOGRAPHER", 1, 1, "Folk dance, Creative composition performance, Colloquium"));
        LOG.info("The list of Faculties is displayed");
        for (Speciality speciality : specialities) {
            System.out.println(speciality.getFacultyName());
        }
        System.out.println("\nWrite what specialty you want to study: "); //make a choice of faculty and specialty
        LOG.info("The list of Specialities is displayed");
        for (Speciality speciality : specialities) {
            System.out.println(speciality.getName());
        }
        LOG.warn("Information input is required. Possible input error");
        Scanner input = new Scanner(System.in);
        String yourSpeciality = input.next().toUpperCase();
        try {
            for (int i = 0; i <= specialities.size(); ) {
                if (!(yourSpeciality.equals(specialities.get(i).getName()))) {
                    i++;
                } else break;
            }
        } catch (Exception e) {
            LOG.log(Level.ERROR, "Exception: Invalid input. The program is closed", e);
            System.out.println("You entered an invalid value. Start over");
        }
        for (Speciality speciality : specialities) {
            String yourFaculty;
            if (yourSpeciality.equals(speciality.getName())) {
                yourFaculty = speciality.getFacultyName();
                LOG.info("Data on the chosen Faculty and Specialty are confirmed. List of exams issued");
                System.out.printf("Ok. Your faculty is %s. Your have to pass the exams: %s.\n", yourFaculty, speciality.getExams());
                AdmissionsOffice.registration(yourFaculty, yourSpeciality);
                List<Applicant> sortedList = applicants.stream().filter(e -> e.getSpeciality().equalsIgnoreCase(yourSpeciality)).toList();
                LOG.info("Applicant going to exams");
                ExamBoard.passExam();
                if (!(grades[0] < MIN_PASS_SCORE || grades[1] < MIN_PASS_SCORE || grades[2] < MIN_PASS_SCORE)) {
                    LOG.info("The list of applicants is transferred to the Deanery");
                    Deanery.sortByGrades(sortedList, AdmissionsOffice.firstLastName, speciality.getNumOfBudgetPlaces(), speciality.getNumOfPaidPlaces(), yourSpeciality);
                }
            }
        }
    }
}