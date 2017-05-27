package com.bstore.services.persistence.pojo;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

/**
 *
 * @author gtrejo
 */
public class Plantilla implements java.io.Serializable{

    private static final long serialVersionUID = 602530830786371688L;
    private int id;
    private String description;
    private String subject;
    private Clob plantilla;
    private String plantillaText;
    
    public Plantilla(){
        
    }
    
    
    public Plantilla(int id, String description, String subject, Clob plantilla){
        this.id = id;
        this.description = description;
        this.subject = subject;
        this.plantilla = plantilla;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Clob getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(Clob plantilla) {
        this.plantilla = plantilla;
    }

    public String getPlantillaText() {
        return plantillaText;
    }

    public void setPlantillaText(String plantillaText) {
        this.plantillaText = plantillaText;
    }
    
    
    
    public String convertClobToString(Clob clob) throws SQLException, IOException {
        StringBuilder sb = new StringBuilder((int) clob.length());
        Reader r = clob.getCharacterStream();
        char[] cbuf = new char[2048];
        int n;
        while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
            sb.append(cbuf, 0, n);
        }
        this.setPlantillaText(sb.toString());
        return this.getPlantillaText();
    }

    @Override
    public String toString() {
        return "MailTemplate{" + "id=" + id + ", description=" + description + ", subject=" + subject + ", plantilla=" + plantilla + ", plantillaText=" + plantillaText + '}';
    }
    
}
