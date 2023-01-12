package pl.solvd.university.departments;

import pl.solvd.university.people.Dean;

import static pl.solvd.university.departments.Faculty.INSTRUMENT;

public enum Speciality {
    CONCERT_PERFORMER(INSTRUMENT, 2, 1, "Instrument, Solfeggio and Sight Reading"),
    VOCAL_SINGER(Faculty.VOCAL, 2, 1, "Singing, Solfeggio and Piano"),
    MUSICAL_DIRECTOR(Faculty.THEATRE, 1, 1, "Acting, Directing Composition Presentation and Musical Literature"),
    CHOREOGRAPHER(Faculty.CHOREOGRAPHY, 1, 1, "Folk dance, Creative composition performance, Colloquium");
    final Faculty category;
    final int numOfBudgetPlaces;
    final int numOfPaidPlaces;
    final String exams;

    Speciality(Faculty category, int numOfBudgetPlaces, int numOfPaidPlaces, String exams) {
        this.category = category;
        this.numOfBudgetPlaces = numOfBudgetPlaces;
        this.numOfPaidPlaces = numOfPaidPlaces;
        this.exams = exams;
    }

    public Faculty getCategory() {
        return category;
    }

    public int getNumOfBudgetPlaces() {
        return numOfBudgetPlaces;
    }

    public int getNumOfPaidPlaces() {
        return numOfPaidPlaces;
    }

    public String getExams() {
        return exams;
    }

    public Dean getDean() {
        return getCategory().getDean();
    }
}
