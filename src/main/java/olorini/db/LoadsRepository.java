package olorini.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadsRepository extends CrudRepository<LoadEntity, Long> {
    List<LoadEntity> getByDroneId(Long droneId);
}
