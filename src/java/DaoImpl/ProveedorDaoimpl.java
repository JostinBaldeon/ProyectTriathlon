package DaoImpl;

import Clases.Proveedor;
import Config.Conexion;
import Dao.ProveedorDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProveedorDaoimpl implements ProveedorDao {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;

    @Override
    public void agregar(Proveedor p) {
        String insertSQL = "INSERT INTO proveedor (nom_prov, num_cont_prov, fecha_creac_prov, pers_cont_prov, correo_cont_prov) VALUES (?, ?, ?, ?, ?)";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(insertSQL);
            ps.setString(1, p.getNom_prov());
            ps.setString(2, p.getNum_cont_prov());
            ps.setDate(3, new java.sql.Date(p.getFecha_creac_prov().getTime()));
            ps.setString(4, p.getPers_cont_prov());
            ps.setString(5, p.getCorreo_cont_prov());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    @Override
    public void eliminar(int id_prov) {
        String SQL = "DELETE FROM proveedor WHERE id_prov=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(SQL);
            ps.setInt(1, id_prov);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void editar(Proveedor p) {
        String updateSQL = "UPDATE proveedor SET nom_prov=?, num_cont_prov=?, fecha_creac_prov=?, pers_cont_prov=?, correo_cont_prov=? WHERE id_prov=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(updateSQL);
            ps.setString(1, p.getNom_prov());
            ps.setString(2, p.getNum_cont_prov());
            ps.setDate(3, new java.sql.Date(p.getFecha_creac_prov().getTime()));
            ps.setString(4, p.getPers_cont_prov());
            ps.setString(5, p.getCorreo_cont_prov());
            ps.setInt(6, p.getId_prov());
            ps.execute();
        } catch (SQLException e) {
            try {
                if (conex != null) {
                    conex.rollback(); // Revertir transacción en caso de error
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        }
    }

    
    @Override
    public void reiniciarAutoIncrement(int id_prov) {
        // Obtén el valor máximo actual del ID
    String maxIdSQL = "SELECT MAX(id_prov) FROM proveedor";
    try {
        conex = Conexion.getConexion();
        ps = conex.prepareStatement(maxIdSQL);
        ResultSet rs = ps.executeQuery();
        int maxId = 0;
        if (rs.next()) {
            maxId = rs.getInt(1);
        }

        // Establece el siguiente valor de AUTO_INCREMENT
        String resetSQL = "ALTER TABLE proveedor AUTO_INCREMENT = ?";
        ps = conex.prepareStatement(resetSQL);
        ps.setInt(1, maxId + 1); // Establece el próximo valor de AUTO_INCREMENT
        ps.execute();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al reiniciar AUTO_INCREMENT: " + e.toString());
        System.out.println("Error al reiniciar AUTO_INCREMENT: " + e.toString());
    }
    }

    

}
