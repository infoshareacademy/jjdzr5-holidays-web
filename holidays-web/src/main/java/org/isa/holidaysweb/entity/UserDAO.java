package org.isa.holidaysweb.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = UserDAO.TABLE_NAME)
public class UserDAO {

    public static final String TABLE_NAME = "user";
    public static final String COLUMN_PREFIX = "u_";

    public UserDAO(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.isActive = true;
    }

    @Id
    @GeneratedValue
    @org.hibernate.annotations.Type(type = "uuid-char")
    @Column(name = COLUMN_PREFIX + "id")
    private UUID id;

    @Size(min = 3, max = 25, message = "{validation.userName}")
    @NotBlank(message = "{validation.blank}")
    @Column(name = "u_username", unique = true)
    private String userName;

    @NotBlank(message = "{validation.blank}")
    @Column(name = COLUMN_PREFIX + "password", nullable = false)
    private String password;

    @Column(name = COLUMN_PREFIX + "role")
    private String role;

    @Column(name = COLUMN_PREFIX + "is_active")
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<VacationDAO> vacationList;

    public List<String> getRolesList() {
        if (role.length() > 0) {
            return Arrays.asList(role.split(","));
        }
        return new ArrayList<>();
    }

}

