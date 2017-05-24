package com.bstore.services.conekta.service;

/**
 *
 * @author gtrejo
 */
public class RequestChargesMSI {
    private String amount;
    private String monthly_installments;
    private String description;
    private String currency;
    private Card card;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMonthly_installments() {
        return monthly_installments;
    }

    public void setMonthly_installments(String monthly_installments) {
        this.monthly_installments = monthly_installments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "RequestChargesMSI{" + "amount=" + amount + ", monthly_installments=" + monthly_installments + ", description=" + description + ", currency=" + currency + ", card=" + card + '}';
    }
    
    public static class Card{
        private String name;
        private String number;
        private String cvc;
        private String exp_month;
        private String exp_year;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCvc() {
            return cvc;
        }

        public void setCvc(String cvc) {
            this.cvc = cvc;
        }

        public String getExp_month() {
            return exp_month;
        }

        public void setExp_month(String exp_month) {
            this.exp_month = exp_month;
        }

        public String getExp_year() {
            return exp_year;
        }

        public void setExp_year(String exp_year) {
            this.exp_year = exp_year;
        }

        @Override
        public String toString() {
            return "Card{" + "name=" + name + ", number=" + number + ", cvc=" + cvc + ", exp_month=" + exp_month + ", exp_year=" + exp_year + '}';
        }
    }
}
