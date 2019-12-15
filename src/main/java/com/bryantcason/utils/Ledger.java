package com.bryantcason.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ledger {

    private List<Transaction> ledger = new ArrayList<>();

    public List<Transaction> getLedger() {
        return ledger;
    }

    public void addTransaction(Transaction transaction) {
        ledger.add(transaction);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transaction;
    }

    public String logTransaction() {
        StringBuilder sb = new StringBuilder();

        for (Transaction transaction : ledger) {
            sb.append("\nTransaction Type: " + transaction.getTransType())
                    .append("\n" + "Amount: " + transaction.getAmount())
                    .append("\n" + "UFTN: " + transaction.getUniqueFinancialTransNum())
                    .append("\n" + "FTN: " + transaction.getFinancialTransNum())
                    .append("\n" + "Destination Account Number: " + transaction.getDestinationAccNum())
                    .append("\n" + "Source Account Number: " + transaction.getSourceAccNum())
                    .append("\n" + "Date: " + transaction.getTransDate() + "\n");
        }

        return sb.toString();
    }
}
