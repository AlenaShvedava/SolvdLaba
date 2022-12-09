package pl.slvd.university;

import java.util.Objects;

public class Speciality extends Faculty {
    private final String name;
    private final int numOfBudgetPlaces;
    private final int numOfPaidPlaces;
    private final String exams;

    public Speciality(String facultyName, String name, int numOfBudgetPlaces, int numOfPaidPlaces, String exams) {
        super(facultyName);
        this.name = name;
        this.numOfBudgetPlaces = numOfBudgetPlaces;
        this.numOfPaidPlaces = numOfPaidPlaces;
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "name='" + name + '\'' +
                ", numOfBudgetPlaces=" + numOfBudgetPlaces +
                ", numOfPaidPlaces=" + numOfPaidPlaces +
                ", exams='" + exams + '\'' +
                ", facultyName='" + getFacultyName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Speciality that = (Speciality) o;
        return numOfBudgetPlaces == that.numOfBudgetPlaces && numOfPaidPlaces == that.numOfPaidPlaces && Objects.equals(name, that.name) && Objects.equals(exams, that.exams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, numOfBudgetPlaces, numOfPaidPlaces, exams);
    }

    public String getName() {
        return name;
    }

    public String getExams() {
        return exams;
    }

    public int getNumOfBudgetPlaces() {
        return numOfBudgetPlaces;
    }

    public int getNumOfPaidPlaces() {
        return numOfPaidPlaces;
    }
}