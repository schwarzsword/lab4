package controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CheckPointService;

@RestController
@RequestMapping("/results")
public class PointController {
    final
    CheckPointService checkPointService;

    @Autowired
    public PointController(CheckPointService checkPointService) {
        this.checkPointService = checkPointService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity getPoints() {
        return ResponseEntity.ok(new Gson().toJson(checkPointService.getPoints()));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity addPoint(@RequestParam("x") String strX,
                            @RequestParam("y") String strY,
                            @RequestParam("r") String strR) {
        String result = checkPointService.savePoint(strX, strY, strR);
        return ResponseEntity.ok("{\"Entering\": \"" + result + "\"}");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity logOut() {
        checkPointService.logOut();
        return ResponseEntity.ok().body("Session was disabled");
    }
}