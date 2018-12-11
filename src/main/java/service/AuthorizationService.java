package service;

import entity.UserEntity;

import java.util.Optional;

public interface AuthorizationService {
    boolean logIn(String login, String password);
    Optional<UserEntity> loadUserByUsername(String login);
    void logOut();
}
