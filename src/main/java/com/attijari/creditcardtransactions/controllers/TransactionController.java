package com.attijari.creditcardtransactions.controllers;

import com.attijari.creditcardtransactions.models.Transaction;
import com.attijari.creditcardtransactions.models.TransactionFilter;
import com.attijari.creditcardtransactions.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() throws IOException {
        return transactionService.getAllTransactions();
    }


    @GetMapping("/filter")
    public List<Transaction> getFilteredTransactions(
            @RequestParam(value = "amount", required = false) Double amount,
            @RequestParam(value = "merchant", required = false) String merchant,
            @RequestParam(value = "status", required = false) String status) throws IOException {
        TransactionFilter filter = new TransactionFilter();
        filter.setAmount(amount);
        filter.setMerchant(merchant);
        filter.setStatus(status);
        return transactionService.getFilteredTransactions(filter);
    }

    @GetMapping("/sort-page")
    public List<Transaction> getSortedTransactions(
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) throws IOException {
        return transactionService.getSortedTransactions(sortDirection, page, size);
    }

    @GetMapping("/sort-property")
    public List<Transaction> getSortedPropertyTransactions(
            @RequestParam(value = "sortProperty", defaultValue = "amount") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection) throws IOException {
        return transactionService.getSortedPropertyTransactions(sortProperty, sortDirection);
    }

}