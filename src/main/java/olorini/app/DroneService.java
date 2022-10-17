package olorini.app;

import olorini.app.pojo.DroneRecord;
import olorini.app.pojo.Model;
import olorini.db.DroneEntity;
import olorini.db.DronesRepository;
import olorini.web.service.pojo.Drone;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DroneService {

    private final DronesRepository repository;

    @Autowired
    public DroneService(DronesRepository repository) {
        this.repository = repository;
    }

    public Long register(Drone request) {
        String serialNumber = request.getSerialNumber();
        if (serialNumber == null || serialNumber.length() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serial number is too long");
        }
        if (!EnumUtils.isValidEnumIgnoreCase(Model.class, request.getModel())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Model of drone has a wrong value");
        }
        if (request.getWeightLimit() > 500) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Weight limit is too big");
        }
        DroneEntity droneEntity = new DroneRecord(request).getDBEntity();
        DroneEntity answer = repository.save(droneEntity);
        return answer.getId();
    }

    public List<Drone> getDrones() {
        List<DroneEntity> drones = repository.findAll();
        return drones.stream()
                .map(Drone::new)
                .collect(Collectors.toList());
    }

}
