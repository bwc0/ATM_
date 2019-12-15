package com.bryantcason.bank;

import com.bryantcason.account.Account;
import com.bryantcason.account.Checking;
import com.bryantcason.account.Savings;
import com.bryantcason.user.User;
import com.bryantcason.utils.Ledger;
import com.bryantcason.utils.Prompt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BankTest {

    private Account sourceAcc;
    private Account destinationAcc;
    private Bank bank;
    private User user;
    private Prompt prompt;

    @BeforeEach
    void setUp() {

        prompt = mock(Prompt.class);

        bank = new Bank(prompt);

        user = new User();
        user.setFirstName("User");
        user.setLastName("Test");
        user.setUsername("username");
        user.setPassword("password");

        sourceAcc = new Checking(3213, 1000, true, user);
        destinationAcc = new Savings(1234, 100, true, user);

        bank.getUsers().add(user);
    }

    @Test
    @DisplayName("Should withdrawal amount from account")
    void withdrawalSuccessfulTest() {
        when(prompt.askForDouble(anyString())).thenReturn(500.0);
        bank.withdrawal(sourceAcc);

        double balance = sourceAcc.getBalance();
        assertEquals(500, balance);
        assertEquals(1, bank.getLedger().getLedger().size());

    }

    @Test
    @DisplayName("Should not withdrawal amount from protected account")
    void withdrawalFailureProtectedAccountTest() {
        when(prompt.askForDouble(anyString())).thenReturn(1500.0);
        bank.withdrawal(sourceAcc);

        double balance = sourceAcc.getBalance();
        assertEquals(1000, balance);
    }

    @Test
    @DisplayName("Should withdrawal amount from unprotected account")
    void withdrawalSuccessfulUnProtectedAccountTest() {
        sourceAcc.setProtected(false);

        when(prompt.askForDouble(anyString())).thenReturn(1500.0);
        bank.withdrawal(sourceAcc);

        double balance = sourceAcc.getBalance();
        assertEquals(-515, balance);
    }
}