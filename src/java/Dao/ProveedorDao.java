package Dao;

import Clases.Proveedor;

/**
 *
 * @author saidc
 */
public interface ProveedorDao {
    void eliminar (int id_prov);
    void editar (Proveedor p);
}
