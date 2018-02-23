package com.aliadnank.controllers;

import com.aliadnank.services.TransactionStatsService;
import com.aliadnank.domains.TransactionStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
* @author Ali Adnan
* Provides stats for transaction in O(1) time for last 60 seconds
* */
@RestController
@RequestMapping("/stats")
public class TransactionsStatsController {

    @Autowired
    private TransactionStatsService statsService;

    @GetMapping(name = "stats", produces = "application/json")
    public TransactionStats retrieveStats(){
    return statsService.reduce();
    }

}
