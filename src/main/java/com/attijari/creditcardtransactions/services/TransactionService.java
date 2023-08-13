package com.attijari.creditcardtransactions.services;

import com.attijari.creditcardtransactions.models.Transaction;
import com.attijari.creditcardtransactions.models.TransactionFilter;

import java.io.IOException;
import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions() throws IOException;

    List<Transaction> getFilteredTransactions(TransactionFilter filter) throws IOException;

    List<Transaction> getSortedTransactions(String sortDirection, int page, int size) throws IOException;

    List<Transaction> getSortedPropertyTransactions(String sortProperty, String sortDirection) throws IOException;

}