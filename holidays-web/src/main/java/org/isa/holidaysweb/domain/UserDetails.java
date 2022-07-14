package org.isa.holidaysweb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.isa.holidaysweb.enity.Departament;

import javax.validation.constraints.Size;
import java.util.UUID;
@Data
@NoArgsConstructor
public class UserDetails {

    @Size(min=3, max=30)
    private String firstName;
    @Size(min=3, max=50)
    private String lastName;
    private Departament departament;
}
