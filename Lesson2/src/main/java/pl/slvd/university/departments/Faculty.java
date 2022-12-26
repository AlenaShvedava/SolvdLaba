package pl.slvd.university.departments;

import pl.slvd.university.administration.Deanery;
import pl.slvd.university.people.Dean;

public enum Faculty {
    INSTRUMENT(new Dean("Brian Ellis"), 1),
    VOCAL(new Dean("Sherry Green"), 2),
    THEATRE(new Dean("Michael Lawson"), 3),
    CHOREOGRAPHY(new Dean("Arlene Garcia"), 4);
    private final Dean dean;
    private final int campus;

    Faculty(Dean dean, int campus) {
        this.dean = dean;
        this.campus = campus;
    }

    public Dean getDean() {
        return dean;
    }

    public int getCampus() {
        return campus;
    }
}