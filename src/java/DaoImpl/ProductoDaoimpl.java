package DaoImpl;

import Clases.Producto;
import Config.Conexion;
import Dao.ProductoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProductoDaoimpl implements ProductoDao {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;

    @Override
    public void agregar(Producto prod) {
        String insertSQL = "INSERT INTO producto (nomb_prod, genero_prod, talla_prod, descr_prod, nom_prov, nombre_categ) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(insertSQL);
            ps.setString(1, prod.getNomb_prod());
            ps.setString(2, prod.getGenero_prod());
            ps.setString(3, prod.getTalla_prod());
            ps.setString(4, prod.getDescr_prod());
            ps.setString(5, prod.getNom_prov());
            ps.setString(6, prod.getNombre_categ());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    @Override
    public void eliminar(int id_prod) {
        String SQL = "DELETE FROM producto WHERE id_prod=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(SQL);
            ps.setInt(1, id_prod);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void reiniciarAutoIncrement(int id_prod) {
        // Obtén el valor máximo actual del ID
        String maxIdSQL = "SELECT MAX(id_prod) FROM producto";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(maxIdSQL);
            ResultSet rs = ps.executeQuery();
            int maxId = 0;
            if (rs.next()) {
                maxId = rs.getInt(1);
            }

            // Establece el siguiente valor de AUTO_INCREMENT
            String resetSQL = "ALTER TABLE producto AUTO_INCREMENT = ?";
            ps = conex.prepareStatement(resetSQL);
            ps.setInt(1, maxId + 1); // Establece el próximo valor de AUTO_INCREMENT
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al reiniciar AUTO_INCREMENT: " + e.toString());
            System.out.println("Error al reiniciar AUTO_INCREMENT: " + e.toString());
        }
    }

    @Override
    public void editar(Producto prod) {
        String updateSQL = "UPDATE producto SET nomb_prod=?, genero_prod=?, talla_prod=?, descr_prod=?, nom_prov=?, nombre_categ=? WHERE id_prod=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(updateSQL);
            ps.setString(1, prod.getNomb_prod());
            ps.setString(2, prod.getGenero_prod());
            ps.setString(3, prod.getTalla_prod());
            ps.setString(4, prod.getDescr_prod());
            ps.setString(5, prod.getNom_prov());
            ps.setString(6, prod.getNombre_categ());
            ps.setInt(7, prod.getId_prod());
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
}