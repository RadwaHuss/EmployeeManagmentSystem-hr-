package org.eme.hrRestfulApp.service;



import lombok.Data;
import org.eme.hrRestfulApp.jpa.VacationEntity;
import org.eme.hrRestfulApp.model.Employee;
import org.eme.hrRestfulApp.model.VacationBalance;
import org.eme.hrRestfulApp.model.VacationType;

import java.time.LocalDate;


public interface VacationService {
    public VacationBalance addVacation(Employee employee, LocalDate startDate, LocalDate endDate, VacationType vacationType);

    public VacationBalance getBalance(Employee employee, VacationType vacationType);
}
