/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author yessine
 */
public class equipement {
    private int id_equi;
    private String nom_equi;
    private String type_eq;
    
    public equipement(){}

    public equipement(String nom_equi, String type_eq) {
        this.nom_equi = nom_equi;
        this.type_eq = type_eq;
    }

    public equipement(int id_equi, String nom_equi, String type_eq) {
        this.id_equi = id_equi;
        this.nom_equi = nom_equi;
        this.type_eq = type_eq;
    }

    
    public int getId_equi() {
        return id_equi;
    }

    public String getNom_equi() {
        return nom_equi;
    }

    public String getType_eq() {
        return type_eq;
    }

    

    public void setNom_equi(String nom_equi) {
        this.nom_equi = nom_equi;
    }

    public void setType_eq(String type_eq) {
        this.type_eq = type_eq;
    }

    

    @Override
    public String toString() {
        return "equipement{" + "id_equi=" + id_equi + ", nom_equi=" + nom_equi + ", type_eq=" + type_eq + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id_equi;
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
        final equipement other = (equipement) obj;
        if (this.id_equi != other.id_equi) {
            return false;
        }
        return true;
    }
    
    
    
}
