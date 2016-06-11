package com.bstore.services.conekta.service;

/**
 *
 * @author gtrejo
 */
public class ResponseCharges {
    private String id;
    private String livemode;
    private String created_at;
    private String status;
    private String currency;
    private String description;
    private String reference_id;
    private String failure_code;
    private String failure_message;
    private String object;
    private long amount;
    private Payment_method payment_method;
    private Details details;
    private Errors errors;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLivemode() {
        return livemode;
    }

    public void setLivemode(String livemode) {
        this.livemode = livemode;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getFailure_code() {
        return failure_code;
    }

    public void setFailure_code(String failure_code) {
        this.failure_code = failure_code;
    }

    public String getFailure_message() {
        return failure_message;
    }

    public void setFailure_message(String failure_message) {
        this.failure_message = failure_message;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Payment_method getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(Payment_method payment_method) {
        this.payment_method = payment_method;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ResponseCharges{" + "id=" + id + ", livemode=" + livemode + ", created_at=" + created_at + ", status=" + status + ", currency=" + currency + ", description=" + description + ", reference_id=" + reference_id + ", failure_code=" + failure_code + ", failure_message=" + failure_message + ", object=" + object + ", amount=" + amount + ", payment_method=" + payment_method + ", details=" + details + ", errors=" + errors + '}';
    }

    
    public static class Payment_method{
        private String expiry_date;
        private String barcode;
        private String barcode_url;
        private String object;
        private String type;

        public String getExpiry_date() {
            return expiry_date;
        }

        public void setExpiry_date(String expiry_date) {
            this.expiry_date = expiry_date;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getBarcode_url() {
            return barcode_url;
        }

        public void setBarcode_url(String barcode_url) {
            this.barcode_url = barcode_url;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Payment_method{" + "expiry_date=" + expiry_date + ", barcode=" + barcode + ", barcode_url=" + barcode_url + ", object=" + object + ", type=" + type + '}';
        }

    }
    
    public static class Details{
        
        private String name;
        private String phone;
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Details{" + "name=" + name + ", phone=" + phone + ", email=" + email + '}';
        }
        
    }
    
    public static class Errors {
        private String status;
        private String message;
        private String error1;
        private String error2;
        private String conecction;
        private boolean ifError;

        public boolean isIfError() {
            return ifError;
        }

        public void setIfError(boolean ifError) {
            this.ifError = ifError;
        }
        
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getError1() {
            return error1;
        }

        public void setError1(String error1) {
            this.error1 = error1;
        }

        public String getError2() {
            return error2;
        }

        public void setError2(String error2) {
            this.error2 = error2;
        }

        public String getConecction() {
            return conecction;
        }

        public void setConecction(String conecction) {
            this.conecction = conecction;
        }

        @Override
        public String toString() {
            return "Errors{" + "status=" + status + ", message=" + message + ", error1=" + error1 + ", error2=" + error2 + ", conecction=" + conecction + ", ifError=" + ifError + '}';
        }
 
    }
    
}
