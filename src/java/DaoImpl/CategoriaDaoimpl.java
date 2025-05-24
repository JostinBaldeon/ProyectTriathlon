package DaoImpl;

import Clases.Categorias;
import Config.Conexion;
import Dao.CategoriaDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CategoriaDaoimpl implements CategoriaDao {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;

    @Override
    public void agregar(Categorias cate) {
        String insertSQL = "INSERT INTO categoria (nombre_categ) VALUES (?)";
        try {
            conex = Conexion.getConexion();
            if (conex != null) {
                ps = conex.prepareStatement(insertSQL);
                ps.setString(1, cate.getNombre_categ());
                ps.execute();
                System.out.println("Categoría agregada en BD: " + cate.getNombre_categ());
            } else {
                System.err.println("No se pudo establecer conexión para agregar categoría");
            }
        } catch (SQLException e) {
            System.err.println("Error SQL agregando categoría: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error agregando categoría: " + e.toString());
        } finally {
            cerrarRecursos();
        }
    }

    @Override
    public void eliminar(int id_categ) {
        String SQL = "DELETE FROM categoria WHERE id_categ=?";
        try {
            conex = Conexion.getConexion();
            if (conex != null) {
                ps = conex.prepareStatement(SQL);
                ps.setInt(1, id_categ);
                int filasAfectadas = ps.executeUpdate();
                System.out.println("Categoría eliminada. Filas afectadas: " + filasAfectadas);
            } else {
                System.err.println("No se pudo establecer conexión para eliminar categoría");
            }
        } catch (SQLException e) {
            System.err.println("Error SQL eliminando categoría: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }

    @Override
    public void reiniciarAutoIncrement(int id_categ) {
        String maxIdSQL = "SELECT MAX(id_categ) FROM categoria";
        try {
            conex = Conexion.getConexion();
            if (conex != null) {
                ps = conex.prepareStatement(maxIdSQL);
                rs = ps.executeQuery();
                int maxId = 0;
                if (rs.next()) {
                    maxId = rs.getInt(1);
                }

                String resetSQL = "ALTER TABLE categoria AUTO_INCREMENT = ?";
                ps = conex.prepareStatement(resetSQL);
                ps.setInt(1, maxId + 1);
                ps.execute();
                System.out.println("AUTO_INCREMENT reiniciado para categoria: " + (maxId + 1));
            } else {
                System.err.println("No se pudo establecer conexión para reiniciar AUTO_INCREMENT");
            }
        } catch (SQLException e) {
            System.err.println("Error reiniciando AUTO_INCREMENT: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al reiniciar AUTO_INCREMENT: " + e.toString());
        } finally {
            cerrarRecursos();
        }
    }

    @Override
    public void editar(Categorias cate) {
        String updateSQL = "UPDATE categoria SET nombre_categ=? WHERE id_categ=?";
        try {
            conex = Conexion.getConexion();
            if (conex != null) {
                ps = conex.prepareStatement(updateSQL);
                ps.setString(1, cate.getNombre_categ());
                ps.setInt(2, cate.getId_categ()); // Faltaba la condición WHERE
                int filasAfectadas = ps.executeUpdate();
                System.out.println("Categoría editada. Filas afectadas: " + filasAfectadas);
            } else {
                System.err.println("No se pudo establecer conexión para editar categoría");
            }
        } catch (SQLException e) {
            System.err.println("Error SQL editando categoría: " + e.getMessage());
            try {
                if (conex != null) {
                    conex.rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Error en rollback: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Error editando categoría: " + e.toString());
        } finally {
            cerrarRecursos();
        }
    }
    
    private void cerrarRecursos() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            // Note: No cerramos la conexión aquí porque puede ser reutilizada
            // La clase Conexion maneja el ciclo de vida de las conexiones
        } catch (SQLException e) {
            System.err.println("Error cerrando recursos: " + e.getMessage());
        }
    }
}