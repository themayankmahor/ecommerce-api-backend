package com.mm.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mm.ecommerce.entity.User;
import com.mm.ecommerce.exceptions.ResourceNotFoundException;
import com.mm.ecommerce.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loading user from db by username
        User user =  userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "email : " + username, 0));

        return user;

    }

}
