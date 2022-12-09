package pl.slvd.university;

import java.util.*;

import static pl.slvd.university.AdmissionsOffice.applicants;
import static pl.slvd.university.ExamBoard.MIN;
import static pl.slvd.university.ExamSheet.grades;

public class Main {
    public static void main(String[] args) {
        Speciality[] specialities = new Speciality[4];
        System.out.println("\nWelcome to the University of the Arts!\nHere you can see the Faculties of the University: ");
        specialities[0] = new Speciality("INSTRUMENT", "CONCERT_PERFORMER", 2, 1, "Instrument, Solfeggio and Sight Reading");
        specialities[1] = new Speciality("VOCAL", "ACADEMIC_SINGER", 2, 1, "Singing, Solfeggio and Piano");
        specialities[2] = new Speciality("THEATRE", "MUSICAL_DIRECTOR", 1, 1, "Acting, Directing Composition Presentation and Musical Literature");
        specialities[3] = new Speciality("CHOREOGRAPHY", "CHOREOGRAPHER", 1, 1, "Folk dance, Creative composition performance, Colloquium");
        for (Speciality speciality : specialities) {
            System.out.println(speciality.getFacultyName());
        }
        System.out.println("\nWrite what specialty you want to study: "); //make a choice of faculty and specialty
        for (Speciality speciality : specialities) {
            System.out.println(speciality.getName());
        }
        Scanner faculty = new Scanner(System.in);
        final String getSpecialty = faculty.next().toUpperCase();
        for (Speciality speciality : specialities) {
            if (getSpecialty.equals(speciality.getName())) {
                speciality.getNumOfBudgetPlaces();
                speciality.getNumOfPaidPlaces();
                String getSpecialty2 = speciality.getFacultyName();
                System.out.printf("Ok. Your faculty is %s. Your have to pass the exams: %s.\n", getSpecialty2, speciality.getExams());
                AdmissionsOffice.registration(getSpecialty2, getSpecialty);

                List<Applicant> sortedList = applicants.stream().filter(e -> e.getSpeciality().equalsIgnoreCase(getSpecialty)).toList();
                ExamBoard.passExam();
                if (!(grades[0] < MIN || grades[1] < MIN || grades[2] < MIN)) {
                    Deanery.sortByGrades(sortedList, AdmissionsOffice.firstLastName, speciality.getNumOfBudgetPlaces(), speciality.getNumOfPaidPlaces());
                }
            }
        }
    }
}