package com.example.demo.entity;


import com.example.demo.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
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

    public User() {
        administratedBill = new HashSet<>();
        role = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Bill> getAdministratedBill() {
        return administratedBill;
    }

    public void setAdministratedBill(Set<Bill> administratedBill) {
        this.administratedBill = administratedBill;
    }

    public Set<UserRole> getRole() {
        return role;
    }

    public void setRole(Set<UserRole> role) {
        this.role = role;
    }

    public static User createUserFromUserModel(UserModel userModel){
        User user = new User();
        user.setPassword(userModel.getPassword());
        user.setEmail(userModel.getEmail());
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", administratedBill=" + administratedBill +
                ", role=" + role +
                '}';
    }
}
