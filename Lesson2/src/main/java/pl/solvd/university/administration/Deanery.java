package pl.solvd.university.administration;

import pl.solvd.university.people.Applicant;
import pl.solvd.university.people.Student;
import pl.solvd.university.state.ReturnDocuments;

import java.util.*;
import java.util.stream.IntStream;

import static pl.solvd.university.Main.*;
import static pl.solvd.university.utils.Utils.checkYesNoInput;

public class Deanery implements Comparator<Applicant> {

    @Override
    public int compare(Applicant o1, Applicant o2) {
        if (o2.getSum() == o1.getSum()) {
            return o2.getFirstLastName().compareTo(o1.getFirstLastName());
        } else {
            return o2.getSum() - o1.getSum();
        }
    }

    public static void summingUpTheResultsOfEntranceExaminations(List<Applicant> sortedApplicantsBySpecialityList, int NumOfBudgetPlaces, int NumOfPaidPlaces, String getSpecialty) throws Exception {
        System.out.println("Do you want to see the rating of Applicants for your Specialty? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        try {
            checkYesNoInput(answer);
        } catch (Exception e) {
            System.out.println("Problem occurred: " + e);
            summingUpTheResultsOfEntranceExaminations(sortedApplicantsBySpecialityList, NumOfBudgetPlaces, NumOfPaidPlaces, getSpecialty);
        }
        switch (answer) {
            case "NO" -> {
                LOG.info("Well, the Applicant is not ready to see the rating of applicants. You can see this list later");
                summingUpTheResultsOfEntranceExaminations(sortedApplicantsBySpecialityList, NumOfBudgetPlaces, NumOfPaidPlaces, getSpecialty);
            }
            case "YES" -> {
                LOG.info("Applicant wants to see the results. Results sorted in descending order");
                Set<Applicant> sortedApplicantsByGradesList = new TreeSet<>(new Deanery());
                sortedApplicantsByGradesList.addAll(sortedApplicantsBySpecialityList);
                List<Applicant> sortedApplicantsByGradesCopy = new ArrayList<>(sortedApplicantsByGradesList);
                System.out.println("\nRATING LIST:\n");
                IntStream.range(0, sortedApplicantsByGradesCopy.size()).forEach(i -> System.out.println((i + 1) + ". " + sortedApplicantsByGradesCopy.get(i)));
                int sum = NumOfBudgetPlaces + NumOfPaidPlaces;
                System.out.printf("\nFor the specialty %s is provided:\nBudget places - %d\nPaid places - %d\n", getSpecialty, NumOfBudgetPlaces, NumOfPaidPlaces);
                LOG.info("Looking for an Applicant in the list and look at his rating");
                for (int i = 0; i < sortedApplicantsByGradesCopy.size(); i++) {
                    boolean isMyApplicant = sortedApplicantsByGradesCopy.get(i).equals(applicant);
                    if (isMyApplicant) {
                        boolean isEnteredTheBudget = i < NumOfBudgetPlaces;
                        boolean isEnteredThePaidPlace = i >= NumOfBudgetPlaces && i < sum;
                        if (isEnteredTheBudget) {
                            System.out.println("\nGreat! You are enrolled in the University for a budget place!");
                            LOG.info("The list of Applicants recommended for enrollment is sent to the Dean of the faculty for approval");
                        } else if (isEnteredThePaidPlace) {
                            System.out.println("\nGreat! You are enrolled in Paid education at the University!\nGo to the Accounting department to conclude a payment agreement.\n");
                            LOG.info("The list of Applicants recommended for enrollment is sent to the Dean of the faculty for approval");
                            Accounting.agreement(sortedApplicantsByGradesCopy);
                        } else {
                            System.out.println("\nUnfortunately, you are not enrolled in the University");
                            LOG.info("Applicant goes to return his documents");
                            applicant.setActivity(new ReturnDocuments());
                            applicant.saveActivityAndApplicantInfoToStateFiles();
                            AdmissionsOffice.returnOfDocuments();
                            break;
                        }
                        applicant.saveActivityAndApplicantInfoToStateFiles();
                        Student.getFirstCourse(sortedApplicantsByGradesCopy.subList(0, sum));
                        System.out.println("\nEntrance examinations are completed.\nCongratulations to future 1st year students!");
                    }
                }
            }
        }
    }
}
