package pl.slvd.university.departments;

import pl.slvd.university.administration.Deanery;

public enum Faculty {
    INSTRUMENT(new Deanery.Dean("Brian Ellis"), 1),
    VOCAL(new Deanery.Dean("Sherry Green"), 2),
    THEATRE(new Deanery.Dean("Michael Lawson"), 3),
    CHOREOGRAPHY(new Deanery.Dean("Arlene Garcia"), 4);
    private final Deanery.Dean dean;
    private final int campus;

    Faculty(Deanery.Dean dean, int campus) {
        this.dean = dean;
        this.campus = campus;
    }

    public Deanery.Dean getDean() {
        return dean;
    }

    public int getCampus() {
        return campus;
    }
}