package com.deepakLearning.journalApp.services;

import com.deepakLearning.journalApp.entities.User;
import com.deepakLearning.journalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUserName = userRepo.findByUserName(username);
        if (byUserName != null) {
            return org.springframework.security.core.userdetails.
                    User.builder().
                    username(byUserName.getUserName()).
                    password(byUserName.getPassword()).
                    roles(byUserName.getRoles().toArray(new String[0])).
                    build();
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}
