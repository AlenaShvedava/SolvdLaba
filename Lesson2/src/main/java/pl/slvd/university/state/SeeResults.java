package pl.slvd.university.state;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.io.FileUtils.writeStringToFile;
import static pl.slvd.university.Main.applicant;

public class SeeResults implements Activity, Serializable {
    @Override
    public void go() throws IOException {
        writeStringToFile(state, "\nI'M GOING TO SEE THE FINAL RESULTS", StandardCharsets.UTF_8,true);
        SaveLoadFiles.save(applicant, "Lesson2/src/main/resources/state.bin");
    }
}
