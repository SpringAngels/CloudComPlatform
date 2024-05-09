package com.cloudcom.page.share;

import com.cloudcom.employee.Employee;
import com.cloudcom.employee.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class SharingService {

    private static final String EMPLOYEE_DOES_NOT_EXIST
            = "employee with username %s doesn't exist";
    private static final String NOT_ENOUGH_TOKENS_TO_SEND
            = "employee with username %s doesn't have enough tokens to send";

    private final EmployeeService employeeService;

    public SharingService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void sendTokens(String senderName, Integer tokensToSend, String receiverName) {
        boolean receiverExists = employeeService.employeeExists(receiverName);

        if (!receiverExists) {
            throw new IllegalStateException(
                    String.format(EMPLOYEE_DOES_NOT_EXIST, receiverName)
            );
        }

        Employee sender = employeeService
                .findEmployeeByUsername(senderName)
                .orElseThrow(); // always exists

        int senderTokens = sender.getTokens();

        if (senderTokens < tokensToSend) {
            throw new IllegalStateException(
                    String.format(NOT_ENOUGH_TOKENS_TO_SEND, senderName)
            );
        }

        employeeService.updateTokens(senderName,senderTokens - tokensToSend);
        employeeService.updateTokens(receiverName, tokensToSend);
    }
}