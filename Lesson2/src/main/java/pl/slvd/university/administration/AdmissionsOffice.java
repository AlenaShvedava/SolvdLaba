package pl.slvd.university.administration;

import pl.slvd.university.people.Applicant;

import java.util.*;

import static pl.slvd.university.Main.LOG;

public class AdmissionsOffice {
    public static List<Applicant> applicants = new ArrayList<>();
    public static String firstLastName;

    public static void registration(String yourFaculty, String yourSpeciality) throws Exception {
        //the registration method accepts the applicant and the values of the faculty and specialty chosen by him
        LOG.info("Confirmation of the chosen specialty and faculty is requested");
        System.out.printf("Apply to faculty: %s, speciality: %s? \n", yourFaculty, yourSpeciality);
        LOG.warn("Information requested. Possible input error");
        Scanner faculty = new Scanner(System.in);
        String chooseDone = faculty.next();
        if ((!(chooseDone.toUpperCase(Locale.ROOT).equals("YES")) && (!(chooseDone.toUpperCase(Locale.ROOT).equals("NO"))))) {
            LOG.error("Exception: Invalid input. The program is closed");
            throw new Exception("You entered an invalid value");
        } else {
            switch (chooseDone.toUpperCase(Locale.ROOT)) {
                case "NO": {
                    LOG.info("Refused");
                    AdmissionsOffice.registration(yourFaculty, yourSpeciality);
                }
                case "YES": {
                    LOG.info("Data confirmed. Additional information requested");
                    System.out.println("Enter your first and last name \n"); //enter personal data to complete the registration
                    List<String> name = new ArrayList<>();
                    name.add(faculty.next());
                    name.add(faculty.next());
                    firstLastName = name.get(0) + " " + name.get(1);
                    System.out.println("Enter your date of birth\n");
                    String birthday = faculty.next();
                    //the applicant has collected all the documents about himself and is ready for registration
                    Applicant applicant = new Applicant((short) 15, firstLastName, birthday, yourFaculty, yourSpeciality);
                    applicants.add(new Applicant((short) 1, "Mary Peterson", "22.10.2000", "INSTRUMENT", "concert_performer", new int[]{56, 44, 95}, applicant.getSum()));
                    applicants.add(new Applicant((short) 2, "Barbara Scott", "14.03.2002", "VOCAL", "vocal_singer", new int[]{66, 40, 39}, applicant.getSum()));
                    applicants.add(new Applicant((short) 3, "Andre Roberts", "22.10.2003", "THEATRE", "musical_director", new int[]{79, 64, 38}, applicant.getSum()));
                    applicants.add(new Applicant((short) 4, "John Hampton", "05.11.2000", "VOCAL", "vocal_singer", new int[]{46, 83, 58}, applicant.getSum()));
                    applicants.add(new Applicant((short) 5, "Erin Jones", "23.07.2001", "CHOREOGRAPHY", "choreographer", new int[]{52, 57, 45}, applicant.getSum()));
                    applicants.add(new Applicant((short) 6, "Mildred Blake", "18.09.2002", "CHOREOGRAPHY", "choreographer", new int[]{94, 89, 86}, applicant.getSum()));
                    applicants.add(new Applicant((short) 7, "Amanda Patrick", "02.05.2000", "INSTRUMENT", "concert_performer", new int[]{79, 87, 100}, applicant.getSum()));
                    applicants.add(new Applicant((short) 8, "Margaret Williams", "31.12.1999", "instrument", "concert_performer", new int[]{31, 44, 68}, applicant.getSum()));
                    applicants.add(new Applicant((short) 9, "Debra Roberts", "27.03.2003", "INSTRUMENT", "concert_performer", new int[]{100, 88, 95}, applicant.getSum()));
                    applicants.add(new Applicant((short) 10, "Julie Hines", "09.11.2001", "VOCAL", "vocal_singer", new int[]{56, 44, 95}, applicant.getSum()));
                    applicants.add(new Applicant((short) 11, "Chad Crawford", "10.02.2002", "THEATRE", "musical_director", new int[]{83, 40, 64}, applicant.getSum()));
                    applicants.add(new Applicant((short) 12, "Rhonda Henderson", "26.07.2002", "INSTRUMENT", "concert_performer", new int[]{77, 53, 80}, applicant.getSum()));
                    applicants.add(new Applicant((short) 13, "George Phillips", "13.03.2001", "VOCAL", "vocal_singer", new int[]{83, 90, 75}, applicant.getSum()));
                    applicants.add(new Applicant((short) 14, "Ruth White", "20.01.2000", "CHOREOGRAPHY", "choreographer", new int[]{85, 60, 58}, applicant.getSum()));
                    applicants.add(new Applicant(applicant.getId(), firstLastName, birthday, applicant.getFaculty(), applicant.getSpeciality(), applicant.getValues(), applicant.getSum()));
                    //List<Applicant> applicantsPlus = new ArrayList<>(applicants);
                    System.out.println("Congratulations! You have registered and are on the list of applicants \n");
                    LOG.info("The list of registered Applicants is displayed");
                    for (Applicant value : applicants) {
                        System.out.println(value);
                    }
                    applicants.set(14, new Applicant(applicant.getId(), firstLastName, birthday, applicant.getFaculty(), applicant.getSpeciality(), applicant.setValues(), applicant.getSum()));
                }
            }
        }
    }
}