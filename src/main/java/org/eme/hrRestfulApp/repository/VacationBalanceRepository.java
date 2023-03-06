package org.eme.hrRestfulApp.repository;


import org.eme.hrRestfulApp.jpa.EmployeeEntity;
import org.eme.hrRestfulApp.jpa.VacationBalanceEntity;
import org.eme.hrRestfulApp.jpa.VacationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationBalanceRepository extends JpaRepository<VacationBalanceEntity, Long> {
    public VacationBalanceEntity  findByEmployeeEntityAndVacationTypeEntity(EmployeeEntity employee, VacationTypeEntity vacationTypeEntity);
}
