package olorini.db;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBRepository extends CrudRepository<DroneEntity, Long> {

//	@Query( value = "SELECT * FROM drones", nativeQuery = true)
//	List<DroneEntity> getDrones();

	List<DroneEntity> findAll();

}