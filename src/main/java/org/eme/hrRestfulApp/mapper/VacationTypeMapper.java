package org.eme.hrRestfulApp.mapper;

import org.eme.hrRestfulApp.jpa.VacationTypeEntity;
import org.eme.hrRestfulApp.model.VacationType;
import org.eme.hrRestfulApp.repository.VacationTypeRepository;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class VacationTypeMapper {
    @Autowired
    VacationTypeRepository vacationTypeRepository;
    @Mapping(target = "name", source = "type")
     VacationType fromEntityToModel(VacationTypeEntity vacationTypeEntity){
            if(vacationTypeEntity.getType().equalsIgnoreCase("Annual")){
                    return VacationType.Annual;
            }else
                return VacationType.Sick;
    }

    @Mapping(target = "type", source = "name")
     VacationTypeEntity fromModelToEntity(VacationType vacationType){
       return vacationTypeRepository.findByType(vacationType.name());
    }

}
