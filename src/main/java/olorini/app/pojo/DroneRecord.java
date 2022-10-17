package olorini.app.pojo;

import olorini.db.DroneEntity;
import olorini.db.DronesRepository;
import olorini.web.service.pojo.Drone;
import org.apache.commons.lang3.EnumUtils;

import java.math.BigDecimal;

public class DroneRecord {

    private String serialNumber;
    private Model model;
    private int weightLimit;
    private BigDecimal batteryCapacity;
    private State state;

    public DroneRecord(Drone source) {
        setSerialNumber(source.getSerialNumber());
        setModel(EnumUtils.getEnumIgnoreCase(Model.class, source.getModel()));
        setWeightLimit(source.getWeightLimit());
        setBatteryCapacity(source.getBatteryCapacity());
        setState(State.IDLE);
    }

    public DroneEntity getDBEntity() {
        DroneEntity entity = new DroneEntity();
        entity.setSerialNumber(this.getSerialNumber());
        entity.setModel(this.getModel().name());
        entity.setWeightLimit(this.getWeightLimit());
        entity.setBatteryCapacity(this.getBatteryCapacity());
        entity.setState(this.getState().name());
        return entity;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public BigDecimal getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(BigDecimal batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
