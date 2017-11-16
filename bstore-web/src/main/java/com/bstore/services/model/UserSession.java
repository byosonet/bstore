package com.bstore.services.model;

/**
 *
 * @author gtrejo
 */
public class UserSession implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String email;
    private String nombre;
    private String paterno;    
    private String telefono;
    private String perfil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", email=" + email + ", nombre=" + nombre + ", paterno=" + paterno + ", perfil=" + perfil + ", telefono=" + telefono + '}';
    }

}
