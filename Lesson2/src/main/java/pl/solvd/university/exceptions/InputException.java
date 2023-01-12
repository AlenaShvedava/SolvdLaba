package pl.solvd.university.exceptions;

import static pl.solvd.university.Main.LOG;

public class InputException extends Exception {
    public InputException(String message, Throwable err) {
        super(message, err);
        LOG.error("Exception: Invalid characters were entered in the field");
    }
}
