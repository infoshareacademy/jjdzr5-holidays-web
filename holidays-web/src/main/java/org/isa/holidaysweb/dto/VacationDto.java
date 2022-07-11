package org.isa.holidaysweb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class VacationDto {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private UUID userId;
    private boolean isApproved;
}
