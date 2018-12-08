package repository;

import entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<UserEntity, String> {
    Optional<UserEntity> findUserByLogin(String login);
}
