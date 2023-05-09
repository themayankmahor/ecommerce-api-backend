package com.mm.ecommerce.services;

import java.util.List;

import com.mm.ecommerce.payloads.UserDto;

public interface UserService {
    
    //UserDto registerNewUser(UserDto userDto);

    ///create user
    UserDto createUser(UserDto userDto);

    ///Register Dto (Normal, Admin, Seller)
    UserDto registerNewNormalUser(UserDto userDto);

    UserDto registerNewAdminUser(UserDto userDto);

    UserDto registerNewSellerUser(UserDto userDto);

    ///Get user by Id
    UserDto getUserById(int userId);

    ///Get All Users
    List<UserDto> getAllUsers();

    ///Update user
    UserDto updateUser(UserDto userDto, int userId);

    ///Delete User
    void deleteUser(int userId);

    ///Check user is already exists or not
    boolean checkEmail(String email);

}
