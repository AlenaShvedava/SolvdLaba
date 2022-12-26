package pl.slvd.university.state;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public interface Activity {
    File state = new File("Lesson2/src/main/resources/state.txt");
    public void go () throws IOException;
}
