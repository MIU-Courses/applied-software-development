package edu.miu.cs.cs489.ems.service;

import edu.miu.cs.cs489.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;

    public void printReport() {
        System.out.println("Employees report:");
        repository.findAllByOrderBySalaryAscLastNameDesc().forEach(System.out::println);
    }
}
