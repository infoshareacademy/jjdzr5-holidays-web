package org.isa.holidaysweb.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = VacationDAO.TABLE_NAME)
public class VacationDAO {
    public static final String TABLE_NAME = "vacation";
    public static final String COLUMN_PREFIX = "v_";

    public VacationDAO(LocalDate dateFrom, LocalDate dateTo, UserDAO user) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.user = user;

    }

    @Id
    @GeneratedValue
    @org.hibernate.annotations.Type(type = "uuid-char")
    @Column(name = COLUMN_PREFIX + "id")
    private UUID id;
    @Column(name = COLUMN_PREFIX + "date_from")
    private LocalDate dateFrom;
    @Column(name = COLUMN_PREFIX + "date_to")
    private LocalDate dateTo;
    @Column(name = COLUMN_PREFIX + "approved")
    private boolean approved;


    @ManyToOne
    @JoinColumn(name = "u_id")
    private UserDAO user;

}
