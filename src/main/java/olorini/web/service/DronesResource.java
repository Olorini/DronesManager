package olorini.web.service;

import olorini.web.service.pojo.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DronesResource {

    private final DroneService codeService;

    @Autowired
    public DronesResource(DroneService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "/drones")
    public List<Drone> getDrones() {
        return codeService.getDrones();
    }

}
