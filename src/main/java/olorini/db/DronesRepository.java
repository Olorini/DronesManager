package olorini.db;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DronesRepository extends CrudRepository<DroneEntity, Long> {

	@Query( value = "SELECT * FROM drones", nativeQuery = true)
	List<DroneEntity> getDrones();

}