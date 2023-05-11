/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author malek guemri
 */
public class Event {
    private String nom;
    private String type;
    private int salle;
    private String dd;
    private String df;
    private int nbp;

    public Event() {
    }

    public Event(String nom, String type, int salle, String dd, String df, int nbp) {
        this.nom = nom;
        this.type = type;
        this.salle = salle;
        this.dd = dd;
        this.df = df;
        this.nbp = nbp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSalle() {
        return salle;
    }

    public void setSalle(int salle) {
        this.salle = salle;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public int getNbp() {
        return nbp;
    }

    public void setNbp(int nbp) {
        this.nbp = nbp;
    }

    
    
    
}
