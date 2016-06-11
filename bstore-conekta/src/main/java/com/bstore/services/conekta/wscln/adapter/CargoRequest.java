package com.bstore.services.conekta.wscln.adapter;

import java.math.BigDecimal;

public class CargoRequest {

	private BigDecimal amount;
	private String monthlyInstallments;
	private String description;
	private String currency;
	private Card card;
	private BigDecimal monto;

	public static class Card{
		private String name;
		private String number;
		private String cvc;
		private String expMonth;
		private String expYear;
		
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
		
		@Override
		public String toString() {
			return "Card [name=" + name + ", number=" + number + ", cvc=" + cvc
					+ ", expMonth=" + expMonth + ", expYear=" + expYear + "]";
		}
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getMonthlyInstallments() {
		return monthlyInstallments;
	}

	public void setMonthlyInstallments(String monthlyInstallments) {
		this.monthlyInstallments = monthlyInstallments;
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "CargoRequest [amount=" + amount + ", monthlyInstallments="
				+ monthlyInstallments + ", description=" + description
				+ ", currency=" + currency + ", card=" + card + ", monto="
				+ monto + "]";
	}

}