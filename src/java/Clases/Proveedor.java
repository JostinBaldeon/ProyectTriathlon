package Clases;

import java.util.Date;

public class Proveedor {
    private int id_prov;
    private String nom_prov;
    private String num_cont_prov;
    private java.sql.Date fecha_creac_prov;
    private String  pers_cont_prov;
    private String correo_cont_prov;

    public Proveedor() {
    }

    public Proveedor(int id_prov, String nom_prov, String num_cont_prov, java.sql.Date fecha_creac_prov, String pers_cont_prov, String correo_cont_prov) {
        this.id_prov = id_prov;
        this.nom_prov = nom_prov;
        this.num_cont_prov = num_cont_prov;
        this.fecha_creac_prov = fecha_creac_prov;
        this.pers_cont_prov = pers_cont_prov;
        this.correo_cont_prov = correo_cont_prov;
    }

    public int getId_prov() {
        return id_prov;
    }

    public void setId_prov(int id_prov) {
        this.id_prov = id_prov;
    }

    public String getNom_prov() {
        return nom_prov;
    }

    public void setNom_prov(String nom_prov) {
        this.nom_prov = nom_prov;
    }

    public String getNum_cont_prov() {
        return num_cont_prov;
    }

    public void setNum_cont_prov(String num_cont_prov) {
        this.num_cont_prov = num_cont_prov;
    }

    public Date getFecha_creac_prov() {
        return fecha_creac_prov;
    }

    public void setFecha_creac_prov(java.sql.Date fecha_creac_prov) {
        this.fecha_creac_prov = fecha_creac_prov;
    }

    public String getPers_cont_prov() {
        return pers_cont_prov;
    }

    public void setPers_cont_prov(String pers_cont_prov) {
        this.pers_cont_prov = pers_cont_prov;
    }

    public String getCorreo_cont_prov() {
        return correo_cont_prov;
    }

    public void setCorreo_cont_prov(String correo_cont_prov) {
        this.correo_cont_prov = correo_cont_prov;
    }

    
}
