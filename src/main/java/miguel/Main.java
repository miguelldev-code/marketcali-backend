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
        System.out.println("Producto creado");

        // Consultar producto
        Producto consultado = dao.consultarProducto(1);
        if (consultado != null) {
            System.out.println("Producto: " + consultado.getNombre());

            // Actualizar producto
            consultado.setPrecio(2300000);
            dao.actualizarProducto(consultado);
            System.out.println("Producto actualizado");

            // Eliminar producto
            dao.eliminarProducto(consultado.getId());
            System.out.println("Producto eliminado");
        } else {
            System.out.println("Producto no encontrado");
        }
    }
}