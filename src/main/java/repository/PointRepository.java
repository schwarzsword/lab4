package repository;

import entity.PointEntity;
import entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointRepository extends CrudRepository<PointEntity, Long> {

    List<PointEntity> findAllByLabuserByParent(UserEntity labuserByParent);

}
