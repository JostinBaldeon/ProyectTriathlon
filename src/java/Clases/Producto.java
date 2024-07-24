package Clases;

public class Producto {
    private int id_prod;
    private String nomb_prod;
    private String genero_prod;
    private String talla_prod;
    private String descr_prod;
    private String nom_prov;
    private String nombre_categ;

    public Producto() {
    }

    public Producto(int id_prod, String nomb_prod, String genero_prod, String talla_prod, String descr_prod, String nom_prov, String nombre_categ) {
        this.id_prod = id_prod;
        this.nomb_prod = nomb_prod;
        this.genero_prod = genero_prod;
        this.talla_prod = talla_prod;
        this.descr_prod = descr_prod;
        this.nom_prov = nom_prov;
        this.nombre_categ = nombre_categ;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNomb_prod() {
        return nomb_prod;
    }

    public void setNomb_prod(String nomb_prod) {
        this.nomb_prod = nomb_prod;
    }

    public String getGenero_prod() {
        return genero_prod;
    }

    public void setGenero_prod(String genero_prod) {
        this.genero_prod = genero_prod;
    }

    public String getTalla_prod() {
        return talla_prod;
    }

    public void setTalla_prod(String talla_prod) {
        this.talla_prod = talla_prod;
    }

    public String getDescr_prod() {
        return descr_prod;
    }

    public void setDescr_prod(String descr_prod) {
        this.descr_prod = descr_prod;
    }

    public String getNom_prov() {
        return nom_prov;
    }

    public void setNom_prov(String nom_prov) {
        this.nom_prov = nom_prov;
    }

    public String getNombre_categ() {
        return nombre_categ;
    }

    public void setNombre_categ(String nombre_categ) {
        this.nombre_categ = nombre_categ;
    }
    
    
    
}
