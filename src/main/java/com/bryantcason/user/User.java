package com.bryantcason.user;

import com.bryantcason.account.*;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private List<Account> accounts;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public User addAccount(Account account) {
        account.setUser(this);
        accounts.add(account);
        return this;
    }

    public User openAccount(int choice, double balance, int pin, boolean isProtected) {
        Account account;

        switch (choice) {

            case 2: account = new Savings(pin, balance, isProtected);
                    break;
            case 3: account = new Investment(pin);
                    break;
            default: account = new Checking(pin, balance, isProtected);
                    break;
        }

        addAccount(account);

        return this;
    }

    public boolean closeAccount(Account account) {
        if (account.getBalance() != 0) {
            return false;
        }

        account.setStatus(AccountStatus.CLOSED);
        return true;
    }
}
