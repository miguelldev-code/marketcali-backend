package miguel.marketcali.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    private static Properties prop = null;

    // Conexion obteniendo datos de propierties
    static {
        try {
            prop = new Properties();
            prop.load(ConexionDB.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration file", e);
        }
    }

    // Establecer conexion 
    public static Connection getConnection() throws SQLException {
        String url = prop.getProperty("db.url");
        String user = prop.getProperty("db.user");
        String password = prop.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }
}