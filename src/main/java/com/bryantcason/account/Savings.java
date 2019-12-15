package com.bryantcason.account;

import com.bryantcason.user.User;

public class Savings extends Account {

    public Savings(int pin, double balance, boolean isProtected) {
        super(AccountType.SAVINGS, pin, balance, isProtected);
    }

    public Savings(int pin, double balance, boolean isProtected, User user) {
        super(AccountType.SAVINGS, pin, balance, isProtected, user);
    }
}
