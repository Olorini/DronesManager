package olorini.web.service;

import olorini.db.DroneEntity;
import olorini.db.DBRepository;
import olorini.web.service.pojo.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DroneService {

    private final DBRepository repository;

    @Autowired
    public DroneService(DBRepository repository) {
        this.repository = repository;
    }

    public List<Drone> getDrones() {
        List<DroneEntity> drones = repository.findAll();
        return drones.stream()
                .map(Drone::new)
                .collect(Collectors.toList());
    }

}
