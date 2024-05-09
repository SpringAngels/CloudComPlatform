package com.cloudcom.employee;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE Employee " +
            "SET tokens = ?2 " +
            "WHERE username = ?1")
    void updateTokens(String username, Integer tokens);
}
