package pl.solvd.university.administration;

import pl.solvd.university.departments.Faculty;
import pl.solvd.university.departments.Speciality;
import pl.solvd.university.documents.Pass;
import pl.solvd.university.people.Applicant;
import pl.solvd.university.state.PassExams;
import pl.solvd.university.state.Registration;
import pl.solvd.university.state.SaveLoadFiles;

import java.io.IOException;
import java.util.*;

import static pl.solvd.university.Main.*;
import static pl.solvd.university.utils.Utils.*;

public class AdmissionsOffice {
    public static List<Applicant> applicants = new ArrayList<>();

    public static void chooseSpeciality() throws IOException, ClassNotFoundException {
        SaveLoadFiles.showInformationFromFile("Lesson2/src/main/resources/state.bin");
        System.out.println("\nHere you can see the Faculties of the University: ");
        LOG.info("The list of Faculties is displayed");
        for (Faculty categories : Faculty.values()) {
            System.out.println(categories);
        }
        System.out.println("\nHere you can see the Specialities of the University: ");
        LOG.info("The list of Specialities is displayed");
        int i = 1;
        for (Speciality type : Speciality.values()) {
            System.out.println((i) + ". " + type);
            i++;
        }
        System.out.println("\nWrite what specialty you want to study (1,2,3,4):\n");
        Scanner input = new Scanner(System.in);
        switch (input.next()) {
            case "1" -> applicant.setSpeciality("CONCERT_PERFORMER");
            case "2" -> applicant.setSpeciality("VOCAL_SINGER");
            case "3" -> applicant.setSpeciality("MUSICAL_DIRECTOR");
            case "4" -> applicant.setSpeciality("CHOREOGRAPHER");
            default -> {
                LOG.info("Exception: You entered an invalid value. Make a choice of Specialty and Faculty again");
                System.out.println("Exception: You entered an invalid value. Please review the list of Faculties again and choose your Specialty");
                chooseSpeciality();
            }
        }
        for (Speciality type : Speciality.values()) {
            if (applicant.getSpeciality().equals(type.name())) {
                applicant.setFaculty(String.valueOf(type.getCategory()));
                LOG.info("Data on the chosen Faculty and Specialty are confirmed. List of exams issued");
                System.out.printf("Ok. Your faculty is %s. Your have to pass the exams: %s.\n", applicant.getFaculty(), type.getExams());
                applicant.setActivity(new Registration());
                applicant.saveActivityAndApplicantInfoToStateFiles();
            }
        }
    }

    public static void registration() throws Exception {
        SaveLoadFiles.showInformationFromFile("Lesson2/src/main/resources/state.bin");
        LOG.info("Requested confirmation of further registration of the Applicant in the chosen Specialty");
        System.out.printf("Apply to faculty: %s, speciality: %s? (yes/no) \n", applicant.getFaculty(), applicant.getSpeciality());
        Scanner faculty = new Scanner(System.in);
        String chooseDone = faculty.next().toUpperCase();
        try {
            checkYesNoInput(chooseDone);
        } catch (Exception e) {
            System.out.println("Problem occurred: " + e);
            registration();
        }
        switch (chooseDone.toUpperCase(Locale.ROOT)) {
            case "NO": {
                LOG.info("Applicant chose the wrong specialty. Start over");
                System.out.println("To choose another specialty, start over");
                main(null);
            }
            case "YES": {
                LOG.info("Speciality is chosen. Request additional information about the Applicant for registration");
                System.out.println("Enter your First name \n");
                Scanner scan = new Scanner(System.in);
                String name = scan.next();
                try {
                    checkLettersInput(name);
                } catch (Exception e) {
                    System.out.println("Problem occurred: " + e);
                    registration();
                    break;
                }
                applicant.setFirstName(name);
                System.out.println("Enter your Last name \n");
                String surname = scan.next();
                try {
                    checkLettersInput(surname);
                } catch (Exception e) {
                    System.out.println("Problem occurred: " + e);
                    registration();
                    break;
                }
                applicant.setLastName(surname);
                System.out.println("Enter your date of birth\n");
                StringBuilder dateOfBirth = new StringBuilder();
                System.out.println("day(dd):");
                String day = scan.next();
                System.out.println("month(MM):");
                String month = scan.next();
                System.out.println("year(yyyy(1900/2000)):");
                String year = scan.next();
                try {
                    checkDateInput(day, month, year);
                } catch (Exception e) {
                    System.out.println("Problem occurred: " + e);
                    registration();
                    break;
                }
                dateOfBirth.append(day).append(".");
                dateOfBirth.append(month).append(".");
                dateOfBirth.append(year);
                applicant.setDateOfBirth(String.valueOf(dateOfBirth));
            }
            applicants.add(applicant);
            LOG.info("The Applicant is registered in the general list of applicants");
            System.out.println("Congratulations! You have registered and are on the List of Applicants \nLIST OF APPLICANTS:\n");
            for (Applicant value : applicants) {
                System.out.println(value);
            }
            Pass.givePassToApplicant(applicant.getFirstLastName());
            applicant.setActivity(new PassExams());
            applicant.saveActivityAndApplicantInfoToStateFiles();
        }
    }

    public static void returnOfDocuments() throws IOException, ClassNotFoundException {
        SaveLoadFiles.showInformationFromFile("Lesson2/src/main/resources/state.bin");
        Pass.takePassFromAnApplicant();
        System.out.println("Your documents have been returned");
    }
}
