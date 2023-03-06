package org.eme.hrRestfulApp.mapper;

import org.eme.hrRestfulApp.jpa.VacationBalanceEntity;
import org.eme.hrRestfulApp.model.VacationBalance;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {VacationTypeMapper.class, EmployeeMapper.class},  injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VacationBalanceMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "days", source = "days")
    @Mapping(target = "vacationType", source = "vacationTypeEntity")
    @Mapping(target = "employee", source = "employeeEntity")
    VacationBalance fromEntityToModel(VacationBalanceEntity vacationBalanceEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "days", source = "days")
    @Mapping(target = "vacationTypeEntity", source = "vacationType")
    @Mapping(target = "employeeEntity", source = "employee")
    VacationBalanceEntity fromModelToEntity(VacationBalance vacationBalanceEntity);

}

