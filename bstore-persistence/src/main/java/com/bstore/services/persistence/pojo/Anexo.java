package com.bstore.services.persistence.pojo;

import java.sql.Clob;
import java.util.Date;

public class Anexo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7713327245131376713L;
	private int id;
	private int idPublicacion;
	private Clob traduccion;
	private String tipoImagen;
	private byte[] imagen;
	private String origenImagen;
	private int foja;
	private int activo;
	private int recargar;
	private Date fechaAlta;
	private Date fechaModificacion;
	private String resultImage;
	
	public Anexo(){
		
	}
	
	public Anexo(int id, int idPublicacion, Clob traductor, String tipoImagen, byte[] imagen, String origenImagen, int foja, int activo,
			int recargar, Date fechaAlta, Date fechaModificacion){
		this.id = id;
		this.idPublicacion = idPublicacion;
		this.traduccion = traductor;
		this.tipoImagen = tipoImagen;
		this.imagen = imagen;
		this.origenImagen = origenImagen;
		this.foja = foja;
		this.activo = activo;
		this.recargar = recargar;
		this.fechaAlta = fechaAlta;
		this.fechaModificacion = this.fechaAlta; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public Clob getTraduccion() {
		return traduccion;
	}

	public void setTraduccion(Clob traduccion) {
		this.traduccion = traduccion;
	}

	public String getTipoImagen() {
		return tipoImagen;
	}

	public void setTipoImagen(String tipoImagen) {
		this.tipoImagen = tipoImagen;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getOrigenImagen() {
		return origenImagen;
	}

	public void setOrigenImagen(String origenImagen) {
		this.origenImagen = origenImagen;
	}

	public int getFoja() {
		return foja;
	}

	public void setFoja(int foja) {
		this.foja = foja;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getResultImage() {
		return resultImage;
	}

	public void setResultImage(String resultImage) {
		this.resultImage = resultImage;
	}

	public int getRecargar() {
		return recargar;
	}

	public void setRecargar(int recargar) {
		this.recargar = recargar;
	}

	@Override
	public String toString() {
		return "Anexo [id=" + id + ", idPublicacion=" + idPublicacion
				+ ", traduccion=" + traduccion + ", tipoImagen=" + tipoImagen
				+ ", origenImagen=" + origenImagen + ", foja=" + foja
				+ ", activo=" + activo + ", recargar=" + recargar
				+ ", fechaAlta=" + fechaAlta + ", fechaModificacion="
				+ fechaModificacion + "]";
	}

}
