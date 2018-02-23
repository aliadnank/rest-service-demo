package com.aliadnank.components;


import com.aliadnank.domains.Transaction;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
/*
* @author Ali Adnan
* Component provides access to Transaction store
* For performance specific reasons, sorted map implementation is used
* in addition with concurrent impl
* */
@Component("transactionStore")
public class TransactionStore {

    private ConcurrentNavigableMap<Long, List<Transaction>> transactionsMap;

    @PostConstruct
    public void init(){
    transactionsMap = new ConcurrentSkipListMap<Long, List<Transaction>>();
    }

    public ConcurrentNavigableMap<Long, List<Transaction>> getStore() {
        return transactionsMap;
    }
}
