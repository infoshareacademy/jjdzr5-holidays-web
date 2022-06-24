package org.isa.holidaysweb.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="TBL_EMPLOYEES")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="first_name")
    @NotEmpty(message = "First name is required")
    @Size(min = 3, max = 50, message = "First name must be between 3 and 20 characters")
    private String firstName;
    
    @Column(name="last_name")
    @NotEmpty(message = "Last name is required")
    @Size(min = 3, max = 50, message = "Last name must be between 3 and 20 characters")
    private String lastName;
    
    @Column(name="department")
    @NotNull(message = "Department is required")
    private String department;

    @Column(name="created_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss" , iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime registryEmployee = LocalDateTime.now();

    private Integer availableHoliday;


}