/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
//taw n7oto fi description
public class Utilisateur {
    
    private int id;
    private String email;
    private String nom;
    private String prenom;
    private Date age;
    private String motdepasse;
    private String photo;

    public Utilisateur(Date age, String email, String nom, String prenom, String motdepasse, String photo) {
   
        this.email = email;
        this.age=age;
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.photo=photo;
    }
    
    

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur( String email) {
       
        this.email = email;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date Age) {
        this.age = Age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
      public String getphoto() {
        return email;
    }

    public void setphoto(String email) {
        this.email = email;
    }

  

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", age=" + age.toString() + ", adresse=" + email + ", photoDeProfil=" +  '}';
    }

    public Utilisateur(int id, Date age, String email, String nom, String prenom,String photo) {
        this.id = id;
        this.age = age;
        this.email = email;
      
        this.nom = nom;
        this.photo=photo;
        this.prenom = prenom;
    }

    public Utilisateur(Date age, String email, String nom, String prenom,String photo) {
        this.age = age;
        this.email = email;
        this.nom = nom;
        this.photo=photo;
        this.prenom = prenom;
    }
    
    
    
    
    
}