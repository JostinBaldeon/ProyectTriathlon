package Clases;

public class Categorias {
    int id_categ;
    String nombre_categ;

    public Categorias() {
    }

    public Categorias(int id_categ, String nombre_categ) {
        this.id_categ = id_categ;
        this.nombre_categ = nombre_categ;
    }

    public int getId_categ() {
        return id_categ;
    }

    public void setId_categ(int id_categ) {
        this.id_categ = id_categ;
    }

    public String getNombre_categ() {
        return nombre_categ;
    }

    public void setNombre_categ(String nombre_categ) {
        this.nombre_categ = nombre_categ;
    }
    
    
}
