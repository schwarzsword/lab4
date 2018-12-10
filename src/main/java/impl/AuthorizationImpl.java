package impl;

import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.AuthorizationService;

import java.util.Optional;

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
        String hashed;
        Optional<UserEntity> user = userRepository.findUserByLogin(login);
        if(user.isPresent()){
            hashed = user.get().getPassword();
         } else hashed = BCrypt.hashpw(" ", BCrypt.gensalt());
        return BCrypt.checkpw(password, hashed);
    }
}
