package com.bstore.services.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author guta
 */
public class MenuModel implements java.io.Serializable{

    private static final long serialVersionUID = 2132574709361534321L;
    
    private int idCol;
    private String nombreCol;
    private List<Pub> pubs;

    public int getIdCol() {
        return idCol;
    }

    public void setIdCol(int idCol) {
        this.idCol = idCol;
    }

    public String getNombreCol() {
        return nombreCol;
    }

    public void setNombreCol(String nombreCol) {
        this.nombreCol = nombreCol;
    }

    @Override
    public String toString() {
        return "MenuModel{" + "idCol=" + idCol + ", nombreCol=" + nombreCol + ", pubs=" + pubs + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idCol;
        hash = 59 * hash + Objects.hashCode(this.nombreCol);
        hash = 59 * hash + Objects.hashCode(this.pubs);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MenuModel other = (MenuModel) obj;
        if (this.idCol != other.idCol) {
            return false;
        }
        if (!Objects.equals(this.nombreCol, other.nombreCol)) {
            return false;
        }
        if (!Objects.equals(this.pubs, other.pubs)) {
            return false;
        }
        return true;
    }

    public List<Pub> getPubs() {
        return pubs;
    }
    public void setPubs(List<Pub> pubs) {
        this.pubs = pubs;
    } 
            
    public static class Pub implements java.io.Serializable{

        private static final long serialVersionUID = -4718267379572656752L;
        private int idPub;
        private String nombrePub;

        public int getIdPub() {
            return idPub;
        }

        public void setIdPub(int idPub) {
            this.idPub = idPub;
        }

        public String getNombrePub() {
            return nombrePub;
        }

        public void setNombrePub(String nombrePub) {
            this.nombrePub = nombrePub;
        }

        @Override
        public String toString() {
            return "Pub{" + "idPub=" + idPub + ", nombrePub=" + nombrePub + '}';
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 73 * hash + this.idPub;
            hash = 73 * hash + Objects.hashCode(this.nombrePub);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Pub other = (Pub) obj;
            if (this.idPub != other.idPub) {
                return false;
            }
            if (!Objects.equals(this.nombrePub, other.nombrePub)) {
                return false;
            }
            return true;
        }
        
        
    }
}
