package org.eme.hrRestfulApp.service.impl;

import org.eme.hrRestfulApp.exception.InConsistentDatesException;
import org.eme.hrRestfulApp.exception.NotEnoughBalanceException;
import org.eme.hrRestfulApp.jpa.VacationBalanceEntity;
import org.eme.hrRestfulApp.jpa.VacationTypeEntity;
import org.eme.hrRestfulApp.mapper.EmployeeMapper;
import org.eme.hrRestfulApp.mapper.VacationBalanceMapper;
import org.eme.hrRestfulApp.mapper.VacationMapper;
import org.eme.hrRestfulApp.model.Employee;
import org.eme.hrRestfulApp.model.Vacation;
import org.eme.hrRestfulApp.model.VacationBalance;
import org.eme.hrRestfulApp.model.VacationType;
import org.eme.hrRestfulApp.repository.VacationBalanceRepository;
import org.eme.hrRestfulApp.repository.VacationRepository;
import org.eme.hrRestfulApp.repository.VacationTypeRepository;
import org.eme.hrRestfulApp.service.VacationService;
import org.eme.hrRestfulApp.utils.VacationCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VacationServiceImpl implements VacationService {

    @Autowired
    VacationRepository vacationRepository;

    @Autowired
    VacationTypeRepository vacationTypeRepository;
    @Autowired
    VacationBalanceRepository vacationBalanceRepository;

    @Autowired
    VacationMapper vacationMapper;

    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    private VacationBalanceMapper vacationBalanceMapper;

    @Override
    public VacationBalance addVacation(Employee employee, LocalDate startDate, LocalDate endDate, VacationType vacationType) {

        int days = VacationCalculator.calculateVacationDaysExcludingWeekends(startDate, endDate);

        if (endDate.isBefore(startDate)) {
            throw new InConsistentDatesException("end date can't be before start date");
        }

        VacationBalance balance = getBalance(employee, vacationType);
        if (balance.getDays() < VacationCalculator.calculateVacationDaysExcludingWeekends(startDate, endDate)) {
            throw new NotEnoughBalanceException("there isn't enough balance for the selected vacation dates");
        }

        //create vacation
        Vacation vacationDays = Vacation.builder().startDate(startDate).endDate(endDate)
                .vacationType(vacationType).employee(employee).build();

        //insert in vacation Table
        vacationRepository.save(vacationMapper.fromModelToEntity(vacationDays));

        //update  days in Vacation Balance
        balance.setDays(balance.getDays() - days);

        vacationBalanceRepository.save(vacationBalanceMapper.fromModelToEntity(balance));

        return balance;
    }

    @Override
    public VacationBalance getBalance(Employee employee, VacationType vacationType) {
        //get days in Vacation Balance
        VacationBalanceEntity vacationBalanceEntity = vacationBalanceRepository.findByEmployeeEntityAndVacationTypeEntity(employeeMapper.fromModelToEntity(employee),
                vacationTypeRepository.findByType(vacationType.name()));

        VacationTypeEntity vacationTypeEntity = vacationTypeRepository.findByType(vacationType.name());

        if (vacationBalanceEntity == null) {
            VacationBalance vacationBalance = new VacationBalance();
            vacationBalance.setEmployee(employee);
            vacationBalance.setVacationType(vacationType);

            vacationBalance.setDays(vacationTypeEntity.getDays());
            vacationBalanceRepository.save(vacationBalanceMapper.fromModelToEntity(vacationBalance));
            return vacationBalance;
        }
        return vacationBalanceMapper.fromEntityToModel(vacationBalanceEntity);
    }
}
