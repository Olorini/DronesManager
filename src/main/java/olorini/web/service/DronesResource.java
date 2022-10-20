package olorini.web.service;

import olorini.app.DroneService;
import olorini.app.JournalService;
import olorini.web.service.pojo.Load;
import olorini.web.service.pojo.Drone;
import olorini.web.service.pojo.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/drones")
public class DronesResource {

    private final DroneService service;
    private final JournalService journalService;

    @Autowired
    public DronesResource(DroneService service, JournalService journalService) {
        this.service = service;
        this.journalService = journalService;
    }

    @GetMapping(path = "/fleet", produces = "application/json")
    public List<Drone> getDrones() {
        return service.getDrones();
    }

    @GetMapping(path="/medication", produces = "application/json")
    public List<Medication> getMedication() {
        return service.getMedication();
    }

    @PostMapping(path = "/register", consumes = "application/json")
    public Map<String, Long> createApiCodeSnippet(@RequestBody Drone request) {
        Long id = service.register(request);
        return Collections.singletonMap("id", id);
    }

    @PostMapping(path ="/load", consumes = "application/json")
    public ResponseEntity<Object> loadDrone(@RequestBody Load request) {
        service.load(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path="/get_medication", produces = "application/json")
    public List<Medication> getMedication(long droneId) {
        return service.getMedication(droneId);
    }

    @GetMapping(path = "/get_idle_drones", produces = "application/json")
    public List<Drone> getIdleDrones() {
        return service.getIdleDrones();
    }

    @GetMapping(path = "/get_battery_level", produces = "application/json")
    public Map<String, Integer> getBatteryLevel(long droneId) {
        int batteryLevel = service.getBatteryLevel(droneId);
         return Collections.singletonMap("batteryLevel", batteryLevel);
    }

    @GetMapping(path = "/journal", produces = "text/html")
    public String getJournal() {
        return journalService.getFileData();
    }

}
