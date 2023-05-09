package com.mm.ecommerce.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mm.ecommerce.config.AppConstants;
import com.mm.ecommerce.entity.Role;
import com.mm.ecommerce.entity.User;
import com.mm.ecommerce.exceptions.ApiException;
import com.mm.ecommerce.exceptions.ResourceNotFoundException;
import com.mm.ecommerce.payloads.UserDto;
import com.mm.ecommerce.repository.RoleRepository;
import com.mm.ecommerce.repository.UserRepository;
import com.mm.ecommerce.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    ///Create User
    @Override
    public UserDto createUser(UserDto userDto) {
        
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    ///Get user by ID
    @Override
    public UserDto getUserById(int userId) {
        
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        return modelMapper.map(user, UserDto.class);

    }

    ///Get All Users
    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        List<UserDto> allUsers = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        System.out.println(users);

        return allUsers;

    }

    ///Update user
    @Override
    public UserDto updateUser(UserDto userDto, int userId) {

        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDto.class);
    }


    ///Delete User
    @Override
    public void deleteUser(int userId) {

        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        userRepository.delete(user);
    }

    ///Register New User
    @Override
    public UserDto registerNewNormalUser(UserDto userDto) {

        //check user is already exists or not
        boolean alreadyExists = checkEmail(userDto.getEmail());

        if (alreadyExists)
        {
            throw new ApiException("User already exists !!!");
        }

        User user = modelMapper.map(userDto, User.class);

        //encoded the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //set roles to new user
        Role role = roleRepository.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);

        User newUser = userRepository.save(user);

        return modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public boolean checkEmail(String email) {
        
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDto registerNewAdminUser(UserDto userDto) {

        //check user is already exists or not
        boolean alreadyExists = checkEmail(userDto.getEmail());

        if (alreadyExists)
        {
            throw new ApiException("Admin already exists !!!");
        }

        User user = modelMapper.map(userDto, User.class);

        //encoded the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //set roles to new user
        Role role = roleRepository.findById(AppConstants.ADMIN_USER).get();
        user.getRoles().add(role);

        User newUser = userRepository.save(user);

        return modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto registerNewSellerUser(UserDto userDto) {
        
        
        //check user is already exists or not
        boolean alreadyExists = checkEmail(userDto.getEmail());

        if (alreadyExists)
        {
            throw new ApiException("Seller already exists !!!");
        }

        User user = modelMapper.map(userDto, User.class);

        //encoded the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //set roles to new user
        Role role = roleRepository.findById(AppConstants.SELLER_USER).get();
        user.getRoles().add(role);

        User newUser = userRepository.save(user);

        return modelMapper.map(newUser, UserDto.class);

    }
    
    
}
