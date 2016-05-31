// default package
// Generated 31-may-2016 0:05:40 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ciudad generated by hbm2java
 */
public class Ciudad implements java.io.Serializable {

	private CiudadId id;
	private Estado estado;
	private Usuario usuario;
	private int estatus;
	private String nombre;
	private Date fechaUmodif;
	private Set<CiudadMunicipio> ciudadMunicipios = new HashSet<CiudadMunicipio>(0);

	public Ciudad() {
	}

	public Ciudad(CiudadId id, Estado estado, Usuario usuario, int estatus, String nombre, Date fechaUmodif) {
		this.id = id;
		this.estado = estado;
		this.usuario = usuario;
		this.estatus = estatus;
		this.nombre = nombre;
		this.fechaUmodif = fechaUmodif;
	}

	public Ciudad(CiudadId id, Estado estado, Usuario usuario, int estatus, String nombre, Date fechaUmodif,
			Set<CiudadMunicipio> ciudadMunicipios) {
		this.id = id;
		this.estado = estado;
		this.usuario = usuario;
		this.estatus = estatus;
		this.nombre = nombre;
		this.fechaUmodif = fechaUmodif;
		this.ciudadMunicipios = ciudadMunicipios;
	}

	public CiudadId getId() {
		return this.id;
	}

	public void setId(CiudadId id) {
		this.id = id;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getEstatus() {
		return this.estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaUmodif() {
		return this.fechaUmodif;
	}

	public void setFechaUmodif(Date fechaUmodif) {
		this.fechaUmodif = fechaUmodif;
	}

	public Set<CiudadMunicipio> getCiudadMunicipios() {
		return this.ciudadMunicipios;
	}

	public void setCiudadMunicipios(Set<CiudadMunicipio> ciudadMunicipios) {
		this.ciudadMunicipios = ciudadMunicipios;
	}

}