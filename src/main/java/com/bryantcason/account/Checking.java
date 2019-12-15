package com.bryantcason.account;

import com.bryantcason.user.User;

public class Checking extends Account {

    public Checking(int pin, double balance, boolean isProtected) {
        super(AccountType.CHECKING, pin, balance, isProtected);
    }

    public Checking(int pin, double balance, boolean isProtected, User user) {
        super(AccountType.CHECKING, pin, balance, isProtected, user);
    }
}
