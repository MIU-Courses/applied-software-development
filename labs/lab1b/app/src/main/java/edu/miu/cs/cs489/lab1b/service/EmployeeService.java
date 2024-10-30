package edu.miu.cs.cs489.lab1b.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489.lab1b.model.Employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {
    private final List<Employee> employees;
    private final ObjectMapper mapper;

    public EmployeeService(List<Employee> employees) {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setDateFormat(new StdDateFormat());
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.employees = employees;
    }

    public String getAllEmployeesInJson() throws JsonProcessingException {
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName)
                        .thenComparing(Comparator.comparing(Employee::getYearlySalary).reversed()))
                .collect(Collectors.toList());
        return mapper.writeValueAsString(sortedEmployees);
    }

    public String getMonthlyUpcomingEnrolleesReport() throws JsonProcessingException {
        LocalDate nextMonthStart = LocalDate.now().plusMonths(1).withDayOfMonth(1);
        LocalDate nextMonthEnd = nextMonthStart.withDayOfMonth(nextMonthStart.lengthOfMonth());

        List<Employee> upcomingEnrollees = employees.stream()
                .filter(employee -> employee.getPensionPlan() == null && ChronoUnit.YEARS.between(employee.getEmploymentDate(), nextMonthEnd) >= 5)
                .sorted(Comparator.comparing(Employee::getEmploymentDate))
                .collect(Collectors.toList());

        return mapper.writeValueAsString(upcomingEnrollees);
    }
}

