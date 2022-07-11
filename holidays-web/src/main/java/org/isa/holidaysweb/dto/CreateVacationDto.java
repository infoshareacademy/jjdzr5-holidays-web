package org.isa.holidaysweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateVacationDto {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private UUID userId;
}
