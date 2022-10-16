package olorini.web.service;

import olorini.db.DroneEntity;
import olorini.db.DronesRepository;
import olorini.web.service.pojo.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DroneService {

    private final DronesRepository repository;

    @Autowired
    public DroneService(DronesRepository repository) {
        this.repository = repository;
    }

    public List<Drone> getDrones() {
        List<DroneEntity> latestSnippets = repository.getDrones();
        return latestSnippets.stream()
                .map(Drone::new)
                .collect(Collectors.toList());
    }

}
