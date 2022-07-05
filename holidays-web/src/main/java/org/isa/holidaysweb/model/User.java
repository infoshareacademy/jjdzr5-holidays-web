package org.isa.holidaysweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstNameAndSurname;

    private String username;

    private String password;

    private String departments;

    private LocalDateTime registrationDate = LocalDateTime.now();

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;


}
