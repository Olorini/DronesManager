package olorini.web.service.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import olorini.db.DroneEntity;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Drone {

	private String id;
	private String name;

	public Drone() { }

	public Drone(DroneEntity source) {
		this.id = source.getId();
		this.name = source.getName();
	}

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
}
