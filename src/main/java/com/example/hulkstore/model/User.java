package com.example.hulkstore.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name ="name")
    @NotBlank(message = "Name is required!")
    private String name;
    @Column(name ="lastname")
    @NotBlank(message = "lastName is required!")
    private String lastName;
    @Column(name ="email")
    @NotBlank(message = "Email is required!")
    private String email;
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 30)
    private int password;

    public User(Long id, @NotBlank(message = "Name is required!") String name, @NotBlank(message = "lastName is required!") String lastName, @NotBlank(message = "Email is required!") String email, @NotNull(message = "Password cannot be null") @Size(min = 6, max = 30) int password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                '}';
    }

}
