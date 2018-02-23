package com.aliadnank.services.impl;

import com.aliadnank.exceptions.InvalidTimestampException;
import com.aliadnank.services.TransactionService;
import com.aliadnank.components.TransactionStore;
import com.aliadnank.domains.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionStore transactionStore;

    @Override
    public void map(Transaction transaction) throws InvalidTimestampException {

        if(isOlderTimestamp(transaction.getTimestamp()))
            throw new InvalidTimestampException("Timestamp is older or invalid");

        if (transactionStore.getStore().get(transaction.getTimestamp()) == null) {
            List<Transaction> transactionList = new ArrayList<>();
            transactionList.add(transaction);
            transactionStore.getStore().put(transaction.getTimestamp(),
                    transactionList);
        }
        else{
            List<Transaction> transactionList = transactionStore.getStore().get(transaction.getTimestamp());
            transactionList.add(transaction);
            transactionStore.getStore().put(transaction.getTimestamp(),
                    transactionList);
        }
    }

    @Override
    public List<Transaction> getTransactions(long timestamp) {
        return transactionStore.getStore().get(timestamp);
    }

    private Boolean isOlderTimestamp(long timestamp){
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.of("UTC")).toInstant().isBefore(Instant.now().minusSeconds(60).atZone(ZoneId.of("UTC")).toInstant());
    }

}
