package Config;

import Clases.Categorias;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author saidc
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/bdtriathlon";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection cx = null;

    public DriverManagerDataSource Conectar() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName(DRIVER);
        datasource.setUrl(URL);
        datasource.setUsername(USER);
        datasource.setPassword(PASS);
        return datasource;
    }

    public static Connection getConexion(){
        try {
            Class.forName(DRIVER);
            if(cx==null){
                cx = DriverManager.getConnection(URL, USER, PASS);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: "+e);
        }
    
    return cx;
    }
    
    

}
