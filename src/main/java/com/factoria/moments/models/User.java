package com.factoria.moments.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column

    private Long id;
    private String username;
    private String userImg;

    private String email;
    @JsonIgnore
    private String password;
    public User(String name) {
        this.username = name;
    }

    @ManyToMany
    private Set<Role> roles;


    public User(long id, String name) {
        this.username = name;
        this.id = id;
    }

    public User(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;
    }


}


