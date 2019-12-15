package com.bryantcason.account;

import com.bryantcason.user.User;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;
    private Account account2;
    private User user;

    @BeforeEach
    void setUp() {

        user = new User();
        account = new Checking(1234, 1000, true, user);

        account2 = new Account();
        account2.setType(AccountType.SAVINGS);
        account2.setAccountNum(UUID.randomUUID());
        account2.setStatus(AccountStatus.OPEN);
        account2.setPin(1234);
        account2.setBalance(1000);
        account2.setInterestRate(0.25);
        account2.setProtected(true);
        account2.setUser(user);

    }

    @Test
    @DisplayName("Debit from Account should return true")
    void debitFromAccountTest() {
        boolean actualValue = account.debit(1000);
        assertEquals(account.getBalance(), 0);
        assertTrue(actualValue);
    }

    @Test
    @DisplayName("Overdraft from unprotected account should return true")
    void debitFromUnprotectedAccountTest() {
        account.setProtected(false);
        boolean actualValue = account.debit(1500);
        assertEquals(account.getBalance(), -515);
        assertTrue(actualValue);
    }

    @Test
    @DisplayName("Overdraft from protected account should return false")
    void debitFromProtectedAccountTest() {
        boolean actualValue = account.debit(1500);
        assertEquals(account.getBalance(), 1000);
        assertFalse(actualValue);
    }

    @Test
    @DisplayName("Debit from closed account should return false")
    void debitFromClosedAccountTest() {
        account.setBalance(0);
        account.setStatus(AccountStatus.CLOSED);
        boolean actualValue = account.debit(1500);
        assertFalse(actualValue);
    }

    @Test
    @DisplayName("Debit from frozen account should return false")
    void debitFromFrozenAccountTest() {
        account.setBalance(0);
        account.setStatus(AccountStatus.FROZEN);
        boolean actualValue = account.debit(1500);
        assertFalse(actualValue);
    }

    @Test
    @DisplayName("Credit to Account should return true")
    void creditToAccountTest() {
        boolean actualValue = account2.credit(1000);
        assertEquals(account2.getBalance(), 2000);
        assertTrue(actualValue);
    }

    @Test
    @DisplayName("Credit to closed Account should return false")
    void creditToClosedAccountTest() {
        account2.setBalance(0);
        account2.setStatus(AccountStatus.CLOSED);
        boolean actualValue = account2.credit(1000);
        assertFalse(actualValue);
    }

    @Test
    @DisplayName("Credit to frozen Account should return false")
    void creditToFrozenAccountTest() {
        account2.setBalance(0);
        account2.setStatus(AccountStatus.FROZEN);
        boolean actualValue = account2.credit(1000);
        assertFalse(actualValue);
    }
}