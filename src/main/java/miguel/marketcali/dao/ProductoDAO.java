package miguel.marketcali.dao;
import miguel.marketcali.model.Producto;
import miguel.marketcali.util.ConexionDB;
import java.sql.*;

public class ProductoDAO {

    // Insertar producto
    public void insertarProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO producto (nombre, marca, precio, cantidad, categoria) VALUES (?,?,?,?,?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement stat = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stat.setString(1, producto.getNombre());
            stat.setString(2, producto.getMarca());
            stat.setDouble(3, producto.getPrecio());
            stat.setInt(4, producto.getCantidad());
            stat.setString(5, producto.getCategoria());

            stat.executeUpdate();

            // Generar ID
            try (ResultSet rs = stat.getGeneratedKeys()) {
                if (rs.next()) {
                    producto.setId(rs.getInt(1));
                }
            }
        }
    }

    // Consultar producto por ID
    public Producto consultarProducto(int id) throws SQLException {
        String sql = "SELECT * FROM producto WHERE id = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement stat = con.prepareStatement(sql)) {

            stat.setInt(1, id);
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                            rs.getInt("id"), // Get ID from results
                            rs.getString("nombre"),
                            rs.getString("marca"),
                            rs.getDouble("precio"),
                            rs.getInt("cantidad"),
                            rs.getString("categoria")
                    );
                }
            }
        }
        return null;
    }

    // Actualizar producto
    public void actualizarProducto(Producto producto) throws SQLException {
        String sql = "UPDATE producto SET nombre=?, marca=?, precio=?, cantidad=?, categoria=? WHERE id = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement stat = con.prepareStatement(sql)) {

            stat.setString(1, producto.getNombre());
            stat.setString(2, producto.getMarca());
            stat.setDouble(3, producto.getPrecio());
            stat.setInt(4, producto.getCantidad());
            stat.setString(5, producto.getCategoria());
            stat.setInt(6, producto.getId());

            stat.executeUpdate();
        }
    }

    // Eliminar producto
    public void eliminarProducto(int id) throws SQLException {
        // codigo SQL
        String sql = "DELETE FROM producto WHERE id = ?";
        try(Connection con = ConexionDB.getConnection();
        PreparedStatement stat = con.prepareStatement(sql)){

            stat.setInt(1, id);
            stat.executeUpdate();
        }
    }
}
