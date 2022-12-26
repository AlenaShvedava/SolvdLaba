package pl.slvd.university.administration;

import pl.slvd.university.people.Applicant;

import java.util.*;
import java.util.stream.IntStream;

import static pl.slvd.university.Main.LOG;

public class Deanery implements Comparator<Applicant> {

    @Override
    public int compare(Applicant o1, Applicant o2) {
        if (o2.getSum() == o1.getSum()) {
            return o2.getFirstLastName().compareTo(o1.getFirstLastName());
        } else
            return o2.getSum() - o1.getSum();
    }

    public static void sortByGrades(List<Applicant> sortedList, String firstLastName, int NumOfBudgetPlaces, int NumOfPaidPlaces, String getSpecialty) throws Exception {
        System.out.println("Ready to see the lists of enrolled in the University?");
        LOG.warn("Confirmation required. Possible input error");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        if ((!(answer.toUpperCase(Locale.ROOT).equals("YES")) && (!(answer.toUpperCase(Locale.ROOT).equals("NO"))))) {
            LOG.error("Exception: Invalid input. The program is closed");
            throw new Exception("You entered an invalid value");
        } else {
            switch (answer) {
                case "NO" -> {
                    LOG.info("Refused");
                    sortByGrades(sortedList, firstLastName, NumOfBudgetPlaces, NumOfPaidPlaces, getSpecialty);
                }
                case "YES" -> {
                    LOG.info("Confirmed. Results sorted in descending order");
                    Set<Applicant> sortedApplicants = new TreeSet<>(new Deanery());
                    sortedApplicants.addAll(sortedList);
                    List<Applicant> sortedApplicantsCopy = sortedApplicants.stream().toList();
                    IntStream.range(0, sortedApplicantsCopy.size()).forEach(i ->
                            System.out.println((i + 1) + ". " + sortedApplicantsCopy.get(i)));
                    int sum = NumOfBudgetPlaces + NumOfPaidPlaces;
                    System.out.printf("For the specialty %s is provided:\n Budget places - %d\n Paid places - %d\n", getSpecialty, NumOfBudgetPlaces, NumOfPaidPlaces);
                    LOG.info("Search for an Applicant in the list, show his final result and exit from the program");
                    for (int i = 0; i < sortedApplicantsCopy.size(); i++) {
                        if (sortedApplicantsCopy.get(i).getFirstLastName().equals(firstLastName)) {
                            if (i >= sum) {
                                System.out.println("Unfortunately, you are not enrolled in the University");
                                break;
                            } else if (sortedApplicantsCopy.get(i).getFirstLastName().equals(firstLastName)) {
                                if (i <= NumOfBudgetPlaces - 1) {
                                    System.out.println("Great! You are enrolled in the University for a budget place!");
                                    break;
                                } else if (sortedApplicantsCopy.get(i).getFirstLastName().equals(firstLastName)) {
                                    if (i >= NumOfBudgetPlaces) {
                                        if (i <= (sum - 1)) {
                                            System.out.println("Great! You are enrolled in paid education at the University! Your studies will cost $2000 per year");
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}