package com.mm.ecommerce.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;
    
    @NotEmpty(message = "First name is Required !!")
    @Size(min = 4, message = "First name must not be min of 4 characters")
    private String firstName;

    @NotEmpty(message = "Last name is required !!")
    @Size(min = 4, message = "Last name must not be min of 4 characters")
    private String lastName;

    @NotEmpty(message = "Email is Required !!")
    @Size(message = "Email address is not valid")
    private String email;

    private String password;

}
