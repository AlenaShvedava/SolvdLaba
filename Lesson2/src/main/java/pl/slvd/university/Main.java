package pl.slvd.university;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import pl.slvd.university.administration.AdmissionsOffice;
import pl.slvd.university.administration.Deanery;
import pl.slvd.university.administration.ExamBoard;
import pl.slvd.university.departments.Faculty;
import pl.slvd.university.departments.Speciality;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.slvd.university.people.Applicant;
import pl.slvd.university.state.Activity;
import pl.slvd.university.state.ReadRules;
import pl.slvd.university.state.SaveLoadFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static pl.slvd.university.administration.AdmissionsOffice.applicants;
import static pl.slvd.university.administration.ExamBoard.MIN_PASS_SCORE;
import static pl.slvd.university.documents.ExamSheet.grades;

public class Main {
    public static final Logger LOG = LogManager.getLogger(Main.class.getName());
    static Activity activity = new ReadRules();
    public static Applicant applicant;
    public static void main(String[] args) throws Exception {
        LOG.info("Program start");
        applicant = new Applicant((short) 0, "unknown", "unknown", "not chosen", "not chosen");
        System.out.println("\nWELCOME TO THE UNIVERSITY OF ARTS!\nPlease read the procedure for conducting entrance examinations at the University\n");
        applicant.setActivity(activity);
        applicant.go();
        readFile();
        System.out.println("\nConfirm that you have read the rules (yes/no)");
        LOG.warn("Information requested. Possible input error");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        if ((!(answer.toUpperCase(Locale.ROOT).equals("YES")) && (!(answer.toUpperCase(Locale.ROOT).equals("NO"))))) {
            LOG.error("Exception: Invalid input. Start over");
            SaveLoadFiles.load("Lesson2/src/main/resources/state.bin");
            main(null);
            throw new Exception("You entered an invalid value");
        } else {
            switch (answer) {
                case "NO" -> {
                    LOG.info("Refused");
                    main(null);
                }
                case "YES" -> {
                    LOG.info("Confirmed");
                    applicant.changeActivity();
                    applicant.go();
                    SaveLoadFiles.load("Lesson2/src/main/resources/state.bin");
                    System.out.println("\nHere you can see the Faculties of the University: ");
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
                    applicant.setSpeciality(input.next().toUpperCase());
                    for (Speciality type : Speciality.values()) {
                        if (applicant.getSpeciality().equals(type.name())) {
                            applicant.setFaculty(String.valueOf(type.getCategory()));
                            LOG.info("Data on the chosen Faculty and Specialty are confirmed. List of exams issued");
                            System.out.printf("Ok. Your faculty is %s. Your have to pass the exams: %s.\n", applicant.getFaculty(), type.getExams());
                            AdmissionsOffice.registration(applicant.getFaculty(), applicant.getSpeciality());
                            List<Applicant> sortedList = applicants.stream().filter(e -> e.getSpeciality().equalsIgnoreCase(applicant.getSpeciality())).toList();
                            applicant.changeActivity();
                            applicant.go();
                            SaveLoadFiles.load("Lesson2/src/main/resources/state.bin");
                            LOG.info("Applicant going to exams");
                            ExamBoard.passExam();
                            if (!(grades.get(0) < MIN_PASS_SCORE || grades.get(1) < MIN_PASS_SCORE || grades.get(2) < MIN_PASS_SCORE)) {
                                LOG.info("The list of applicants is transferred to the Deanery");
                                Deanery.sortByGrades(sortedList, applicant.getFirstLastName(), type.getNumOfBudgetPlaces(), type.getNumOfPaidPlaces(), applicant.getSpeciality());
                            }
                        }
                    }
                }
            }
        }
    }
    private static void readFile() throws IOException {
        try {
            File start = new File("Lesson2/src/main/resources/start.txt");
            List<String> lines = FileUtils.readLines(start, StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            LOG.error("FileNotFoundException");
            System.out.println("File not found" + e);
        }
    }
}
