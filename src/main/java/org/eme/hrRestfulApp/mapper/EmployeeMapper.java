package org.eme.hrRestfulApp.mapper;

import org.eme.hrRestfulApp.jpa.EmployeeEntity;
import org.eme.hrRestfulApp.model.Employee;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public interface EmployeeMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "department", source = "department")
    Employee fromEntityToModel(EmployeeEntity employeeEntity);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "department", source = "department")
    EmployeeEntity fromModelToEntity(Employee employee);
}
