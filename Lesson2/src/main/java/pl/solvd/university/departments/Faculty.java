package pl.solvd.university.departments;

import pl.solvd.university.people.Dean;

public enum Faculty {
    INSTRUMENT(1),
    VOCAL(2),
    THEATRE(3),
    CHOREOGRAPHY(4);

    private Dean dean;
    private final int campus;

    Faculty(int campus) {
        this.campus = campus;
    }

    public void setDean(Dean dean) {
        this.dean = dean;
    }

    public Dean getDean() {
        return dean;
    }

    public int getCampus() {
        return campus;
    }
}
