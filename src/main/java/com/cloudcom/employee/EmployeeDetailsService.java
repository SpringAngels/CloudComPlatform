package com.cloudcom.employee;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return employeeRepository.findByUsername(username)
                .map(EmployeeUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployees() {
        employeeRepository.deleteAll();
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
