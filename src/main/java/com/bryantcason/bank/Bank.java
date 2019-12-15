package com.bryantcason.bank;

import com.bryantcason.account.Account;
import com.bryantcason.user.User;
import com.bryantcason.utils.Ledger;
import com.bryantcason.utils.Prompt;
import com.bryantcason.utils.Transaction;
import com.bryantcason.utils.TransactionType;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static com.bryantcason.utils.Prompt.giveMessage;

public class Bank {

    private List<User> users;
    private Ledger ledger;
    private Prompt prompt;

    public Bank(Prompt prompt) {
        users = new ArrayList<>();
        this.ledger = new Ledger();
        this.prompt = prompt;
    }

    public List<User> getUsers() {
        return users;
    }

    public Ledger getLedger() {
        return ledger;
    }

    public void withdrawal(Account account) {
        try {
            double amount = prompt.askForDouble("Enter amount: ");

            if (account.debit(amount)) {
                System.out.println("Approved");

                Transaction withdrawalTrans = new Transaction(amount,
                        TransactionType.DEBIT, account.getAccountNum());

                ledger.addTransaction(withdrawalTrans);

                receipt(withdrawalTrans, account);

            } else {
                System.out.println("Denied");
            }

        } catch (InputMismatchException ime) {
            giveMessage("Must be a number");
        }
    }

    private void receipt(Transaction withdrawalTransaction, Account source) {
        giveMessage("\n  Transaction:\n   Amount:" + withdrawalTransaction.getAmount() + "\n   Type: "
                + withdrawalTransaction.getTransType() + "\n   Transaction Number: " +
                withdrawalTransaction.getFinancialTransNum() + "\n   Source a/n: " +
                withdrawalTransaction.getSourceAccNum() + "\n   Balance: " + source.getBalance());
    }
}
