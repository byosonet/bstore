package com.bstore.services.persistence.pojo;

import java.util.Date;

/**
 *
 * @author gtrejo
 */
public class Sesion implements java.io.Serializable{

    private static final long serialVersionUID = 6155716255476882147L;
    private int id;
    private String idSesion;
    private String usuario;
    private Date fecha;
    
    public Sesion(){
    }
    
    public Sesion(int id, String idSesion, String usuario, Date fecha){
        this.id = id;
        this.idSesion = idSesion;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sesion{" + "id=" + id + ", idSesion=" + idSesion + ", usuario=" + usuario + ", fecha=" + fecha + '}';
    }
    
}
