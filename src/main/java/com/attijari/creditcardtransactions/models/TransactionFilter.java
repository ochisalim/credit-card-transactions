package com.attijari.creditcardtransactions.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionFilter {
    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("merchant")
    private String merchant;

    @JsonProperty("status")
    private String status;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}