package org.isa.holidaysweb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.isa.holidaysweb.entity.Departament;

import java.time.LocalDate;
import java.util.UUID;
@Data
@NoArgsConstructor
public class VacationWithDetailsDto {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private UUID userId;
    private boolean isApproved;
    private String firstName;
    private String lastName;
    private String userName;
    private UUID vacationId;
    private boolean isInFuture;




    public VacationWithDetailsDto(LocalDate dateFrom, LocalDate dateTo, UUID userId, boolean isApproved, String firstName, String lastName, String userName, UUID vacationId) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userId = userId;
        this.isApproved = isApproved;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.vacationId = vacationId;

    }
}
