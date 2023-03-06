package org.eme.hrRestfulApp.repository;


import org.eme.hrRestfulApp.jpa.VacationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationTypeRepository  extends JpaRepository<VacationTypeEntity, Long> {
   public VacationTypeEntity findByType(String type);
}