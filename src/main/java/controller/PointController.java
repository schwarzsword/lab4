package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    public
    ResponseEntity getPoints(@AuthenticationPrincipal User user) {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        return ResponseEntity.ok(gson.toJson(checkPointService.getPoints(authorizationService.loadUserByUsername(user.getUsername()).get())));
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