package olorini.app;

import olorini.db.*;
import olorini.web.service.pojo.Drone;
import olorini.web.service.pojo.Load;
import olorini.web.service.pojo.Medication;
import olorini.web.service.pojo.State;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DroneService {

    private final DronesRepository dronesRepository;
    private final MedicationRepository medicationRepository;
    private final LoadsRepository loadsRepository;

    @Autowired
    public DroneService(DronesRepository dronesRepository,
                        MedicationRepository medicationRepository,
                        LoadsRepository loadsRepository) {
        this.dronesRepository = dronesRepository;
        this.medicationRepository = medicationRepository;
        this.loadsRepository = loadsRepository;
    }

    public void load(Load request) {
        List<Long> result = new ArrayList<>();
        if (request.getDroneId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Drone identifier is empty");
        }
        if (request.getMedicineIds().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "List of medicine identifiers are empty");
        }
        Optional<DroneEntity> dbDrone = dronesRepository.findById(request.getDroneId());
        if (!dbDrone.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Drone is not found");
        }
        DroneEntity droneEntity = dbDrone.get();
        checkDroneAvailableForLoading(droneEntity);
        saveDroneState(State.LOADING, droneEntity);
        List<MedicationEntity> medicationEntities = medicationRepository.findByIdIn(request.getMedicineIds());
        if (medicationEntities.isEmpty()) {
            saveDroneState(State.IDLE, droneEntity);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Medicines are not found");
        }
        int weight = medicationEntities.stream()
                .map(MedicationEntity::getWeight)
                .reduce(Integer::sum).get();
        if (weight > droneEntity.getWeightLimit()) {
            saveDroneState(State.IDLE, droneEntity);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Weight of medicine is very large");
        }
        for (MedicationEntity medication : medicationEntities) {
            LoadEntity loadEntity = new LoadEntity(droneEntity, medication);
            LoadEntity resultEntity = loadsRepository.save(loadEntity);
            result.add(resultEntity.getId());
        }
        if (!result.isEmpty()) {
            saveDroneState(State.LOADED, droneEntity);
        } else {
            saveDroneState(State.IDLE, droneEntity);
        }
    }

    public Long register(Drone request) {
        String serialNumber = request.getSerialNumber();
        if (serialNumber == null || serialNumber.length() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serial number is too long");
        }
        if (request.getWeightLimit() > 500) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Weight limit is very large");
        }
        request.setState(State.IDLE);
        DroneEntity entity = new DroneEntity(request);
        DroneEntity result = dronesRepository.save(entity);
        return result.getId();
    }

    public List<Drone> getDrones() {
        List<DroneEntity> drones = dronesRepository.findAll();
        return drones.stream()
                .map(Drone::new)
                .collect(Collectors.toList());
    }

    public List<Drone> getIdleDrones() {
        List<DroneEntity> drones = dronesRepository.findByState(State.IDLE.name());
        return drones.stream()
                .map(Drone::new)
                .collect(Collectors.toList());
    }

    public int getBatteryLevel(long droneId) {
        Optional<DroneEntity> droneEntity = dronesRepository.findById(droneId);
        if (!droneEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Drone is not found");
        }
        return droneEntity.get().getBatteryCapacity();
    }

    public List<Medication> getMedication() {
        List<MedicationEntity> medication = medicationRepository.findAll();
        return medication.stream()
                .map(Medication::new)
                .collect(Collectors.toList());
    }

    public List<Medication> getMedication(long droneId) {
        List<LoadEntity> loads = loadsRepository.getByDroneId(droneId);
        return loads.stream()
                .map(LoadEntity::getMedication)
                .map(Medication::new)
                .collect(Collectors.toList());
    }

    private void checkDroneAvailableForLoading(DroneEntity droneEntity) {
        if (EnumUtils.getEnumIgnoreCase(State.class, droneEntity.getState()) != State.IDLE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Drone is busy");
        }
        if (droneEntity.getBatteryCapacity() < 25) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Drone isn't charged");
        }

    }

    private void saveDroneState(State state, DroneEntity drone) {
        drone.setState(state.name());
        dronesRepository.save(drone);
    }

}
