package com.bryantcason.user;

import com.bryantcason.account.Account;
import com.bryantcason.account.AccountStatus;
import com.bryantcason.account.AccountType;
import com.bryantcason.account.Checking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Account account;

    @BeforeEach
    void setUp() {
        user = new User("username", "password", "first", "last");
        account = new Checking(1234, 1000, true);
    }

    @Test
    @DisplayName("Add account to user list of accounts return user")
    void addAccountTest() {
        User actualValue = user.addAccount(account);
        assertEquals(1, actualValue.getAccounts().size());
        assertEquals(user.getUsername(), actualValue.getAccounts().get(0).getUser().getUsername());
        assertEquals(user.getPassword(), actualValue.getAccounts().get(0).getUser().getPassword());
        assertEquals(user.getFirstName(), actualValue.getAccounts().get(0).getUser().getFirstName());
        assertEquals(user.getLastName(), actualValue.getAccounts().get(0).getUser().getLastName());
        assertEquals(1, actualValue.getAccounts().size());
    }

    @Test
    @DisplayName("Open checking account return user")
    void openCheckingAccountTest() {
        User actualValue = user.openAccount(1,5000, 1111, true);
        assertNotNull(actualValue);
        assertEquals(1, actualValue.getAccounts().size());
        assertEquals(5000, actualValue.getAccounts().get(0).getBalance());
        assertEquals(1111, actualValue.getAccounts().get(0).getPin());
        assertEquals(AccountType.CHECKING, actualValue.getAccounts().get(0).getType());
        assertEquals(AccountStatus.OPEN, actualValue.getAccounts().get(0).getStatus());
        assertTrue(actualValue.getAccounts().get(0).isProtected());
    }

    @Test
    @DisplayName("Open savings account return user")
    void openSavingsAccountTest() {
        User actualValue = user.openAccount(2,5000, 1111, true);
        assertNotNull(actualValue);
        assertEquals(1, actualValue.getAccounts().size());
        assertEquals(5000, actualValue.getAccounts().get(0).getBalance());
        assertEquals(1111, actualValue.getAccounts().get(0).getPin());
        assertEquals(AccountType.SAVINGS, actualValue.getAccounts().get(0).getType());
        assertEquals(AccountStatus.OPEN, actualValue.getAccounts().get(0).getStatus());
        assertTrue(actualValue.getAccounts().get(0).isProtected());
    }

    @Test
    @DisplayName("Open investment account return user")
    void openInvestmentAccountTest() {
        User actualValue = user.openAccount(3,5000, 1111, true);
        assertNotNull(actualValue);
        assertEquals(1, actualValue.getAccounts().size());
        assertEquals(1111, actualValue.getAccounts().get(0).getPin());
        assertEquals(AccountType.INVESTMENT, actualValue.getAccounts().get(0).getType());
    }

    @Test
    @DisplayName("Close account if balance is zero return true")
    void closeSuccessfulAccount() {
        user.addAccount(account);
        account.setBalance(0);
        boolean actualValue = user.closeAccount(account);
        assertTrue(actualValue);
        assertEquals(AccountStatus.CLOSED, user.getAccounts().get(0).getStatus());
    }

    @Test
    @DisplayName("Close account if balance is not zero return false")
    void closeFailedAccount() {
        user.addAccount(account);
        boolean actualValue = user.closeAccount(account);
        assertFalse(actualValue);
        assertEquals(AccountStatus.OPEN, user.getAccounts().get(0).getStatus());
    }
}