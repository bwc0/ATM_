package com.bryantcason.account;

import com.bryantcason.user.User;

import java.util.UUID;

public class Account {

    private AccountType type;
    private UUID accountNum;
    private AccountStatus status;
    private int pin;
    private double balance, interestRate;
    private boolean isProtected;
    private User user;

    public Account() {
    }

    public Account(AccountType type, int pin) {
        this.type = type;
        this.pin = pin;
    }

    public Account(AccountType type, int pin, double balance, boolean isProtected) {
        this.type = type;
        this.pin = pin;
        this.accountNum = UUID.randomUUID();
        this.status = AccountStatus.OPEN;
        this.balance = balance;
        this.isProtected = isProtected;
    }

    public Account(AccountType type, int pin, double balance, boolean isProtected, User user) {
        this.type = type;
        this.pin = pin;
        this.accountNum = UUID.randomUUID();
        this.status = AccountStatus.OPEN;
        this.balance = balance;
        this.isProtected = isProtected;
        this.user = user;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public UUID getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(UUID accountNum) {
        this.accountNum = accountNum;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public synchronized boolean debit(double amount) {
        if (isClosedOrFrozen(this)) return false;

        if (balance < amount) {
            if (isProtected) {
                return false;
            }

            balance = balance - amount -15;
            return true;
        }

        balance -= amount;
        return true;
    }

    public synchronized boolean credit(double amount) {
        if (isClosedOrFrozen(this)) return false;

        synchronized (this) {
            balance += amount;
        }

        return true;
    }

    private boolean isClosedOrFrozen(Account source) {
        if (source.status == AccountStatus.CLOSED || source.status == AccountStatus.FROZEN) {
            return true;
        }

        return false;
    }
}
