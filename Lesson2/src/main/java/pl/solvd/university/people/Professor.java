package pl.solvd.university.people;

public class Professor {
    public String firstName;
    public String lastName;

    public Professor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static void consult() {
        System.out.println("+ You attended a pre-exam consultation");
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }
}
