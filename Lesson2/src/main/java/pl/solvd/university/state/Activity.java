package pl.solvd.university.state;

import java.io.File;
import java.io.IOException;

public interface Activity {
    File state = new File("Lesson2/src/main/resources/state.txt");

    void saveActivityInfo() throws IOException;
}
