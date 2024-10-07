package com.deepakLearning.journalApp.controllers;

import com.deepakLearning.journalApp.entities.User;
import com.deepakLearning.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
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

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User oldUser = userService.findByUserName(userName);
            if (oldUser!=null){
                oldUser.setFullName(user.getFullName());
                oldUser.setPassword(user.getPassword());
                userService.creatUser(oldUser);
                return new ResponseEntity<>("USER UPDATED",HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>("User Not Found",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            userService.deleteUser(userName);
            return new ResponseEntity<>("USER UPDATED",HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
