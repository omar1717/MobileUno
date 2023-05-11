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
public class Salle {
    private String nbs;
    private String type;
    private int cin;

    public Salle() {
    }

    public Salle(String id, String type, int cin) {
        this.nbs = id;
        this.type = type;
        this.cin = cin;
    }

    public Salle(String type, int cin) {
        this.type = type;
        this.cin = cin;
    }

    public String getNbs() {
        return nbs;
    }

    public void setNbs(String id) {
        this.nbs = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }
    
    
}
