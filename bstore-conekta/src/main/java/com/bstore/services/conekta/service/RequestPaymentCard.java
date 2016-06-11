package com.bstore.services.conekta.service;

/**
 *
 * @author david
 */
public class RequestPaymentCard {
    private String currency;
    private long amount;
    private String description;
    private String referenceId;
    private String card;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "RequestPaymentCard{" + "currency=" + currency + ", amount=" + amount + ", description=" + description + ", referenceId=" + referenceId + ", card=" + card + '}';
    }
}
