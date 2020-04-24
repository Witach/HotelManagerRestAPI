package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserModel {
    @Email
    @NotNull
    String email;
    @NotNull
    @Size(min = 5, max = 24)
    String password;
}
