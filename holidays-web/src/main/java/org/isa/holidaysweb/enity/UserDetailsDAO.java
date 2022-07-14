package org.isa.holidaysweb.enity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = UserDetailsDAO.TABLE_NAME)
public class UserDetailsDAO {

    public static final String TABLE_NAME = "user_details";
    public static final String COLUMN_PREFIX = "ud_";

    public UserDetailsDAO(String firstName, String lastName, Departament departament, UserDAO user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departament = departament;
        this.user = user;
    }

    @Id
    @GeneratedValue
    @org.hibernate.annotations.Type(type = "uuid-char")
    @Column(name = COLUMN_PREFIX + "id")
    private UUID id;
    @Column(name = COLUMN_PREFIX + "first_name")
    private String firstName;
    @Column(name = COLUMN_PREFIX + "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name = COLUMN_PREFIX + "departament")
    private Departament departament;

    @OneToOne
    private UserDAO user;

}
