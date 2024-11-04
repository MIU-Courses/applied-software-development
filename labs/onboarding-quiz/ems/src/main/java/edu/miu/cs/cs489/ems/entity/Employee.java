package edu.miu.cs.cs489.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
}
