package pl.solvd.university.utils;

import pl.solvd.university.exceptions.InputException;

import java.io.IOException;

public class Utils {
    public static void checkYesNoInput(String text) throws InputException {
        if (!(text.equals("YES")) && (!(text.equals("NO")))) {
            throw new InputException("You can only enter \"Yes\" or \"No\"", new IOException());
        }
    }

    public static void checkLettersInput(String text) throws InputException {
        if (!(text.matches("[a-zA-Z]+"))) {
            throw new InputException("Your answer must contain only letters", new IOException());
        }
    }

    public static void checkDateInput(String day, String month, String year) throws InputException {
        if (!(day.matches("0?[1-9]|[12][0-9]|3[01]") && month.matches("0?[1-9]|1[0-2]") && year.matches("(19)[6-9][0-9]|(20)0?[0-9]"))) {
            throw new InputException("No such date. Date entered incorrectly", new IOException());
        }
    }
}
