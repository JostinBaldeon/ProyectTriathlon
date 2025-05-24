package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author saidc
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/bdtriathlon?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "";

    // Método para Spring JdbcTemplate
    public DriverManagerDataSource Conectar() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        try {
            datasource.setDriverClassName(DRIVER);
            datasource.setUrl(URL);
            datasource.setUsername(USER);
            datasource.setPassword(PASS);
            System.out.println("DataSource configurado correctamente");
        } catch (Exception e) {
            System.err.println("Error configurando DataSource: " + e.getMessage());
            e.printStackTrace();
        }
        return datasource;
    }

    // Método para conexiones directas JDBC
    public static Connection getConexion() {
        Connection cx = null;
        try {
            Class.forName(DRIVER);
            cx = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión establecida correctamente");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado");
            System.err.println("Asegúrate de tener el conector MySQL en tu classpath");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error de conexión a la base de datos:");
            System.err.println("URL: " + URL);
            System.err.println("Usuario: " + USER);
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return cx;
    }
    
    // Método para cerrar conexión
    public static void cerrarConexion(Connection cx) {
        try {
            if (cx != null && !cx.isClosed()) {
                cx.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error cerrando conexión: " + e.getMessage());
        }
    }
    
    // Método para probar la conexión
    public static boolean probarConexion() {
        Connection cx = null;
        try {
            cx = getConexion();
            return cx != null && !cx.isClosed();
        } catch (SQLException e) {
            System.err.println("Error probando conexión: " + e.getMessage());
            return false;
        } finally {
            cerrarConexion(cx);
        }
    }
}