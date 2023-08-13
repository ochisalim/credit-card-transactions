package com.attijari.creditcardtransactions;

import com.attijari.creditcardtransactions.controllers.TransactionController;
import com.attijari.creditcardtransactions.models.Transaction;
import com.attijari.creditcardtransactions.models.TransactionFilter;
import com.attijari.creditcardtransactions.services.TransactionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import org.mockito.ArgumentMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
public class TransactionsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;


    @Test
    public void testGetFilteredTransactions() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Transaction> mockTransactions = objectMapper.readValue(
                new ClassPathResource("transactionsMock.json").getInputStream(),
                new TypeReference<List<Transaction>>() {});

        when(transactionService.getFilteredTransactions(
                ArgumentMatchers.any(TransactionFilter.class)))
                .thenReturn(mockTransactions);

        mockMvc.perform(get("/v1/api/transactions/filter")
                        .param("amount", "100.0")
                        .param("merchant", "Amazon")
                        .param("status", "approved"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(mockTransactions.size())));
        System.out.println("Test 'testGetFilteredTransactions' passed successfully!");
    }

    @Test
    public void testGetSortedPropertyTransactions() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Transaction> mockTransactions = objectMapper.readValue(
                new ClassPathResource("transactionsMock.json").getInputStream(),
                new TypeReference<List<Transaction>>() {});

        when(transactionService.getSortedPropertyTransactions(
                ArgumentMatchers.eq("amount"), ArgumentMatchers.eq("asc")))
                .thenReturn(mockTransactions);

        mockMvc.perform(get("/v1/api/transactions/sort-property")
                        .param("sortProperty", "amount")
                        .param("sortDirection", "asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(mockTransactions.size())));
        System.out.println("Test 'testGetSortedPropertyTransactions' passed successfully!");
    }
}