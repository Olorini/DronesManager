package olorini.web.service;

import olorini.app.DroneService;
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

    @Autowired
    public DronesResource(DroneService service) {
        this.service = service;
    }

    @GetMapping(path = "/fleet")
    public List<Drone> getDrones() {
        return service.getDrones();
    }

    @GetMapping(path="/medication")
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

}
