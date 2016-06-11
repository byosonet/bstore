
package com.bstore.services.conekta.service;

/**
 *
 * @author gtrejo
 */
public class RequestCharges {
    private String currency;
    private String amount;
    private String description;
    private String reference_id;
    private Cash cash;
    private Details details;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "RequestCharges{" + "currency=" + currency + ", amount=" + amount + ", description=" + description + ", reference_id=" + reference_id + ", cash=" + cash + ", details=" + details + '}';
    }

    public static class Cash{
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Cash{" + "type=" + type + '}';
        }
    }
    
    public static class Details{
        private String name;
        private String email;
        private String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Details{" + "name=" + name + ", email=" + email + ", phone=" + phone + '}';
        }
        
        
        
    }

}
