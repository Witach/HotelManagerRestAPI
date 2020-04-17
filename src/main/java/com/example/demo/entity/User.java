package com.example.demo.entity;


import com.example.demo.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;

    @Column(unique = true)
    String email;

    @JsonIgnore
    String password;

    @JsonIgnore
    @OneToMany(mappedBy = "administrator")
    Set<Bill> administratedBill;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_role_id", referencedColumnName = "user_role_id")}
    )
    @Column(name = "user_role")
    Set<UserRole> role;

    @OneToOne
    Person person;

    public User() {
        administratedBill = new HashSet<>();
        role = new HashSet<>();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static User createUserFromUserModel(UserModel userModel){
        User user = new User();
        user.setPassword(userModel.getPassword());
        user.setEmail(userModel.getEmail());
        return user;
    }

}
