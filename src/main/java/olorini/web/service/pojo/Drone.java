package olorini.web.service.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import olorini.db.DroneEntity;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Drone {

	private Long id;
	private String serialNumber;
	private String model;
	private int weightLimit;
	private BigDecimal batteryCapacity;
	private String state;

	public Drone() { }

	public Drone(DroneEntity source) {
		setId(source.getId());
		setSerialNumber(source.getSerialNumber());
		setModel(source.getModel());
		setWeightLimit(source.getWeightLimit());
		setBatteryCapacity(source.getBatteryCapacity());
		setState(source.getState());
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
