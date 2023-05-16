package com.mm.ecommerce.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars !!")
    private String password;

    private Set<RoleDto> roles = new HashSet<>();

    @JsonIgnore
	public String getPassword()
	{
		return password;
	}
	
	@JsonProperty
	public void setPassword(String password)
	{
		this.password = password;
	}

}
