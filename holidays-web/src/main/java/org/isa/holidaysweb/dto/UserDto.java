package org.isa.holidaysweb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDto {

    private UUID id;
    private String userName;
    private String password;
    private String role;
    private boolean isActive;


    public List<String> getRolesList() {
        if (role.length()>0) {
            return Arrays.asList(role.split(","));
        }
        return new ArrayList<>();
    }
}
