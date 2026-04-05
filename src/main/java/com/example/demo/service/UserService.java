package com.example.demo.service;

import java.util.List;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@Service
public class UserService {
	 private final UserRepository repo;
	    private final PasswordEncoder encoder;

	    public UserService(UserRepository repo, PasswordEncoder encoder) {
	        this.repo = repo;
	        this.encoder = encoder;
	    }

	    public User createUser(User user) {
	    	 user.setPassword(encoder.encode(user.getPassword())); 
	    	 user.setRole(Role.VIEWER);     
	    	 user.setActive(true);                    
	    	 return repo.save(user);
	    }

	    public List<User> getAllUsers() {
	        return repo.findAll();
	    }
	    
	    

}
