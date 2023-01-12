package pl.solvd.university.people;

import pl.solvd.university.documents.Pass;
import pl.solvd.university.state.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import static pl.solvd.university.documents.ExamSheet.grades;

public class Applicant implements Serializable {
    private int id;
    private String firstName, lastName, firstLastName, dateOfBirth, faculty, speciality;
    private ArrayList<Integer> values = new ArrayList<>(Arrays.asList(0, 0, 0));
    public static int sum;
    public Pass myPass;
    private Activity activity;

    public Applicant() {
    }

    public Applicant(short id, String firstName, String lastName, String dateOfBirth, String faculty, String speciality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.faculty = faculty;
        this.speciality = speciality;
    }

    public Applicant(int id, String firstName, String lastName, String dateOfBirth, String faculty, String speciality, ArrayList<Integer> values, int sum) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.faculty = faculty;
        this.speciality = speciality;
        this.values = values;
        Applicant.sum = sum;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getFirstLastName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public String getFaculty() {
        return faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public ArrayList<Integer> getValues() {
        return values;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getSum() {
        return getValues().stream().mapToInt(Integer::valueOf).sum();
    }

    public void setValues() {
        values = grades;
    }

    @Override
    public String toString() {
        return String.format("%25s |%20s |%20s |%25s |%20s |%10s ", getFirstLastName(), getDateOfBirth(), getFaculty().toUpperCase(Locale.ROOT), getSpeciality().toUpperCase(Locale.ROOT), getValues().toString().replace("[", "").replace("]", ""), getSum());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        boolean isIdEquals = id == applicant.id;
        boolean isNameEquals = Objects.equals(firstLastName, applicant.firstLastName);
        boolean isDateOfBirthEquals = Objects.equals(dateOfBirth, applicant.dateOfBirth);
        boolean isFacultyEquals = Objects.equals(faculty, applicant.faculty);
        boolean isSpecialityEquals = Objects.equals(speciality, applicant.speciality);
        boolean isMyPassEquals = Objects.equals(myPass, applicant.myPass);
        return isIdEquals && isNameEquals && isDateOfBirthEquals && isFacultyEquals && isSpecialityEquals && isMyPassEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstLastName, dateOfBirth, faculty, speciality, myPass);
    }

    public void saveActivityAndApplicantInfoToStateFiles() throws IOException {
        activity.saveActivityInfo();
    }
}
