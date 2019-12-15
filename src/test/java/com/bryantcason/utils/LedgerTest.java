package com.bryantcason.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LedgerTest {

    private Ledger ledger;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        ledger = new Ledger();
    }

    @Test
    @DisplayName("Transaction receipt for Source and Destination")
    void createTransactionWithSourceAndDestinationAccountTest() {
        transaction = new Transaction();
        transaction.setAmount(100);
        transaction.setTransType(TransactionType.TRANSFER);
        transaction.setDestinationAccNum(UUID.randomUUID());
        transaction.setSourceAccNum(UUID.randomUUID());
        transaction.setFinancialTransNum(UUID.randomUUID());
        transaction.setUniqueFinancialTransNum(UUID.randomUUID());
        transaction.setTransDate(new Date().toString());

        Transaction actualValue = ledger.createTransaction(transaction);

        assertEquals(100, actualValue.getAmount());
        assertEquals(TransactionType.TRANSFER, actualValue.getTransType());
        assertNotNull(actualValue.getSourceAccNum());
        assertNotNull(actualValue.getDestinationAccNum());
        assertNotNull(actualValue.getFinancialTransNum());
        assertNotNull(actualValue.getUniqueFinancialTransNum());
    }

    @Test
    @DisplayName("Transaction receipt for Source")
    void testCreateTransactionWithSourceAccountTest() {
        transaction = new Transaction(100, TransactionType.DEBIT, UUID.randomUUID());

        Transaction actualValue = ledger.createTransaction(transaction);

        assertEquals(100, actualValue.getAmount());
        assertEquals(TransactionType.DEBIT, actualValue.getTransType());
        assertNotNull(actualValue.getSourceAccNum());
        assertNotNull(actualValue.getFinancialTransNum());
        assertNotNull(actualValue.getUniqueFinancialTransNum());
    }

    @Test
    void logTransactionTest() {
        transaction = new Transaction(100, TransactionType.TRANSFER,
                UUID.randomUUID(), UUID.randomUUID());

        ledger.addTransaction(transaction);

        String expectedValue = "\nTransaction Type: " + transaction.getTransType() + "\n" + "Amount: " + transaction.getAmount() + "\n"
                + "UFTN: " + transaction.getUniqueFinancialTransNum() + "\n" + "FTN: " + transaction.getFinancialTransNum() + "\n" +
                "Destination Account Number: " + transaction.getDestinationAccNum() + "\n" + "Source Account Number: " +
                transaction.getSourceAccNum() + "\n" + "Date: " + transaction.getTransDate() + "\n";

        String actualValue = ledger.logTransaction();

        assertEquals(expectedValue, actualValue);
    }
}