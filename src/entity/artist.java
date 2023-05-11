/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.io.IOException;

import java.util.Objects;

/**
 *
 * @author yessine
 */
public class artist {

   
   
     private int id_Ar;
    private String nom_Ar; 
    private String prenom_Ar;
    private String detail_Ar;
    private String genre_Ar;
    private String desc_ar;
     private String url_ar;
    private String dateStr;
    public artist(){}

    public artist(int id_Ar, String nom_Ar, String prenom_Ar, String detail_Ar, String genre_Ar, String desc_ar, String url_ar, String dateStr) {
        this.id_Ar = id_Ar;
        this.nom_Ar = nom_Ar;
        this.prenom_Ar = prenom_Ar;
        this.detail_Ar = detail_Ar;
        this.genre_Ar = genre_Ar;
        this.desc_ar = desc_ar;
        this.url_ar = url_ar;
        this.dateStr = dateStr;
    }

    public artist(String nom_Ar, String prenom_Ar) {
      this.nom_Ar = nom_Ar;
        this.prenom_Ar = prenom_Ar;
    }

    public artist(String nom_Ar, String prenom_Ar, String dateStr, String detail_Ar, String genre_Ar, String desc_ar, String url_ar) {
        this.nom_Ar = nom_Ar;
        this.prenom_Ar = prenom_Ar;
        this.dateStr = dateStr;
        this.detail_Ar = detail_Ar;
        this.genre_Ar = genre_Ar;
        this.desc_ar = desc_ar;
        this.url_ar = url_ar;
        
    }

    public artist(String nom_Ar, String prenom_Ar, String detail_Ar, String genre_Ar, String desc_ar) {
        this.nom_Ar = nom_Ar;
        this.prenom_Ar = prenom_Ar;
        this.detail_Ar = detail_Ar;
        this.genre_Ar = genre_Ar;
        this.desc_ar = desc_ar;
    
        
    }
    
    public artist(int id_Ar){
    this.id_Ar=id_Ar;
    
    }
    
     public artist(int id_Ar, String nom_Ar, String prenom_Ar, String detail_Ar, String genre_Ar, String desc_ar) {
      this.id_Ar=id_Ar;
        this.nom_Ar = nom_Ar;
        this.prenom_Ar = prenom_Ar;
        this.detail_Ar = detail_Ar;
        this.genre_Ar = genre_Ar;
        this.desc_ar = desc_ar;
    
        
    }
     
     
     public artist(String nom_Ar, String prenom_Ar, String detail_Ar, String genre_Ar, String desc_ar, String url_ar) {
        this.nom_Ar = nom_Ar;
        this.prenom_Ar = prenom_Ar;
        this.detail_Ar = detail_Ar;
        this.genre_Ar = genre_Ar;
        this.desc_ar = desc_ar;
        this.url_ar = url_ar;
        
    }

    public artist(String nom_Ar, String prenom_Ar, String detail_Ar) {
        this.nom_Ar = nom_Ar;
        this.prenom_Ar = prenom_Ar; 
        this.detail_Ar = detail_Ar;
      }

  

    public artist(String nom_Ar, String prenom_Ar, String dateStr, String detail_Ar) {
       this.nom_Ar = nom_Ar;
        this.prenom_Ar = prenom_Ar; 
        this.detail_Ar = detail_Ar; }
    

    public int getId_Ar() {
        return id_Ar;
    }

    public String getNom_Ar() {
        return nom_Ar;
    }

    public String getPrenom_Ar() {
        return prenom_Ar;
    }

   

    public String getDetail_Ar() {
        return detail_Ar;
    }

    public String getGenre_Ar() {
        return genre_Ar;
    }

    public String getDesc_ar() {
        return desc_ar;
    }

    public String getUrl_ar() {
        return url_ar;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setId_Ar(int id_Ar) {
        this.id_Ar = id_Ar;
    }

    
    
    public void setNom_Ar(String nom_Ar) {
        this.nom_Ar = nom_Ar;
    }

    public void setPrenom_Ar(String prenom_Ar) {
        this.prenom_Ar = prenom_Ar;
    }

    

    public void setDetail_Ar(String detail_Ar) {
        this.detail_Ar = detail_Ar;
    }

    public void setGenre_Ar(String genre_Ar) {
        this.genre_Ar = genre_Ar;
    }

    public void setDesc_ar(String desc_ar) {
        this.desc_ar = desc_ar;
    }

    public void setUrl_ar(String url_ar) {
        this.url_ar = url_ar;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id_Ar;
        hash = 41 * hash + Objects.hashCode(this.nom_Ar);
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
        final artist other = (artist) obj;
        if (this.id_Ar != other.id_Ar) {
            return false;
        }
        if (!Objects.equals(this.nom_Ar, other.nom_Ar)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "artist{" + "id_Ar=" + id_Ar + ", nom_Ar=" + nom_Ar + ", prenom_Ar=" + prenom_Ar + ", detail_Ar=" + detail_Ar + ", genre_Ar=" + genre_Ar + ", desc_ar=" + desc_ar + ", url_ar=" + url_ar + ", dateStr=" + dateStr + '}';
    }

  
}
