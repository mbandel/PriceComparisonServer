package com.pc.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pc.poster.Poster;
import com.pc.rating.Rating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "UsersUsers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "UserName")
    private String username;

    @Column(name = "Email")
    @Email
    private String email;

    @Column(name = "Password")
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Poster> posters;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Rating> ratings;

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.username=username;
        this.email=email;
        this.password=password;
    }

    public void updateValues(UserDto userDto) {
        if (userDto.getUsername() != null) {
            this.username = userDto.getUsername();
        }
        if (userDto.getEmail() != null) {
            this.email = userDto.getEmail();
        }
    }
}
