/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MSI
 */
public class Boutique {
    private int idBo;
    private String nomBo;
    private String adresseBo;
    private double latB;
    private double longB;

    public Boutique() {
    }

    public Boutique( String nomBo, String adresseBo, double latB, double longB) {
     
        this.nomBo = nomBo;
        this.adresseBo = adresseBo;
        this.latB = latB;
        this.longB = longB;
    }

    public Boutique(int id, String nom, String adresse, double lat, double lon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    public int getIdBo() {
        return idBo;
    }

    public void setIdBo(int idBo) {
        this.idBo = idBo;
    }

    public String getNomBo() {
        return nomBo;
    }

    public void setNomBo(String nomBo) {
        this.nomBo = nomBo;
    }

    public String getAdresseBo() {
        return adresseBo;
    }

    public void setAdresseBo(String adresseBo) {
        this.adresseBo = adresseBo;
    }

    public double getLatB() {
        return latB;
    }

    public void setLatB(double latB) {
        this.latB = latB;
    }

    public double getLongB() {
        return longB;
    }

    @Override
    public String toString() {
        return "Boutique{" + "idBo=" + idBo + ", nomBo=" + nomBo + ", adresseBo=" + adresseBo + ", latB=" + latB + ", longB=" + longB + '}';
    }

    public void setLongB(double longB) {
        this.longB = longB;
    }
}
