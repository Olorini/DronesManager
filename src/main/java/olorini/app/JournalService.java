package olorini.app;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class JournalService {
    private static final Logger LOGGER = Logger.getLogger(JournalService.class);

    public String getFileData() {
         try {
            Path path = Paths.get("./src/main/resources/jobs/drone_journal.txt");
            FileInputStream fis = new FileInputStream(path.toFile());
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
            return null;
         }
    }
}
