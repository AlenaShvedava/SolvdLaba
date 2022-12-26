package pl.slvd.university.people;

import pl.slvd.university.documents.Pass;

import java.util.*;

import static pl.slvd.university.documents.ExamSheet.grades;

public class Applicant {
    private final short id;
    private final String firstLastName, dateOfBirth, faculty, speciality;
    private ArrayList<Integer> values = new ArrayList<>(Arrays.asList(0, 0, 0)); //the number of grades will receive in exams
    public static int sum;
    Pass myPass;

    public Applicant(short id, String firstLastName, String dateOfBirth, String faculty, String speciality) {
        this.id = id;
        this.firstLastName = firstLastName;
        this.dateOfBirth = dateOfBirth;
        this.faculty = faculty;
        this.speciality = speciality;
    }

    public Applicant(short id, String firstLastName, String dateOfBirth, String faculty, String speciality, ArrayList<Integer> values, int sum) {
        this.id = id;
        this.firstLastName = firstLastName;
        this.dateOfBirth = dateOfBirth;
        this.faculty = faculty;
        this.speciality = speciality;
        this.values = values;
        Applicant.sum = sum;
    }

    public short getId() {
        return id;
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

    public String getFirstLastName() {
        return firstLastName;
    }

    public int getSum() {
        return getValues().stream().mapToInt(Integer::valueOf).sum();
    }

    public ArrayList<Integer> setValues() {
        values = grades;
        return values;
    }

    @Override
    public String toString() {
        return String.format("%25s |%20s |%20s |%25s |%20s |%10s ", firstLastName, dateOfBirth, faculty.toUpperCase(Locale.ROOT), speciality.toUpperCase(Locale.ROOT), values.toString().replace("[", "").replace("]", ""), getSum());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return id == applicant.id && Objects.equals(firstLastName, applicant.firstLastName) && Objects.equals(dateOfBirth, applicant.dateOfBirth) && Objects.equals(faculty, applicant.faculty) && Objects.equals(speciality, applicant.speciality) && Objects.equals(myPass, applicant.myPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstLastName, dateOfBirth, faculty, speciality, myPass);
    }
}