package org.eme.hrRestfulApp.mapper;

import org.eme.hrRestfulApp.jpa.VacationEntity;
import org.eme.hrRestfulApp.model.Vacation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",  uses = {VacationTypeMapper.class, EmployeeMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface VacationMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "vacationType", source = "vacationTypeEntity")
    @Mapping(target = "employee", source = "employeeEntity")
    Vacation fromEntityToModel(VacationEntity vacationBalanceEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "vacationTypeEntity", source = "vacationType")
    @Mapping(target = "employeeEntity", source = "employee")
    VacationEntity fromModelToEntity(Vacation vacationBalanceEntity);

}
