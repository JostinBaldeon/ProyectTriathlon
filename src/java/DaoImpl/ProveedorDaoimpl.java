package DaoImpl;

import Clases.Proveedor;

import Config.Conexion;
import Dao.ProveedorDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class ProveedorDaoimpl implements ProveedorDao {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;

    @Override
    public void eliminar(int id_prov) {
        String SQL = "DELETE FROM proveedor WHERE id_prov=?";
        try {
            conex = Conexion.getConexion();
            conex.setAutoCommit(false);  // Desactivar autocommit
            ps = conex.prepareStatement(SQL);
            ps.setInt(1, id_prov);
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
    public void editar(Proveedor p) {
        String updateSQL = "UPDATE proveedor SET nom_prov=?,num_cont_prov=?, fecha_creac_prov=?, pers_cont_prov=?,correo_cont_prov=?  WHERE id_prov=?";
        try {
            conex = Conexion.getConexion();
            conex.setAutoCommit(false);  // Desactivar autocommit
            ps = conex.prepareStatement(updateSQL);
            ps.setString(1, p.getNom_prov());
            ps.setString(2, p.getNum_cont_prov());
            ps.setDate(3, new java.sql.Date(p.getFecha_creac_prov().getTime()));
            ps.setString(4, p.getPers_cont_prov());
            ps.setString(5, p.getCorreo_cont_prov());
            ps.setInt(6, p.getId_prov());
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
