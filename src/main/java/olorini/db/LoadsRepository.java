package olorini.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadsRepository extends CrudRepository<LoadEntity, Long> {




}
