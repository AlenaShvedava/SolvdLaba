package pl.slvd.university.people;

import pl.slvd.university.documents.Pass;
import pl.slvd.university.state.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import static pl.slvd.university.documents.ExamSheet.grades;

public class Applicant implements Serializable {
    private final short id;
    private String firstLastName, dateOfBirth, faculty, speciality;
    private ArrayList<Integer> values = new ArrayList<>(Arrays.asList(0, 0, 0)); //the number of grades will receive in exams
    public static int sum;
    Pass myPass;
    Activity activity;

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

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getDateOfBirth() {
        return dateOfBirth;
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

    public void changeActivity() {
        if (activity instanceof ReadRules) {
            setActivity(new ChoosingSpeciality());
        } else if (activity instanceof ChoosingSpeciality) {
            setActivity(new Registration());
        } else if (activity instanceof Registration) {
            setActivity(new PassExams());
        } else if (activity instanceof PassExams) {
            setActivity(new SeeResults());
        } else
            setActivity(new ReturnDocuments());
    }

    public void go() throws IOException {
        activity.go();
    }
}
