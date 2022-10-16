package olorini.db;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "drones")
@Table(name = "drones")
public class DroneEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column
	private String name;

	public DroneEntity() { }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DroneEntity drone = (DroneEntity) o;
		return id.equals(drone.id) && name.equals(drone.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
