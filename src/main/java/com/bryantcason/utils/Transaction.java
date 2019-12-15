package com.bryantcason.utils;

import java.util.Date;
import java.util.UUID;

public class Transaction {

    private UUID financialTransNum;
    private UUID uniqueFinancialTransNum;
    private double amount;
    private TransactionType transType;
    private UUID sourceAccNum;
    private UUID destinationAccNum;
    private String transDate;

    public Transaction() {
    }

    public Transaction(double amount, TransactionType transType, UUID sourceAccNum, UUID destinationAccNum) {
        this.financialTransNum = UUID.randomUUID();
        this.uniqueFinancialTransNum = UUID.randomUUID();
        this.amount = amount;
        this.transType = transType;
        this.sourceAccNum = sourceAccNum;
        this.destinationAccNum = destinationAccNum;
        this.transDate = new Date().toString();
    }

    public Transaction(double amount, TransactionType transType, UUID sourceAccNum) {
        this.financialTransNum = UUID.randomUUID();
        this.uniqueFinancialTransNum = UUID.randomUUID();
        this.amount = amount;
        this.transType = transType;
        this.sourceAccNum = sourceAccNum;
        this.transDate = new Date().toString();
    }

    public UUID getFinancialTransNum() {
        return financialTransNum;
    }

    public void setFinancialTransNum(UUID financialTransNum) {
        this.financialTransNum = financialTransNum;
    }

    public UUID getUniqueFinancialTransNum() {
        return uniqueFinancialTransNum;
    }

    public void setUniqueFinancialTransNum(UUID uniqueFinancialTransNum) {
        this.uniqueFinancialTransNum = uniqueFinancialTransNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransType() {
        return transType;
    }

    public void setTransType(TransactionType transType) {
        this.transType = transType;
    }

    public UUID getSourceAccNum() {
        return sourceAccNum;
    }

    public void setSourceAccNum(UUID sourceAccNum) {
        this.sourceAccNum = sourceAccNum;
    }

    public UUID getDestinationAccNum() {
        return destinationAccNum;
    }

    public void setDestinationAccNum(UUID destinationAccNum) {
        this.destinationAccNum = destinationAccNum;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
}
