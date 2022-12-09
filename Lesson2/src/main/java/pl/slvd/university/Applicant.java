package pl.slvd.university;

import java.util.*;

import static java.util.Arrays.stream;

import static pl.slvd.university.ExamSheet.grades;

public class Applicant {
    private final short id;
    private final String firstLastName;
    private final String dateOfBirth;
    private final String faculty;
    private final String speciality;
    private int[] values = new int[3]; //the number of grades will receive in exams
    int sum = stream(getValues()).sum();

    public Applicant(short id, String firstLastName, String dateOfBirth, String faculty, String speciality) {
        this.id = id;
        this.firstLastName = firstLastName;
        this.dateOfBirth = dateOfBirth;
        this.faculty = faculty;
        this.speciality = speciality;
    }

    public Applicant(short id, String firstLastName, String dateOfBirth, String faculty, String speciality, int[] values, int sum) {
        this.id = id;
        this.firstLastName = firstLastName;
        this.dateOfBirth = dateOfBirth;
        this.faculty = faculty;
        this.speciality = speciality;
        this.values = values;
        this.sum = sum;
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

    public int[] getValues() {
        return values;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public int getSum() {
        return stream(getValues()).sum();
    }

    public int[] setValues() {
        this.values = grades;
        return values;
    }

    @Override
    public String toString() {
        return String.format("%20s |%20s |%20s |%20s |%20s |%20s ", firstLastName, dateOfBirth, faculty.toUpperCase(Locale.ROOT), speciality.toUpperCase(Locale.ROOT), Arrays.toString(values), getSum()) +
                '|';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(speciality, applicant.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speciality);
    }
}