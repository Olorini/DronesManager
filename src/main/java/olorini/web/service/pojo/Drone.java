package olorini.web.service.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import olorini.db.DroneEntity;
import org.apache.commons.lang3.EnumUtils;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Drone {
	private Long id;
	private String serialNumber;
	private Model model;
	private int weightLimit;
	private int batteryCapacity;
	private State state;

	public Drone() { }

	public Drone(DroneEntity source) {
		setId(source.getId());
		setSerialNumber(source.getSerialNumber());
		setModel(EnumUtils.getEnumIgnoreCase(Model.class, source.getModel()));
		setWeightLimit(source.getWeightLimit());
		setBatteryCapacity(source.getBatteryCapacity());
		setState(EnumUtils.getEnumIgnoreCase(State.class, source.getModel()));
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

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
