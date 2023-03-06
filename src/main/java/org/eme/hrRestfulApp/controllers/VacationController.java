package org.eme.hrRestfulApp.controllers;

import org.eme.hrRestfulApp.exception.InConsistentDatesException;
import org.eme.hrRestfulApp.exception.NotEnoughBalanceException;
import org.eme.hrRestfulApp.mapper.EmployeeMapper;
import org.eme.hrRestfulApp.model.Employee;
import org.eme.hrRestfulApp.model.VacationBalance;
import org.eme.hrRestfulApp.model.VacationType;
import org.eme.hrRestfulApp.repository.EmployeeRepository;
import org.eme.hrRestfulApp.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
@RequestMapping("/vacation")
public class VacationController {

    @Autowired
    VacationService vacationService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapper employeeMapper;

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

    @PostMapping
    public ModelAndView addVacation(
            @RequestParam("vacationType") String vacationType,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        Employee employee = employeeMapper.fromEntityToModel(employeeRepository.findAll().get(0));
        vacationService.addVacation(employee, startDate, endDate, VacationType.valueOf(vacationType));
        return new ModelAndView("redirect:/vacation");
    }

    @GetMapping
    public ModelAndView vacationForm() {
        Employee employee = employeeMapper.fromEntityToModel(employeeRepository.findAll().get(0));
        VacationBalance annualVacationBalance = vacationService.getBalance(employee, VacationType.Annual);
        VacationBalance sickVacationBalance = vacationService.getBalance(employee, VacationType.Sick);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("Annual", annualVacationBalance);
        modelAndView.addObject("Sick", sickVacationBalance);
        return modelAndView;
    }

}
