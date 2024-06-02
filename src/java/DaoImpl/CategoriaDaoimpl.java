package DaoImpl;

import Clases.Categorias;
import Config.Conexion;
import Dao.CategoriaDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaDaoimpl implements CategoriaDao {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;

    @Override
    public void eliminar(int id_categ) {
        String SQL = "DELETE FROM categoria WHERE id_categ=?";
        try {
            conex = Conexion.getConexion();
            conex.setAutoCommit(false);  // Desactivar autocommit
            ps = conex.prepareStatement(SQL);
            ps.setInt(1, id_categ);
            ps.execute();
            conex.commit();  // Confirmar transacción
        } catch (SQLException e) {
            try {
                if (conex != null) {
                    conex.rollback();  // Revertir transacción en caso de error
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            System.out.println(e.toString());
        } finally {
            try {
                if (conex != null) {
                    conex.setAutoCommit(true);  // Volver a activar autocommit
                    conex.close();  // Cerrar conexión
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    @Override
    public void editar(Categorias c) {
        String updateSQL = "UPDATE categoria SET nombre_categ=?  WHERE id_categ=?";
        try {
            conex = Conexion.getConexion();
            conex.setAutoCommit(false);  // Desactivar autocommit
            ps = conex.prepareStatement(updateSQL);
            ps.setString(1, c.getNombre_categ());
            ps.setInt(2, c.getId_categ());
            ps.executeUpdate();
        conex.commit();  // Confirmar transacción
        } catch (SQLException e) {
            try {
                if (conex != null) {
                    conex.rollback();  // Revertir transacción en caso de error
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            System.out.println(e.toString());
        } finally {
            try {
                if (conex != null) {
                    conex.setAutoCommit(true);  // Volver a activar autocommit
                    conex.close();  // Cerrar conexión
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
