package edu.miu.cs.cs489.ems.repository;

import edu.miu.cs.cs489.ems.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAllByOrderBySalaryAscLastNameDesc();
}
