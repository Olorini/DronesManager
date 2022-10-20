package olorini.db;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends CrudRepository<MedicationEntity, Long> {
	List<MedicationEntity> findAll();
	List<MedicationEntity> findByIdIn(List<Long> ids);
}