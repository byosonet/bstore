package com.bstore.services.persistence.pojo;
// Generated 02-jun-2016 21:47:36 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

/**
 * Compra generated by hbm2java
 */
public class Compra implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6547708758871807877L;
	private CompraId id;
	private FormaPago formaPago;
	private BigDecimal precioCompra;
	private Date fechaCompra;
	
	private String idConekta;
	private String liveMode;
	private String status;
	private String currencyCard;
	private String descriptionCard;
	private String nameCard;
	private String last4Card;
	private String brandCard;
	private String authCodeCard;
	private String amountCard; 
	private String nameUser;
	private String phoneUser;
	private String emailUser;
	private Publicacion publicacion;

	public Compra() {
	}

	public Compra(CompraId id, Date fechaCompra) {
		this.id = id;
		this.fechaCompra = fechaCompra;
	}

	public Compra(CompraId id, FormaPago formaPago, BigDecimal precioCompra, Date fechaCompra,
			String idConekta, String livemode, String status, String currencyCard, String descriptionCard,
			String nameCard, String last4Card, String brandCard, String authCodeCard, String amountCard,
			String nameUser, String phoneUser, String emailUser) {
		this.id = id;
		this.formaPago = formaPago;
		this.precioCompra = precioCompra;
		this.fechaCompra = fechaCompra;
		this.idConekta = idConekta;
		this.liveMode = livemode;
		this.status = status;
		this.currencyCard = currencyCard;
		this.descriptionCard = descriptionCard;
		this.nameCard = nameCard;
		this.last4Card = last4Card;
		this.brandCard = brandCard;
		this.authCodeCard = authCodeCard;
		this.amountCard = amountCard;
		this.nameUser = nameUser;
		this.phoneUser = phoneUser;
		this.emailUser = emailUser;	
	}

	public CompraId getId() {
		return this.id;
	}

	public void setId(CompraId id) {
		this.id = id;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public BigDecimal getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Date getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getIdConekta() {
		return idConekta;
	}

	public void setIdConekta(String idConekta) {
		this.idConekta = idConekta;
	}

	public String getLiveMode() {
		return liveMode;
	}

	public void setLiveMode(String liveMode) {
		this.liveMode = liveMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrencyCard() {
		return currencyCard;
	}

	public void setCurrencyCard(String currencyCard) {
		this.currencyCard = currencyCard;
	}

	public String getDescriptionCard() {
		return descriptionCard;
	}

	public void setDescriptionCard(String descriptionCard) {
		this.descriptionCard = descriptionCard;
	}

	public String getNameCard() {
		return nameCard;
	}

	public void setNameCard(String nameCard) {
		this.nameCard = nameCard;
	}

	public String getLast4Card() {
		return last4Card;
	}

	public void setLast4Card(String last4Card) {
		this.last4Card = last4Card;
	}

	public String getBrandCard() {
		return brandCard;
	}

	public void setBrandCard(String brandCard) {
		this.brandCard = brandCard;
	}

	public String getAuthCodeCard() {
		return authCodeCard;
	}

	public void setAuthCodeCard(String authCodeCard) {
		this.authCodeCard = authCodeCard;
	}

	public String getAmountCard() {
		return amountCard;
	}

	public void setAmountCard(String amountCard) {
		this.amountCard = amountCard;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getPhoneUser() {
		return phoneUser;
	}

	public void setPhoneUser(String phoneUser) {
		this.phoneUser = phoneUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amountCard == null) ? 0 : amountCard.hashCode());
		result = prime * result + ((authCodeCard == null) ? 0 : authCodeCard.hashCode());
		result = prime * result + ((brandCard == null) ? 0 : brandCard.hashCode());
		result = prime * result + ((currencyCard == null) ? 0 : currencyCard.hashCode());
		result = prime * result + ((descriptionCard == null) ? 0 : descriptionCard.hashCode());
		result = prime * result + ((emailUser == null) ? 0 : emailUser.hashCode());
		result = prime * result + ((fechaCompra == null) ? 0 : fechaCompra.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idConekta == null) ? 0 : idConekta.hashCode());
		result = prime * result + ((last4Card == null) ? 0 : last4Card.hashCode());
		result = prime * result + ((liveMode == null) ? 0 : liveMode.hashCode());
		result = prime * result + ((nameCard == null) ? 0 : nameCard.hashCode());
		result = prime * result + ((nameUser == null) ? 0 : nameUser.hashCode());
		result = prime * result + ((phoneUser == null) ? 0 : phoneUser.hashCode());
		result = prime * result + ((precioCompra == null) ? 0 : precioCompra.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		if (amountCard == null) {
			if (other.amountCard != null)
				return false;
		} else if (!amountCard.equals(other.amountCard))
			return false;
		if (authCodeCard == null) {
			if (other.authCodeCard != null)
				return false;
		} else if (!authCodeCard.equals(other.authCodeCard))
			return false;
		if (brandCard == null) {
			if (other.brandCard != null)
				return false;
		} else if (!brandCard.equals(other.brandCard))
			return false;
		if (currencyCard == null) {
			if (other.currencyCard != null)
				return false;
		} else if (!currencyCard.equals(other.currencyCard))
			return false;
		if (descriptionCard == null) {
			if (other.descriptionCard != null)
				return false;
		} else if (!descriptionCard.equals(other.descriptionCard))
			return false;
		if (emailUser == null) {
			if (other.emailUser != null)
				return false;
		} else if (!emailUser.equals(other.emailUser))
			return false;
		if (fechaCompra == null) {
			if (other.fechaCompra != null)
				return false;
		} else if (!fechaCompra.equals(other.fechaCompra))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idConekta == null) {
			if (other.idConekta != null)
				return false;
		} else if (!idConekta.equals(other.idConekta))
			return false;
		if (last4Card == null) {
			if (other.last4Card != null)
				return false;
		} else if (!last4Card.equals(other.last4Card))
			return false;
		if (liveMode == null) {
			if (other.liveMode != null)
				return false;
		} else if (!liveMode.equals(other.liveMode))
			return false;
		if (nameCard == null) {
			if (other.nameCard != null)
				return false;
		} else if (!nameCard.equals(other.nameCard))
			return false;
		if (nameUser == null) {
			if (other.nameUser != null)
				return false;
		} else if (!nameUser.equals(other.nameUser))
			return false;
		if (phoneUser == null) {
			if (other.phoneUser != null)
				return false;
		} else if (!phoneUser.equals(other.phoneUser))
			return false;
		if (precioCompra == null) {
			if (other.precioCompra != null)
				return false;
		} else if (!precioCompra.equals(other.precioCompra))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", precioCompra=" + precioCompra + ", fechaCompra=" + fechaCompra + "]";
	}

}
