package com.cloudcom.employee;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements UserDetailsService {

    private static final String EMPLOYEE_NOT_FOUND_MESSAGE =
            "employee with username %s not found";

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository,
                           PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(EMPLOYEE_NOT_FOUND_MESSAGE, username)));
    }

    public String signUpEmployee(Employee employee) {
        boolean employeeExists = employeeRepository
                .findByUsername(employee.getUsername())
                .isPresent();

        if (employeeExists) {
            throw new IllegalStateException("username already taken");
        }

        String encodedPassword = passwordEncoder.encode(employee.getPassword());

        employee.setPassword(encodedPassword);

        employeeRepository.save(employee);

        return "employee has been registered";
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
