package org.isa.holidaysweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isa.holidaysweb.domain.User;
import org.isa.holidaysweb.enity.Departament;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDetailsDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private Departament departament;
    private String profilePicture;

    public UserDetailsDto(String firstName, String lastName, Departament departament, String profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departament = departament;
        this.profilePicture = profilePicture;
    }
}
