package pl.solvd.university.state;

import pl.solvd.university.people.Applicant;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveLoadFiles {
    public static void saveApplicantToFile(Applicant applicant, String file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(file)))) {
            oos.writeObject(applicant);
        }
    }

    public static void showInformationFromFile(String file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(file)))) {
            Applicant applicant = (Applicant) ois.readObject();
            System.out.printf("The Applicant's name is %s, date of birth %s, faculty %s speciality %s\n", applicant.getFirstLastName(), applicant.getDateOfBirth(), applicant.getFaculty(), applicant.getSpeciality());
        }
    }
}
