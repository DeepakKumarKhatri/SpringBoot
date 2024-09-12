package com.deepakLearning.journalApp.controllers;

import com.deepakLearning.journalApp.entities.User;
import com.deepakLearning.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User> allUsers = userService.getUsers();
        try {
            if (allUsers!=null && !allUsers.isEmpty()){
                return new ResponseEntity<> (allUsers, HttpStatus.OK);
            }
            return new ResponseEntity<>(new User[0],HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            User oldUser = userService.findByUserName(user.getUserName());
            if (oldUser!=null){
                oldUser.setFullName(user.getFullName());
                oldUser.setPassword(user.getPassword());
                userService.creatUser(oldUser);
                return new ResponseEntity<>("USER UPDATED",HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
