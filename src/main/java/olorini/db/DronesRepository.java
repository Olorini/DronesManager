package olorini.db;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DronesRepository extends CrudRepository<DroneEntity, Long> {
	List<DroneEntity> findAll();
	Optional<DroneEntity> findById(Long id);
	List<DroneEntity> findByState(String state);

}