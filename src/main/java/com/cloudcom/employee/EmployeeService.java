package com.cloudcom.employee;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void signUpEmployee(Employee employee) {
        boolean employeeExists = employeeExists(employee.getUsername());

        if (employeeExists) {
            throw new IllegalStateException("username already taken");
        }

        String encodedPassword = passwordEncoder.encode(employee.getPassword());

        employee.setPassword(encodedPassword);

        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    public boolean employeeExists(String username) {
        return employeeRepository
                .findByUsername(username)
                .isPresent();
    }

    public Optional<Employee> findEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    public void updateTokens(String username, Integer tokens) {
        employeeRepository.updateTokens(username, tokens);
    }

    public List<Employee> getSortedEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Employee::getTokens).reversed()) // from greatest to lowest for the table
                .collect(Collectors.toList());
    }
}
