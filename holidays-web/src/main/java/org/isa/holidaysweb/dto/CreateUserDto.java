package org.isa.holidaysweb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateUserDto {
    private UUID id;
    private String userName;
    private String password;
    private String role;
    public CreateUserDto(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public CreateUserDto(UUID id, String userName, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
