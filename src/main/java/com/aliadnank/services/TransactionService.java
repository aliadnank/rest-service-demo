package com.aliadnank.services;

import com.aliadnank.exceptions.InvalidTimestampException;
import com.aliadnank.domains.Transaction;

import java.util.List;

public interface TransactionService {


    public void map(Transaction transaction) throws InvalidTimestampException;

    public List<Transaction> getTransactions(long timestamp);

}
