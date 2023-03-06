package org.eme.hrRestfulApp.jpa;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "Vacation_Balance")
public class VacationBalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_type_id")
    private VacationTypeEntity vacationTypeEntity;

    @Column(name = "days")
    private int days;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;


}
