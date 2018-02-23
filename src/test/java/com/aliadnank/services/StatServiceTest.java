package com.aliadnank.services;

import com.aliadnank.exceptions.InvalidTimestampException;
import com.aliadnank.components.TransactionStore;
import com.aliadnank.domains.Transaction;
import com.aliadnank.domains.TransactionStats;
import com.aliadnank.services.impl.TransactionServiceImpl;
import com.aliadnank.services.impl.TransactionStatsServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = {TransactionServiceImpl.class, TransactionStore.class, TransactionStatsServiceImpl.class})
public class StatServiceTest {

    @Autowired
    private TransactionStatsService statsService;

    @Autowired
    TransactionService transactionService;

    @Before
    public void init(){
    }

    @Test
    public void testStats() throws InvalidTimestampException {
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

        transactionService.map(transaction1);
        transactionService.map(transaction2);
        transactionService.map(transaction3);

        TransactionStats expected = new TransactionStats();
        expected.setCount(3);
        expected.setMax(300);
        expected.setMin(100);
        expected.setAvg(200);
        expected.setSum(600);

       TransactionStats actual =  statsService.reduce();

        Assert.assertEquals(expected.getSum(),actual.getSum(),0);

    }

}
