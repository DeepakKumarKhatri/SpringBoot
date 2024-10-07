package com.deepakLearning.journalApp.controllers;

import com.deepakLearning.journalApp.entities.User;
import com.deepakLearning.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/general")
public class GeneralController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        try {
            User newUser = userService.creatUser(user);
            if (newUser!=null){
                return new ResponseEntity<> (newUser, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
