package org.isa.holidaysweb.enity;

import lombok.*;
import org.isa.holidaysweb.annotation.ValidDates;
import org.isa.holidaysweb.domain.Vacation;
import org.springframework.format.annotation.DateTimeFormat;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_PREFIX + "id")
    private UUID id;
    @Column(name = COLUMN_PREFIX + "date_from")
    private LocalDate dateFrom;
    @Column(name = COLUMN_PREFIX + "date_to")
    private LocalDate dateTo;

    @ManyToOne
    private UserDAO user;


}
