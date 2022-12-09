package pl.slvd.university;

import java.util.*;
import java.util.stream.IntStream;

class Deanery implements Comparator<Applicant> {

    @Override
    public int compare(Applicant o1, Applicant o2) {
        return o2.getSum() - (o1.getSum());
    }

    public static void sortByGrades(List<Applicant> sortedList, String firstLastName, int getNumOfBudgetPlaces, int getNumOfPaidPlaces) {
        System.out.println("Ready to see the lists of enrolled in the University?");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next().toUpperCase();
        switch (answer) {
            case "NO" -> sortByGrades(sortedList, firstLastName, getNumOfBudgetPlaces, getNumOfPaidPlaces);
            case "YES" -> {
                Set<Applicant> sortedApplicants = new TreeSet<>(new Deanery());
                sortedApplicants.addAll(sortedList);
                List<Applicant> sortedApplicantsCopy = sortedApplicants.stream().toList();

                IntStream.range(0, sortedApplicantsCopy.size()).forEach(i ->
                        System.out.println((i + 1) + ". " + sortedApplicantsCopy.get(i)));
                int sum = getNumOfBudgetPlaces + getNumOfPaidPlaces;

                for (int i = 0; i < sortedApplicantsCopy.size(); i++) {
                    if (sortedApplicantsCopy.get(i).getFirstLastName().equals(firstLastName) && (i >= sum)) {
                        System.out.println("Unfortunately, you are not enrolled in the University.");
                        break;
                    } else if (sortedApplicantsCopy.get(i).getFirstLastName().equals(firstLastName) && (i <= getNumOfBudgetPlaces - 1)) {
                        System.out.println("Great! You are enrolled in the University for a budget place!");
                        break;
                    } else if (sortedApplicantsCopy.get(i).getFirstLastName().equals(firstLastName) && (i > getNumOfBudgetPlaces - 1) && (i <= (sum - getNumOfBudgetPlaces))) {
                        System.out.println("Great! You are enrolled in paid education at the University! Your studies will cost $2,000 per year.");
                        break;
                    }
                }
            }
        }
    }
}