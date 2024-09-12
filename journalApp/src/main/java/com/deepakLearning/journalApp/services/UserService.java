package com.deepakLearning.journalApp.services;

import com.deepakLearning.journalApp.entities.User;
import com.deepakLearning.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User creatUser(User user){
        return userRepo.save(user);
    }

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public Optional<User> getUser(ObjectId userId){
        return userRepo.findById(userId);
    }

    public void deleteUser(ObjectId userId){
        userRepo.deleteById(userId);
    }

    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

}
