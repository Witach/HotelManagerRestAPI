package com.example.demo.service;

import com.example.demo.entity.UserRole;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserRoleRepository userRoleRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    public void addNewUser(UserModel userModel) throws UserAlreadyExistsException {
        Optional<User> userOptional = this.userRepository.findUserByEmail(userModel.getEmail());
        if(userOptional.isPresent())
            throw new UserAlreadyExistsException(userModel.getEmail());

        User user = User.createUserFromUserModel(userModel);

        encodeUserPassword(user);

        addDefaultRoleToUser(user.getRole());

        this.userRepository.save(user);
    }

    private void encodeUserPassword(User user){
        String password = user.getPassword();
        String passwordEncoded = passwordEncoder.encode(password);
        user.setPassword(passwordEncoded);
    }

    private void addDefaultRoleToUser(Set<UserRole> userRoleSet){
        UserRole defaultRole = userRoleRepository.findUserRoleByName("USER_ROLE").get();
        userRoleSet.add(defaultRole);
    }

}
