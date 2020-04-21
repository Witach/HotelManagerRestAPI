package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserModel {
    @Email
    @NotBlank
    String email;
    @NotBlank
    @Size(min = 8, max = 24)
    String password;
}
