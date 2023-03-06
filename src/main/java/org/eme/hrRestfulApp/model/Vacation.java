package org.eme.hrRestfulApp.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class Vacation {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private VacationType vacationType;
    private Employee employee;

}
