package org.isa.holidaysweb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.isa.holidaysweb.entity.Departament;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateUserDetailsDto {
    private String firstName;
    private String lastName;
    private Departament departament;
    private UUID userId;
    private String profilePicture;

    public CreateUserDetailsDto(String firstName, String lastName, Departament departament, UUID userId, String profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departament = departament;
        this.userId = userId;
        this.profilePicture = profilePicture;
    }

}
