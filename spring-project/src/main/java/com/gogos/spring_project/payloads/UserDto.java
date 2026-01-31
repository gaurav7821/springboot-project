package com.gogos.spring_project.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "User name must be min of 4 characters !!")
    private String name;

    @Email(message = "Please Enter a valid mail")
//    @Pattern(regexp = "abc@gmail.com")
    private String email;

    @NotEmpty
    @Size(min = 4, max = 10, message = "Password must be min of 4 char and max 10")
    private String password;

    @NotEmpty
    private String about;

}
