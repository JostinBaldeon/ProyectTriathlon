package Dao;

import Clases.Categorias;

/**
 *
 * @author saidc
 */
public interface CategoriaDao {
    void agregar(Categorias cate);
    void eliminar (int id_categoria);
    void reiniciarAutoIncrement(int id_prov);
    void editar (Categorias cate);
}
