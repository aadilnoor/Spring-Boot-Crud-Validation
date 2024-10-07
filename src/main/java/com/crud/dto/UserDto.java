package com.crud.dto;

import jakarta.persistence.Id; 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	@Id
	private Integer id;

	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	@NotEmpty
	private String address;
	
	@Email
	private String email;
 
	@Min(18)
	@Max(60)
	private int age;

}
