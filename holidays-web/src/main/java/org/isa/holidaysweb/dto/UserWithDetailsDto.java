package org.isa.holidaysweb.dto;

import lombok.Getter;
import lombok.Setter;
import org.isa.holidaysweb.entity.Departament;

import java.util.UUID;
@Getter
@Setter
public class UserWithDetailsDto {
    private UUID userId;
    private String userName;
    private String password;
    private String role;
    private boolean isActive;
    private UUID userDetailsId;
    private String firstName;
    private String lastName;
    private Departament departament;
    private String profilePicture;


}
