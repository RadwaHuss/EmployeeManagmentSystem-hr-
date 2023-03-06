package org.eme.hrRestfulApp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private Long id;
    private String name;
    private String email;
    private String department;

}
