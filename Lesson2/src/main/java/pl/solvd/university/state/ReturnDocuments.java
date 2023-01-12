package pl.solvd.university.state;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.io.FileUtils.writeStringToFile;
import static pl.solvd.university.Main.applicant;

public class ReturnDocuments implements Activity, Serializable {
    @Override
    public void saveActivityInfo() throws IOException {
        writeStringToFile(state, "\nI'M GOING TO RETURN MY DOCUMENTS\n", StandardCharsets.UTF_8, true);
        SaveLoadFiles.saveApplicantToFile(applicant, "Lesson2/src/main/resources/state.bin");
    }
}
