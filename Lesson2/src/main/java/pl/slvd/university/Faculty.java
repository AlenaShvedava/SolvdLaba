package pl.slvd.university;

import java.util.Objects;

public abstract class Faculty {
    private final String facultyName;

    public Faculty(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyName='" + getFacultyName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(getFacultyName(), faculty.getFacultyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFacultyName());
    }
}