package org.isa.holidaysweb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserDto {
    private String userName;
    private String password;
    private String role;
    public CreateUserDto(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
