package Dao;

import Clases.Producto;

public interface ProductoDao {
    void agregar(Producto prod);
    void eliminar(int id_prod);
    void reiniciarAutoIncrement(int id_prod);
    void editar(Producto prod);
    
}
