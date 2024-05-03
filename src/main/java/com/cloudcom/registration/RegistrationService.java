package com.cloudcom.registration;

import com.cloudcom.employee.Employee;
import com.cloudcom.employee.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final EmployeeService employeeService;

    public RegistrationService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void register(RegistrationRequest request) {
        int employeeTokens = 0;

        employeeService.signUpEmployee(
                new Employee(
                        request.getUsername(),
                        request.getPassword(),
                        employeeTokens
                )
        );
    }
}
