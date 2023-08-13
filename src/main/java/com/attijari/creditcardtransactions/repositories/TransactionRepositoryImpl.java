package com.attijari.creditcardtransactions.repositories;

import com.attijari.creditcardtransactions.models.Transaction;
import com.attijari.creditcardtransactions.models.TransactionFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Transaction> transactions;


    @Override
    public List<Transaction> getTransactions() throws IOException {
        if (transactions == null) {
            ClassPathResource resource = new ClassPathResource("transactionsMock.json");
            Transaction[] transactionArray = objectMapper.readValue(resource.getInputStream(), Transaction[].class);
            transactions = Arrays.asList(transactionArray);
        }
        return transactions;
    }

    @Override
    public List<Transaction> getFilteredTransactions(TransactionFilter filter) throws IOException {
        if (transactions == null) {
            transactions = getTransactions();
        }

        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if ((filter.getAmount() == null || transaction.getAmount().equals(filter.getAmount())) &&
                    (filter.getMerchant() == null || transaction.getMerchant().equalsIgnoreCase(filter.getMerchant())) &&
                    (filter.getStatus() == null || transaction.getStatus().equalsIgnoreCase(filter.getStatus()))) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }


    @Override
    public List<Transaction> getSortedTransactions(String sortDirection, int page, int size) throws IOException {
        if (transactions == null) {
            transactions = getTransactions();
        }

        List<Transaction> sortedTransactions = new ArrayList<>(transactions);

        if ("asc".equalsIgnoreCase(sortDirection)) {
            sortedTransactions.sort(Comparator.comparing(Transaction::getAmount));
        } else {
            sortedTransactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
        }
        return getPaginatedList(sortedTransactions, page, size);
    }


    @Override
    public List<Transaction> getSortedPropertyTransactions(String sortProperty, String sortDirection) throws IOException {
        if (transactions == null) {
            transactions = getTransactions();
        }

        List<Transaction> sortedTransactions = new ArrayList<>(transactions);

        Comparator<Transaction> comparator;

        switch (sortProperty.toLowerCase()) {
            case "merchant":
                comparator = Comparator.comparing(Transaction::getMerchant);
                break;
            case "status":
                comparator = Comparator.comparing(Transaction::getStatus);
                break;
            default:
                comparator = Comparator.comparing(Transaction::getAmount);
        }

        if ("desc".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }

        sortedTransactions.sort(comparator);
        return sortedTransactions;
    }


    private List<Transaction> getPaginatedList(List<Transaction> transactions, int page, int size) {
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, transactions.size());
        return transactions.subList(fromIndex, toIndex);
    }

}