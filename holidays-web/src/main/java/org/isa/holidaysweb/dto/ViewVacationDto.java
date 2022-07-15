package org.isa.holidaysweb.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Setter
@Getter
public class ViewVacationDto {
    private UUID id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private boolean isInFuture;
    private boolean approved;

    public ViewVacationDto(UUID id, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        if(dateFrom.isBefore(LocalDate.now())) {
            this.isInFuture = false;
        } else {
            this.isInFuture = true;
        }
    }
}
