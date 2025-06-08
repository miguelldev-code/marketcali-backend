package miguel.marketcali.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    private static Connection conexion = null;

    public static Connection getConnection() {
        if (conexion == null) {
            try{
                // Configuracion enlazada al propierties
                Properties prop = new Properties();
                prop.load(ConexionDB.class.getResourceAsStream("/config.properties"));

                String url = prop.getProperty("db.url");
                String user = prop.getProperty("db.user");
                String password = prop.getProperty("db.password");

                conexion = DriverManager.getConnection(url, user, password);

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }

        }
        return conexion;
    }
}
