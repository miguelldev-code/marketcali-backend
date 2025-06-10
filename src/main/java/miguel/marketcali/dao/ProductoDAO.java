// ProductoDAO.java
package miguel.marketcali.dao;

import miguel.marketcali.model.Producto;
import miguel.marketcali.util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Insertar producto (sin cambios)
    public void insertarProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO producto (nombre, marca, precio, cantidad, categoria) VALUES (?,?,?,?,?)";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement stat = con.prepareStatement(sql)){
            stat.setString(1, producto.getNombre());
            stat.setString(2, producto.getMarca());
            stat.setDouble(3, producto.getPrecio());
            stat.setInt(4, producto.getCantidad());
            stat.setString(5, producto.getCategoria());
            stat.executeUpdate();
        }
    }

    // Consultar producto por ID (corregido para incluir ID)
    public Producto consultarProducto(int id) throws SQLException {
        String sql = "SELECT * FROM producto WHERE id = ?";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement stat = con.prepareStatement(sql)){
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            if(rs.next()){
                Producto p = new Producto(
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad"),
                        rs.getString("categoria")
                );
                p.setId(rs.getInt("id")); // Asignar ID
                return p;
            }
        }
        return null;
    }

    // Actualizar producto (tabla corregida)
    public void actualizarProducto(Producto producto) throws SQLException {
        String sql = "UPDATE producto SET nombre=?, marca=?, precio=?, cantidad=?, categoria=? WHERE id = ?"; // Tabla corregida
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement stat = con.prepareStatement(sql)){
            stat.setString(1, producto.getNombre());
            stat.setString(2, producto.getMarca());
            stat.setDouble(3, producto.getPrecio());
            stat.setInt(4, producto.getCantidad());
            stat.setString(5, producto.getCategoria());
            stat.setInt(6, producto.getId());
            stat.executeUpdate();
        }
    }

    // Eliminar producto (sin cambios)
    public void eliminarProducto(int id) throws SQLException {
        String sql = "DELETE FROM producto WHERE id = ?";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement stat = con.prepareStatement(sql)){
            stat.setInt(1, id);
            stat.executeUpdate();
        }
    }

    // LISTAR TODOS (completamente corregido)
    public List<Producto> listarTodos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try(Connection con = ConexionDB.getConnection();
            PreparedStatement stat = con.prepareStatement(sql)){
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                Producto p = new Producto(
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad"),
                        rs.getString("categoria")
                );
                p.setId(rs.getInt("id")); // Asignar ID
                productos.add(p);
            }
        }
        return productos;
    }
}