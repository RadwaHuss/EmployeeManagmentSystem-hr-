package org.eme.hrRestfulApp.exception;

import org.eme.hrRestfulApp.mapper.EmployeeMapper;
import org.eme.hrRestfulApp.model.Employee;
import org.eme.hrRestfulApp.model.VacationBalance;
import org.eme.hrRestfulApp.model.VacationType;
import org.eme.hrRestfulApp.repository.EmployeeRepository;
import org.eme.hrRestfulApp.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class InConsistentDatesExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    VacationService vacationService;
    @ExceptionHandler(InConsistentDatesException.class)
    public ModelAndView handleInConsistentDateError(HttpServletRequest request, InConsistentDatesException ex) {
        Employee employee = employeeMapper.fromEntityToModel(employeeRepository.findAll().get(0));
        VacationBalance annualVacationBalance = vacationService.getBalance(employee, VacationType.Annual);
        VacationBalance sickVacationBalance = vacationService.getBalance(employee, VacationType.Sick);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("Annual", annualVacationBalance);
        modelAndView.addObject("Sick", sickVacationBalance);
        modelAndView.addObject("datesError", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ModelAndView handleNotEnoughBalanceError(HttpServletRequest request, NotEnoughBalanceException ex) {
        Employee employee = employeeMapper.fromEntityToModel(employeeRepository.findAll().get(0));
        VacationBalance annualVacationBalance = vacationService.getBalance(employee, VacationType.Annual);
        VacationBalance sickVacationBalance = vacationService.getBalance(employee, VacationType.Sick);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("Annual", annualVacationBalance);
        modelAndView.addObject("Sick", sickVacationBalance);
        modelAndView.addObject("balanceError", ex.getMessage());
        return modelAndView;
    }
}
