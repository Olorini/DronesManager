package olorini.db;

import olorini.web.service.pojo.Drone;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity(name = "drones")
@Table(name = "drones")
public class DroneEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	@Size(min = 1, max = 100)
	private String serialNumber;

	@Column
	private String model;

	@Column
	@Max(500)
	private int weightLimit;

	@Column
	private int batteryCapacity;

	@Column
	private String state;

	public DroneEntity() { }

	public DroneEntity(Drone source) {
		setSerialNumber(source.getSerialNumber());
		setModel(source.getModel().name());
		setWeightLimit(source.getWeightLimit());
		setBatteryCapacity(source.getBatteryCapacity());
		setState(source.getState().name());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DroneEntity that = (DroneEntity) o;
		return id.equals(that.id) && serialNumber.equals(that.serialNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, serialNumber);
	}
}
