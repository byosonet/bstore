package com.bstore.services.persistence.pojo;
// Generated 02-jun-2016 21:47:36 by Hibernate Tools 3.4.0.CR1

/**
 * CompraId generated by hbm2java
 */
public class CompraId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7227843486673662935L;
	private int idUsuario;
	private int idPublicacion;
	private int id;

	public CompraId() {
	}

	public CompraId(int idUsuario, int idPublicacion, int id) {
		this.idUsuario = idUsuario;
		this.idPublicacion = idPublicacion;
		this.id = id;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPublicacion() {
		return this.idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CompraId))
			return false;
		CompraId castOther = (CompraId) other;

		return (this.getIdUsuario() == castOther.getIdUsuario())
				&& (this.getIdPublicacion() == castOther.getIdPublicacion()) && (this.getId() == castOther.getId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdUsuario();
		result = 37 * result + this.getIdPublicacion();
		result = 37 * result + this.getId();
		return result;
	}

}