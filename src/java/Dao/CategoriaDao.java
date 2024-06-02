package Dao;

import Clases.Categorias;

/**
 *
 * @author saidc
 */
public interface CategoriaDao {
    void eliminar (int id_categoria);
    void editar (Categorias c);
}
