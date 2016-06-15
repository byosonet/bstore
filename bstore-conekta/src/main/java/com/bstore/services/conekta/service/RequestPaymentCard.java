package com.bstore.services.conekta.service;

import java.util.ArrayList;
import java.util.List;

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
	private Details details;

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

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "RequestPaymentCard [currency=" + currency + ", amount=" + amount + ", description=" + description
				+ ", referenceId=" + referenceId + ", card=" + card + ", detail=" + details + "]";
	}

	public static class Details {
		private String phone;
		private String email;
		private String name;
		private List<Item> line_items = new ArrayList<Item>();

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

		public List<Item> getLine_items() {
			return line_items;
		}

		public void setLine_items(List<Item> line_items) {
			this.line_items = line_items;
		}

		@Override
		public String toString() {
			return "Detail{" + "phone=" + phone + ", email=" + email + ", name=" + name + ", lineItems=" + line_items
					+ '}';
		}

		public static class Item {
			private String name;
			private String price;
			private String description;

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

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			@Override
			public String toString() {
				return "Item [name=" + name + ", price=" + price + ", description=" + description + "]";
			}

		}
	}
}
