package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import service.AuthorizationService;
import service.CheckPointService;

@RestController
@RequestMapping("/results")
public class PointController {
    final
    CheckPointService checkPointService;
    final AuthorizationService authorizationService;

    @Autowired
    public PointController(CheckPointService checkPointService, AuthorizationService authorizationService) {
        this.checkPointService = checkPointService;
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getPoints(@AuthenticationPrincipal User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Hibernate5Module());

        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(checkPointService.getPoints(authorizationService.loadUserByUsername(user.getUsername()).get())));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity addPoint(@RequestParam("x") String strX,
                            @RequestParam("y") String strY,
                            @RequestParam("r") String strR,
                            @AuthenticationPrincipal User user) {
        UserEntity userEntity = authorizationService.loadUserByUsername(user.getUsername()).get();
        String result = checkPointService.savePoint(strX, strY, strR, userEntity);
        return ResponseEntity.ok("{\"Entering\": \"" + result + "\"}");
    }

}