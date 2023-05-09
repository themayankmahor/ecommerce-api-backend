package com.mm.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.ecommerce.payloads.ApiResponse;
import com.mm.ecommerce.payloads.UserDto;
import com.mm.ecommerce.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    ///Create new user
    @PostMapping("/new-user")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createdUser = userService.createUser(userDto);

        return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
    }

    ///Get User By ID
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") int userId)
    {
        UserDto userDto = userService.getUserById(userId);

        return ResponseEntity.ok(userDto);
    }

    ///Get All Users
    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    ///Update User
    @PutMapping("/update-user/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") int userId)
    {
        return ResponseEntity.ok(userService.updateUser(userDto, userId));
    }

    ///Delete User
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete-user/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") int userId)
    {
        userService.deleteUser(userId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

}
