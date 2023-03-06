package org.eme.hrRestfulApp.repository;

import org.eme.hrRestfulApp.jpa.VacationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository  extends JpaRepository<VacationEntity, Long> {

}
