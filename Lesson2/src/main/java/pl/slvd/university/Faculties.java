package pl.slvd.university;

import java.util.Arrays;

import java.util.Locale;
import java.util.Scanner;

public class Faculties {

    public enum Faculty {
        INSTRUMENT, VOCAL, THEATRE, CHOREOGRAPHY;

    }

    public static class Speciality {

        public enum Specialty {

            CONCERT_PERFORMER, ACADEMIC_SINGER, MUSICAL_DIRECTOR, CHOREOGRAPHER;

        }

        static void choose() {
            System.out.println("Welcome to the University of the Arts!\nHere you can study at the faculties: \n");
            System.out.println(Arrays.toString(Faculty.values()).replace("[", "").replace("]", "")); //displays all faculties recorded in enum
            System.out.println("What Specialty are you interested in? \n");
            System.out.println(Arrays.toString(Specialty.values()).replace("[", "").replace("]", "")); //displays all specialities recorded in enum
            Scanner faculty = new Scanner(System.in);
            String getSpecialty = faculty.next();
            getSpecialty = getSpecialty.toUpperCase(Locale.ROOT);
            switch (getSpecialty) { //choose your speciality
                case "CONCERT_PERFORMER" -> {
                    String getSpecialty2 = String.valueOf(Faculty.INSTRUMENT).toUpperCase(Locale.ROOT);
                    System.out.printf("Ok. Your faculty is %s. Your have to pass the exams: Instrument, Solfeggio and Sight Reading\n", Faculty.INSTRUMENT);
                    AdmissionsOffice.registration(getSpecialty, getSpecialty2);
                }
                case "ACADEMIC_SINGER" -> {
                    String getSpecialty2 = String.valueOf(Faculty.VOCAL).toUpperCase(Locale.ROOT);
                    System.out.printf("Ok. Your faculty is %s. Your have to pass the exams: Singing, Solfeggio and Piano\n", Faculty.VOCAL);
                    AdmissionsOffice.registration(getSpecialty, getSpecialty2);
                }
                case "MUSICAL_DIRECTOR" -> {
                    String getSpecialty2 = String.valueOf(Faculty.THEATRE).toUpperCase(Locale.ROOT);
                    System.out.printf("Ok. Your faculty is %s. Your have to pass the exams: Acting, Directing Composition Presentation and Musical Literature\n", Faculty.THEATRE);
                    AdmissionsOffice.registration(getSpecialty, getSpecialty2);
                }
                case "CHOREOGRAPHER" -> {
                    String getSpecialty2 = String.valueOf(Faculty.CHOREOGRAPHY).toUpperCase(Locale.ROOT);
                    System.out.printf("Ok. Your faculty is %s. Your have to pass the exams: Folk dance, Creative composition performance, Colloquium\n", Faculty.CHOREOGRAPHY);
                    AdmissionsOffice.registration(getSpecialty, getSpecialty2);
                }
            }
        }
    }
}