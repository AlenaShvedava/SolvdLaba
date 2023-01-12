package pl.solvd.university;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.university.administration.AdmissionsOffice;
import pl.solvd.university.administration.Deanery;
import pl.solvd.university.administration.ExamBoard;
import pl.solvd.university.departments.Speciality;
import pl.solvd.university.people.Applicant;
import pl.solvd.university.people.Dean;
import pl.solvd.university.people.Professor;
import pl.solvd.university.state.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static pl.solvd.university.administration.AdmissionsOffice.applicants;
import static pl.solvd.university.administration.ExamBoard.MIN_PASS_SCORE;
import static pl.solvd.university.departments.Faculty.*;
import static pl.solvd.university.documents.ExamSheet.grades;
import static pl.solvd.university.utils.Utils.checkYesNoInput;

public class Main {
    public static final Logger LOG = LogManager.getLogger(Main.class.getName());
    public static Applicant applicant;

    public static void main(String[] args) throws Exception {
        LOG.info("Program start");
        applicant = new Applicant();
        System.out.println("\nWELCOME TO THE UNIVERSITY OF ARTS!\nPlease read the procedure for conducting entrance examinations at the University\n");
        LOG.info("First of all, the Applicant should read about the procedure for conducting entrance examinations at the University");
        applicant.setActivity(new ReadRules());
        applicant.saveActivityAndApplicantInfoToStateFiles();
        readUniversityRules();
        System.out.println("\nConfirm that you have read the rules (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        try {
            checkYesNoInput(answer);
        } catch (Exception e) {
            System.out.println("Problem occurred: " + e);
            main(null);
        }
        switch (answer) {
            case "NO" -> {
                LOG.info("The Applicant has not read the University Rules. Start from beginning and just read it");
                main(null);
            }
            case "YES" -> {
                LOG.info("The Applicant received information about the stages of admission to the University");
                applicant.setActivity(new ChoosingSpeciality());
                applicant.saveActivityAndApplicantInfoToStateFiles();
                AdmissionsOffice.chooseSpeciality();
                Professor professor1 = new Professor("Mario", "Hill");
                Professor professor3 = new Professor("Jon", "King");
                Professor professor2 = new Professor("Lorraine", "Wilson");
                Professor professor4 = new Professor("Matthew", "Banks");
                Professor professor5 = new Professor("John", "Woods");
                Professor professor6 = new Professor("Matthew", "Banks");
                professor2 = new Dean(professor2.firstName, professor1.lastName);
                professor3 = new Dean(professor3.firstName, professor2.lastName);
                professor5 = new Dean(professor5.firstName, professor3.lastName);
                professor6 = new Dean(professor6.firstName, professor4.lastName);
                INSTRUMENT.setDean((Dean) professor5);
                VOCAL.setDean((Dean) professor2);
                THEATRE.setDean((Dean) professor6);
                CHOREOGRAPHY.setDean((Dean) professor3);
                applicants.add(new Applicant((short) 1, "Mary", "Peterson", "22.10.2000", "INSTRUMENT", "concert_performer", new ArrayList<>(List.of(56, 44, 95)), applicant.getSum()));
                applicants.add(new Applicant((short) 2, "Barbara", "Scott", "14.03.2002", "VOCAL", "vocal_singer", new ArrayList<>(List.of(66, 40, 39)), applicant.getSum()));
                applicants.add(new Applicant((short) 3, "Andre", "Roberts", "22.10.2003", "THEATRE", "musical_director", new ArrayList<>(List.of(79, 64, 38)), applicant.getSum()));
                applicants.add(new Applicant((short) 4, "John", "Hampton", "05.11.2000", "VOCAL", "vocal_singer", new ArrayList<>(List.of(46, 83, 58)), applicant.getSum()));
                applicants.add(new Applicant((short) 5, "Erin", "Jones", "23.07.2001", "CHOREOGRAPHY", "choreographer", new ArrayList<>(List.of(52, 57, 45)), applicant.getSum()));
                applicants.add(new Applicant((short) 6, "Mildred", "Blake", "18.09.2002", "CHOREOGRAPHY", "choreographer", new ArrayList<>(List.of(94, 89, 86)), applicant.getSum()));
                applicants.add(new Applicant((short) 7, "Amanda", "Patrick", "02.05.2000", "INSTRUMENT", "concert_performer", new ArrayList<>(List.of(79, 87, 100)), applicant.getSum()));
                applicants.add(new Applicant((short) 8, "Margaret", "Williams", "31.12.1999", "instrument", "concert_performer", new ArrayList<>(List.of(31, 44, 68)), applicant.getSum()));
                applicants.add(new Applicant((short) 9, "Debra", "Roberts", "27.03.2003", "INSTRUMENT", "concert_performer", new ArrayList<>(List.of(100, 88, 95)), applicant.getSum()));
                applicants.add(new Applicant((short) 10, "Julie", "Hines", "09.11.2001", "VOCAL", "vocal_singer", new ArrayList<>(List.of(56, 44, 95)), applicant.getSum()));
                applicants.add(new Applicant((short) 11, "Chad", "Crawford", "10.02.2002", "THEATRE", "musical_director", new ArrayList<>(List.of(83, 40, 64)), applicant.getSum()));
                applicants.add(new Applicant((short) 12, "Rhonda", "Henderson", "26.07.2002", "INSTRUMENT", "concert_performer", new ArrayList<>(List.of(77, 53, 80)), applicant.getSum()));
                applicants.add(new Applicant((short) 13, "George", "Phillips", "13.03.2001", "VOCAL", "vocal_singer", new ArrayList<>(List.of(83, 90, 75)), applicant.getSum()));
                applicants.add(new Applicant((short) 14, "Ruth", "White", "20.01.2000", "CHOREOGRAPHY", "choreographer", new ArrayList<>(List.of(85, 60, 58)), applicant.getSum()));
                AdmissionsOffice.registration();
                LOG.info("Applicant preparing to take exams");
                SaveLoadFiles.showInformationFromFile("Lesson2/src/main/resources/state.bin");
                List<Applicant> sortedApplicantsBySpecialityList = applicants.stream().filter(e -> e.getSpeciality().equalsIgnoreCase(applicant.getSpeciality())).toList();
                System.out.println("\nExam Board:\nNow you need to visit the pre-exam consultation");
                Professor.consult();
                ExamBoard.passExam();
                if (!(grades.get(0) < MIN_PASS_SCORE || grades.get(1) < MIN_PASS_SCORE || grades.get(2) < MIN_PASS_SCORE)) {
                    LOG.info("The list of applicants is transferred to the Deanery");
                    Deanery.summingUpTheResultsOfEntranceExaminations(sortedApplicantsBySpecialityList, Speciality.valueOf(applicant.getSpeciality()).getNumOfBudgetPlaces(), Speciality.valueOf(applicant.getSpeciality()).getNumOfPaidPlaces(), applicant.getSpeciality());
                    LOG.info("Admission tests completed");
                }
            }

        }
    }

    private static void readUniversityRules() throws IOException {
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
