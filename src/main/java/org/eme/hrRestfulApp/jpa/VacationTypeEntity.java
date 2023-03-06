package org.eme.hrRestfulApp.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vacation_type")
public class VacationTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "days")
    private int days;

}
