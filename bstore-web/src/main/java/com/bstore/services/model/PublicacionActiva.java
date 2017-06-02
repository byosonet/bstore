package com.bstore.services.model;

/**
 *
 * @author guta
 */
public class PublicacionActiva implements java.io.Serializable{

    private static final long serialVersionUID = -7981976512466831257L;
    private int id;
    private String name;
    private boolean purchased;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", name=" + name + '}';
    }
    
}
