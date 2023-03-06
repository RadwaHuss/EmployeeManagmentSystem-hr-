package org.eme.hrRestfulApp.model;

import lombok.Data;

@Data
public class VacationBalance {
    private Long id;
    private int days;
    private int vacationTypeId;
    private VacationType vacationType;
    private Employee employee;
}
