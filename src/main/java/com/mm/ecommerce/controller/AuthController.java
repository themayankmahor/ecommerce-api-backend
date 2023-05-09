package com.mm.ecommerce.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.ecommerce.exceptions.ApiException;
import com.mm.ecommerce.payloads.JwtAuthRequest;
import com.mm.ecommerce.payloads.JwtAuthResponse;
import com.mm.ecommerce.payloads.UserDto;
import com.mm.ecommerce.security.JwtTokenHelper;
import com.mm.ecommerce.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    private void authenticate(String username, String password)
    {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            
            System.out.println("Invalid Details !!!");

            throw new ApiException("Invalid Username or Password");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception
    {
        authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        String token = jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();

        response.setToken(token);
        response.setUser(modelMapper.map(userDetails, UserDto.class));

        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    //register new user api
	@PostMapping("/user/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto registeredUser = userService.registerNewNormalUser(userDto);
		
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}

    //register new admin
	@PostMapping("/admin/register")
	public ResponseEntity<UserDto> registerAdmin(@Valid @RequestBody UserDto userDto)
	{
		UserDto registeredUser = userService.registerNewAdminUser(userDto);
		
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}

    //register new seller
	@PostMapping("/seller/register")
	public ResponseEntity<UserDto> registerSeller(@Valid @RequestBody UserDto userDto)
	{
		UserDto registeredUser = userService.registerNewSellerUser(userDto);
		
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}
    
}
