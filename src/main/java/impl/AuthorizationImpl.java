package impl;

import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.AuthorizationService;

@Service
public class AuthorizationImpl implements AuthorizationService {
    final
    UserRepository userRepository;

    @Autowired
    public AuthorizationImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean logIn(String login, String password) {
        return BCrypt.checkpw(password, userRepository
                .findUserByLogin(login)
                .map(UserEntity::getPassword).orElse("User not found"));
    }
}
