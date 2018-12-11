//package controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import service.AuthorizationService;
//
//@RestController
//public class AuthorizationController {
//
//    final
//    AuthorizationService authorizationService;
//
//    @Autowired
//    public AuthorizationController(AuthorizationService authorizationService) {
//        this.authorizationService = authorizationService;
//    }
//
////    @RequestMapping(value = "/login", method = RequestMethod.POST)
////    public ResponseEntity login(@RequestParam("username") String login, @RequestParam("password") String password) {
////        if (authorizationService.logIn(login, password)) {
////            return ResponseEntity.ok(true);
////        }
////        return ResponseEntity.ok(false);
////    }
////    @RequestMapping(value = "/logout", method = RequestMethod.POST)
////    public
////    @ResponseBody
////    ResponseEntity logOut() {
//////        checkPointService.logOut();
////        return ResponseEntity.ok().body("Session was disabled");
////    }
//
//}
