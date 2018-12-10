package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AuthorizationService;

@RestController
public class AuthorizationController {

    final
    AuthorizationService authorizationService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam("login") String login, @RequestParam("password") String password) {
        if (authorizationService.logIn(login, password)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }


}
