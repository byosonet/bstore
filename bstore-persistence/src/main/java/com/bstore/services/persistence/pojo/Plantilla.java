package com.bstore.services.persistence.pojo;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class Plantilla {
    private int id;
    private String description;
    private String subject;
    private Clob mailTemplate;
    private String template;
    
    public Plantilla(){
        
    }
    
    
    public Plantilla(int id, String description, String subject, Clob mailTemplate){
        this.id = id;
        this.description = description;
        this.subject = subject;
        this.mailTemplate = mailTemplate;
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

    public Clob getMailTemplate() {
        return mailTemplate;
    }

    public void setMailTemplate(Clob mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
    
    
    
    public String readClob(Clob clob) throws SQLException, IOException {
        StringBuilder sb = new StringBuilder((int) clob.length());
        Reader r = clob.getCharacterStream();
        char[] cbuf = new char[2048];
        int n;
        while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
            sb.append(cbuf, 0, n);
        }
        this.setTemplate(sb.toString());
        return this.getTemplate();
    }

    @Override
    public String toString() {
        return "MailTemplate{" + "id=" + id + ", description=" + description + ", subject=" + subject + ", mailTemplate=" + mailTemplate + ", template=" + template + '}';
    }
    
}
