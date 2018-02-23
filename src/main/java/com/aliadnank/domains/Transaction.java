package com.aliadnank.domains;

import javax.validation.constraints.NotNull;
/*
* @author Ali Adnan
* Transaction Domain object
* */
public class Transaction {
    private long timestamp;
    private double amount;

    public Transaction() {
    }

    public Transaction(long timestamp, double amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }

    @NotNull
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @NotNull
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
