package com.bstore.services.conekta.service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class ResponseChargesMSI {
    private String id;
    private String status;
    private Long paidAt;
    private Long fee;
    private String customerId;
    private String referenceId;
    private String object;
//    private String refunds;
    private String failureCode;
    private String failureMessage;
    private Long amount;
    private String currency;
    private String description;
    private Long monthlyInstallments;
    private Long createdAt;
    private boolean livemode;
    private PaymentMethod paymentMethod;
    private Detail detail;
    private Errors errors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Long paidAt) {
        this.paidAt = paidAt;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String objectCharge) {
        this.object = objectCharge;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public Long getMonthlyInstallments() {
        return monthlyInstallments;
    }

    public void setMonthlyInstallments(Long monthlyInstallments) {
        this.monthlyInstallments = monthlyInstallments;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isLivemode() {
        return livemode;
    }

    public void setLivemode(boolean livemode) {
        this.livemode = livemode;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ResponseChargesMSI{" + "id=" + id + ", status=" + status + ", paidAt=" + paidAt + ", fee=" + fee + ", customerId=" + customerId + ", referenceId=" + referenceId + ", objectCharge=" + object + ", failureCode=" + failureCode + ", failureMessage=" + failureMessage + ", amount=" + amount + ", currency=" + currency + ", description=" + description + ", monthlyInstallments=" + monthlyInstallments + ", createdAt=" + createdAt + ", livemode=" + livemode + ", paymentMethod=" + paymentMethod + ", detail=" + detail + ", errors=" + errors + '}';
    }
    
    public static class PaymentMethod{
        private String object;
        private String expMonth;
        private String expYear;
        private String last4;
        private String brand;
        private String name;
        private String authCode;

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public String getExpMonth() {
            return expMonth;
        }

        public void setExpMonth(String expMonth) {
            this.expMonth = expMonth;
        }

        public String getExpYear() {
            return expYear;
        }

        public void setExpYear(String expYear) {
            this.expYear = expYear;
        }

        public String getLast4() {
            return last4;
        }

        public void setLast4(String last4) {
            this.last4 = last4;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthCode() {
            return authCode;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }

        @Override
        public String toString() {
            return "PaymentMethod{" + "object=" + object + ", expMonth=" + expMonth + ", expYear=" + expYear + ", last4=" + last4 + ", brand=" + brand + ", name=" + name + ", authCode=" + authCode + '}';
        }
    }
    
    public static class Detail{
        private String phone;
        private String email;
        private String name;
        private List<Item> lineItems = new ArrayList<Item>();

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Item> getLineItems() {
            return lineItems;
        }

        public void setLineItems(List<Item> lineItems) {
            this.lineItems = lineItems;
        }

        @Override
        public String toString() {
            return "Detail{" + "phone=" + phone + ", email=" + email + ", name=" + name + ", lineItems=" + lineItems + '}';
        }
        
        public static class Item{
            private String name;
            private String price;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            @Override
            public String toString() {
                return "Item{" + "name=" + name + ", price=" + price + '}';
            }
        }
    }
    
    public static class Errors{
        private String type;
        private String message;
        private String code;
        private String param;
        private String messageToPurchaser;
        private String object;
        private String conecction;
        private boolean ifError;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getMessageToPurchaser() {
            return messageToPurchaser;
        }

        public void setMessageToPurchaser(String messageToPurchaser) {
            this.messageToPurchaser = messageToPurchaser;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public String getConecction() {
            return conecction;
        }

        public void setConecction(String conecction) {
            this.conecction = conecction;
        }

        public boolean isIfError() {
            return ifError;
        }

        public void setIfError(boolean ifError) {
            this.ifError = ifError;
        }

        @Override
        public String toString() {
            return "Errors{" + "type=" + type + ", message=" + message + ", code=" + code + ", param=" + param + ", messageToPurchaser=" + messageToPurchaser + ", object=" + object + ", conecction=" + conecction + ", ifError=" + ifError + '}';
        }
    }
}
