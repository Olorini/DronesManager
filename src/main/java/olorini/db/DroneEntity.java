package olorini.db;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "drones")
@Table(name = "drones")
public class DroneEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@Size(min = 1, max = 100)
	private String serialNumber;

	@OneToOne
	@JoinColumn(name="model_id")
	private DroneModelEntity model;

	@Column
	@Max(500)
	private int weightLimit;

	@Column
	private BigDecimal batteryCapacity;

	@OneToOne
	@JoinColumn(name="state_id")
	private DroneStateEntity state;

	public DroneEntity() { }

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

	public DroneModelEntity getModel() {
		return model;
	}

	public void setModel(DroneModelEntity model) {
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

	public DroneStateEntity getState() {
		return state;
	}

	public void setState(DroneStateEntity state) {
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
