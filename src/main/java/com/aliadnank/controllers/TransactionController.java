package com.aliadnank.controllers;

import com.aliadnank.exceptions.InvalidTimestampException;
import com.aliadnank.domains.Transaction;
import com.aliadnank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
/*
* @author Ali Adnan
* Controller handles the transactions stuff, makes sure transactions are added in the store
* returns 204 when timestamp is older is 60 seconds
* */
@RequestMapping("/transactions")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> addTransaction(@Valid @ModelAttribute Transaction transaction) throws InvalidTimestampException {
        transactionService.map(transaction);
    return new ResponseEntity<String>("transaction added",HttpStatus.CREATED);
    }
}
