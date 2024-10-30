package edu.miu.cs.cs489.lab1b.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import edu.miu.cs.cs489.lab1b.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {
    private final List<Employee> employees;
    private final ObjectMapper mapper;

    public EmployeeService(List<Employee> employees) {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setDateFormat(new StdDateFormat());
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
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstOfNextMonth = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastOfNextMonth = calendar.getTime();

        List<Employee> upcomingEnrollees = employees.stream()
                .filter(employee -> employee.getPensionPlan() == null || !employee.getEmploymentDate().before(firstOfNextMonth)
                        && !employee.getEmploymentDate().after(lastOfNextMonth))
                .sorted(Comparator.comparing(Employee::getEmploymentDate))
                .collect(Collectors.toList());

        return mapper.writeValueAsString(upcomingEnrollees);
    }
}

