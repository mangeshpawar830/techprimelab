package com.techprime.service;

import com.techprime.model.User;
import com.techprime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

   

	 public boolean login(String email, String password) {
	        User user = userRepository.findByEmail(email);
	        if (user != null) {
	            if (password.equals(user.getPassword())) {
	                System.out.println("Login successfully");
	                return true;
	            } else {
	                System.out.println("Invalid credentials");
	            }
	        } else {
	            System.out.println("User not found");
	        }
	        return false;
	    }
}