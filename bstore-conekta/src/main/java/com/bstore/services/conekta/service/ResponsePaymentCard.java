package com.bstore.services.conekta.service;

import java.util.List;

/**
 *
 * @author gtrejo
 */
public class ResponsePaymentCard {
    private String id;
    private boolean livemode;
    private long createdAt;
    private String status;
    private String currency;
    private String description;
    private String referenceId;
    private String failureCode;
    private String failureMessage;
    private String object;
    private long amount;
    private Payment paymentMethod;
    private Details details;
    private Errors error;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLivemode() {
        return livemode;
    }

    public void setLivemode(boolean livemode) {
        this.livemode = livemode;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
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

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
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

    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Errors getError() {
        return error;
    }

    public void setError(Errors error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponsePaymentCard{" + "id=" + id + ", livemode=" + livemode + ", createdAt=" + createdAt + ", status=" + status + ", currency=" + currency + ", description=" + description + ", referenceId=" + referenceId + ", failureCode=" + failureCode + ", failureMessage=" + failureMessage + ", object=" + object + ", amount=" + amount + ", paymentMethod=" + paymentMethod + ", details=" + details + ", error=" + error + '}';
    }
    
    public static class Payment{
        private String authCode;
        private String object;
        private String last4;
        private String expMonth;
        private String expYear;
        private String name;
        private String brand;

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }
        
        public String getAuthCode() {
            return authCode;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public String getLast4() {
            return last4;
        }

        public void setLast4(String last4) {
            this.last4 = last4;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Payment{" + "authCode=" + authCode + ", object=" + object + ", last4=" + last4 + ", expMonth=" + expMonth + ", expYear=" + expYear + ", name=" + name + ", brand=" + brand + '}';
        }
    }
    
    public static class Details{
        private String name;
        private String phone;
        private String email;
        private List<LineItems> lineItems;

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

		public List<LineItems> getLineItems() {
			return lineItems;
		}

		public void setLineItems(List<LineItems> lineItems) {
			this.lineItems = lineItems;
		}

		@Override
		public String toString() {
			return "Details [name=" + name + ", phone=" + phone + ", email=" + email + ", lineItems=" + lineItems + "]";
		}

		public static class LineItems{
            private String name;
            private String description;
            private Long unitPrice;
            private Integer quantity;
            private String sku;
            private String type;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Long getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(Long unitPrice) {
                this.unitPrice = unitPrice;
            }

            public Integer getQuantity() {
                return quantity;
            }

            public void setQuantity(Integer quantity) {
                this.quantity = quantity;
            }

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            @Override
            public String toString() {
                return "LineItems{" + "name=" + name + ", description=" + description + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", sku=" + sku + ", type=" + type + '}';
            }
            
        }
    }
    
    public static class Errors {
        private String status;
        private String message;
        private String error1;
        private String error2;
        private String conecction;
        private boolean ifError;

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

        public boolean isIfError() {
            return ifError;
        }

        public void setIfError(boolean ifError) {
            this.ifError = ifError;
        }

        @Override
        public String toString() {
            return "Errors{" + "status=" + status + ", message=" + message + ", error1=" + error1 + ", error2=" + error2 + ", conecction=" + conecction + ", ifError=" + ifError + '}';
        }
    }
}
