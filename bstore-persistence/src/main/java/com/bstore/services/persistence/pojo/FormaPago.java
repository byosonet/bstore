package com.bstore.services.persistence.pojo;
// Generated 02-jun-2016 21:47:36 by Hibernate Tools 3.4.0.CR1

/**
 * FormaPago generated by hbm2java
 */
public class FormaPago implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8033327071643456923L;
	private int id;
	private String formaPago;
	private Integer status;

	public FormaPago() {
	}

	public FormaPago(int id) {
		this.id = id;
	}

	public FormaPago(int id, String formaPago, Integer status) {
		this.id = id;
		this.formaPago = formaPago;
		this.status = status;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FormaPago [id=" + id + ", formaPago=" + formaPago + ", status=" + status + "]";
	}

	
}
