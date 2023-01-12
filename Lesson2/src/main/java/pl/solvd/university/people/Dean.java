package pl.solvd.university.people;

import pl.solvd.university.departments.Speciality;

import java.util.List;

import static pl.solvd.university.Main.*;

public class Dean extends Professor {

    public Dean(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public static <T> List<T> approve(List<T> approvalList) {
        List<T> approvedList = approvalList.stream().toList();
        LOG.info("Dean signs documents");
        System.out.printf("%s the Dean of %s faculty approved the list\n", Speciality.valueOf(applicant.getSpeciality()).getDean(), (applicant.getFaculty()));
        return approvedList;
    }
}
