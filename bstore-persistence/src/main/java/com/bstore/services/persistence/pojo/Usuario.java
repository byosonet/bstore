package com.bstore.services.persistence.pojo;
// Generated 02-jun-2016 21:47:36 by Hibernate Tools 3.4.0.CR1

/**
 * Usuario generated by hbm2java
 */
public class Usuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6269529491327180701L;
	private Usuario id;
	private String AMaterno;
	private String APaterno;
	private String email;
	private int estatus;
	private byte[] foto;
	private String login;
	private String nombre;
	private String password;
	private int perfilId;
	private String sexo;

	public Usuario() {
	}

	public Usuario(Usuario id, String APaterno, int estatus, String login, String nombre, String password,
			int perfilId) {
		this.id = id;
		this.APaterno = APaterno;
		this.estatus = estatus;
		this.login = login;
		this.nombre = nombre;
		this.password = password;
		this.perfilId = perfilId;
	}

	public Usuario(Usuario id, String AMaterno, String APaterno, String email, int estatus, byte[] foto, String login,
			String nombre, String password, int perfilId, String sexo) {
		this.id = id;
		this.AMaterno = AMaterno;
		this.APaterno = APaterno;
		this.email = email;
		this.estatus = estatus;
		this.foto = foto;
		this.login = login;
		this.nombre = nombre;
		this.password = password;
		this.perfilId = perfilId;
		this.sexo = sexo;
	}

	public Usuario getId() {
		return this.id;
	}

	public void setId(Usuario id) {
		this.id = id;
	}

	public String getAMaterno() {
		return this.AMaterno;
	}

	public void setAMaterno(String AMaterno) {
		this.AMaterno = AMaterno;
	}

	public String getAPaterno() {
		return this.APaterno;
	}

	public void setAPaterno(String APaterno) {
		this.APaterno = APaterno;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEstatus() {
		return this.estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPerfilId() {
		return this.perfilId;
	}

	public void setPerfilId(int perfilId) {
		this.perfilId = perfilId;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
