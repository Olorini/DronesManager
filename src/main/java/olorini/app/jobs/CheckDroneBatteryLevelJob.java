package olorini.app.jobs;

import olorini.db.DroneEntity;
import olorini.db.DronesRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CheckDroneBatteryLevelJob {

    private final Logger LOGGER = Logger.getLogger(CheckDroneBatteryLevelJob.class);

    private final DronesRepository dronesRepository;

    @Autowired
    public CheckDroneBatteryLevelJob(DronesRepository dronesRepository) {
        this.dronesRepository = dronesRepository;
    }

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 3600000)
    public void check() {
        try {
            Path path = Paths.get("./src/main/resources/jobs/drone_journal.txt");
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            FileOutputStream fos = new FileOutputStream(path.toFile(), true);
            OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            writer.write(createLine());
            writer.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private String createLine() {
        List<DroneEntity> drones = dronesRepository.findAll();
        StringBuilder builder = new StringBuilder();
        String date = DATE_FORMAT.format(new Date());
        for (DroneEntity drone : drones) {
            builder.append(date).append(" ").append(drone.getSerialNumber()).append(" ");
            int capacity = drone.getBatteryCapacity();
            String batteryInfo = (capacity < 25) ? capacity + " LOW" : capacity + " NORMAL";
            builder.append(batteryInfo).append("\n");
        }
        return builder.toString();
    }
}
