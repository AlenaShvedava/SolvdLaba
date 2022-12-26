package pl.slvd.university.state;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import pl.slvd.university.Main;
import pl.slvd.university.people.Applicant;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.io.FileUtils.writeStringToFile;
import static pl.slvd.university.Main.applicant;

public class ChoosingSpeciality implements Activity, Serializable {
    @Override
    public void go() throws IOException {
        writeStringToFile(state, "\nI'M GOING TO CHOOSE A SPECIALITY", StandardCharsets.UTF_8, true);
        SaveLoadFiles.save(applicant, "Lesson2/src/main/resources/state.bin");
    }
}
