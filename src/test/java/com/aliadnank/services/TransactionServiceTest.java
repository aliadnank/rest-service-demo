package com.aliadnank.services;

import com.aliadnank.exceptions.InvalidTimestampException;
import com.aliadnank.components.TransactionStore;
import com.aliadnank.domains.Transaction;
import com.aliadnank.services.impl.TransactionServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TransactionServiceImpl.class, TransactionStore.class})
public class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;


    @Before
    public void init(){
    }

    @Test
    public void addTransactionTest() throws InvalidTimestampException {
        long _timestamp = Instant.now().toEpochMilli();

        Transaction transaction1 = new Transaction();
        transaction1.setAmount(100);
        transaction1.setTimestamp(_timestamp);

        Transaction transaction2 = new Transaction();
        transaction2.setAmount(200);
        transaction2.setTimestamp(_timestamp);

        Transaction transaction3 = new Transaction();
        transaction3.setAmount(300);
        transaction3.setTimestamp(_timestamp);

        List<Transaction> list = new ArrayList<>();
        list.add(transaction1);
        list.add(transaction2);
        list.add(transaction3);

        transactionService.map(transaction1);
        transactionService.map(transaction2);
        transactionService.map(transaction3);

        List<Transaction> list2 = transactionService.getTransactions(_timestamp);

        Assert.assertEquals(list.size(), list2.size());


    }

}
