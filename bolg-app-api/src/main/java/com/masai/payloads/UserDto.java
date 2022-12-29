package com.masai.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Integer id;

	@NotEmpty
	@Size(min = 4, message = " user name should be min of 4 characters ")
	private String name;

	@Email(message = " email is not valid !")
	private String email;

	@NotEmpty
	@Pattern(regexp = "(^([+]\\d{2}([ ])?)?\\d{10}$)", message = "Number should be in format: {+91 1234567890, +911234567890, 1234567890}")
	private String mobile;

	@NotEmpty
	@Size(min = 4, max = 10, message = "password must be min of 4 and max 10")
	private String password;

	@NotEmpty
	private String about;

}
