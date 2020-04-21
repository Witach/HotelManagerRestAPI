package com.example.demo.model;

import com.example.demo.validators.UserExists;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PersonModel {

    @NotBlank
    @Size(min = 3, max = 32)
    String firstName;

    @NotBlank
    @Size(min = 3, max = 32)
    String lastName;

    @UserExists
    String userEmail;
}
