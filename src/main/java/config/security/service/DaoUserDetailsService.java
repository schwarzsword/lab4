package config.security.service;

import entity.UserEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.AuthorizationService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userDetailsService")
public class DaoUserDetailsService implements UserDetailsService {

    Logger log = LogManager.getLogger(DaoUserDetailsService.class);


    @Resource
    private AuthorizationService authorizationService;

    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Optional<UserEntity> user = authorizationService.loadUserByUsername(login);

        if (user.isPresent()) {
            UserEntity foundUser = user.get();
            return new User(foundUser.getUsername(), foundUser.getPassword(), getGrantedAuthorities(foundUser));
        }
        log.info("User not found");
        throw new UsernameNotFoundException("Username not found");

    }

    private List<GrantedAuthority> getGrantedAuthorities(UserEntity userEntity) {

        log.info("User was found " + userEntity.getUsername());

        List authorities = new ArrayList<GrantedAuthority>();

        authorities.add(

                new SimpleGrantedAuthority("User")
        );

        log.info("Count of authorities of user:" + authorities.size());

        return authorities;

    }
}