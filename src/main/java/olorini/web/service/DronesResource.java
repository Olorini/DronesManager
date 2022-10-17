package olorini.web.service;

import olorini.app.DroneService;
import olorini.web.service.pojo.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/drones")
public class DronesResource {

    private final DroneService codeService;

    @Autowired
    public DronesResource(DroneService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "/fleet")
    public List<Drone> getDrones() {
        return codeService.getDrones();
    }

    @PostMapping(path = "/register", consumes = "application/json")
    public Map<String, Long> createApiCodeSnippet(@RequestBody Drone request) {
        Long id = codeService.register(request);
        return Collections.singletonMap("id", id);
    }

}
