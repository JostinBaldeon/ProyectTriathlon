package Dao;

import Clases.Proveedor;

public interface ProveedorDao {
    void agregar(Proveedor p);
    void eliminar(int id_prov);
    void reiniciarAutoIncrement(int id_prov);
    void editar(Proveedor p);
    
}
