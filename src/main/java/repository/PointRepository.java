package repository;

import entity.PointEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointRepository extends CrudRepository<PointEntity, Long> {
    void deleteAllBySessionId(String sessionId);
    List<PointEntity> findAllBySessionId(String sessionId);

}
