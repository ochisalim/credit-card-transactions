package com.attijari.creditcardtransactions.services;

import com.attijari.creditcardtransactions.exception.InvalidFilterException;
import com.attijari.creditcardtransactions.exception.InvalidSortDirectionException;
import com.attijari.creditcardtransactions.exception.InvalidSortPropertyException;
import com.attijari.creditcardtransactions.models.Transaction;
import com.attijari.creditcardtransactions.models.TransactionFilter;
import com.attijari.creditcardtransactions.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public List<Transaction> getAllTransactions() throws IOException {
        return transactionRepository.getTransactions();
    }

    @Override
    public List<Transaction> getFilteredTransactions(TransactionFilter filter) throws IOException {
        validateFilter(filter);
        return transactionRepository.getFilteredTransactions(filter);
    }

    @Override
    public List<Transaction> getSortedTransactions(String sortDirection, int page, int size) throws IOException {
        validateSortDirection(sortDirection);
        return transactionRepository.getSortedTransactions(sortDirection, page, size);
    }

    @Override
    public List<Transaction> getSortedPropertyTransactions(String sortProperty, String sortDirection) throws IOException {
        validateSortPropertyAndDirection(sortProperty, sortDirection);
        return transactionRepository.getSortedPropertyTransactions(sortProperty, sortDirection);
    }

    private void validateFilter(TransactionFilter filter) {
        if (filter.getAmount() != null && filter.getAmount() < 0) {
            throw new InvalidFilterException("Invalid filter value: amount cannot be negative.");
        }
        if (filter.getMerchant() != null && isNumeric(filter.getMerchant())) {
            throw new InvalidFilterException("Invalid filter value: merchant cannot be a number.");
        }
        if (filter.getStatus() != null && isNumeric(filter.getStatus())) {
            throw new InvalidFilterException("Invalid filter value: status cannot be a number.");
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private void validateSortDirection(String sortDirection) {
        if (!"asc".equalsIgnoreCase(sortDirection) && !"desc".equalsIgnoreCase(sortDirection)) {
            throw new InvalidSortDirectionException("Invalid sort direction. Use 'asc' or 'desc'.");
        }
    }

    private void validateSortPropertyAndDirection(String sortProperty, String sortDirection) {
        if (!"amount".equalsIgnoreCase(sortProperty) &&
                !"merchant".equalsIgnoreCase(sortProperty) &&
                !"status".equalsIgnoreCase(sortProperty)) {
            throw new InvalidSortPropertyException("Invalid sort property. Use 'amount', 'merchant', or 'status'.");
        }
        validateSortDirection(sortDirection);
    }
}
