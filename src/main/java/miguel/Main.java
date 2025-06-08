package miguel;
import miguel.marketcali.dao.ProductoDAO;
import miguel.marketcali.model.Producto;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        ProductoDAO dao = new ProductoDAO();

        // Crear producto
        Producto nuevo = new Producto("Laptop", "Dell", 2500000, 10, "Tecnología");
        dao.insertarProducto(nuevo);

        // Consultar producto
        Producto consultado = dao.consultarProducto(1);
        System.out.println("Producto: " + consultado.getNombre());

        // Actualizar producto
        consultado.setPrecio(2300000);
        dao.actualizarProducto(consultado);

        // Eliminar producto
        dao.eliminarProducto(1);

    }
}