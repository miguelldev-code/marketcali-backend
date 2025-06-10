package miguel.marketcali.servlets;

import miguel.marketcali.dao.ProductoDAO;
import miguel.marketcali.model.Producto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/productos"})
public class ProductoServlet extends HttpServlet {

    private ProductoDAO dao = new ProductoDAO();

    // GET: Listar y formularios
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if("nuevo".equals(action)) {
            request.getRequestDispatcher("/jsp/formularioProducto.jsp").forward(request, response);

        } else if("editar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                Producto p = dao.consultarProducto(id);
                request.setAttribute("producto", p);
                request.getRequestDispatcher("/jsp/formularioProducto.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else {
            // Llamada sin parámetro
            try {
                request.setAttribute("productos", dao.listarTodos());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.getRequestDispatcher("/jsp/listaProductos.jsp").forward(request, response);
        }
    }

    // POST: Procesar formularios
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String marca = request.getParameter("marca");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String categoria = request.getParameter("categoria");

        Producto p = new Producto(nombre, marca, precio, stock, categoria);

        if(id != null && !id.isEmpty()) {

            // Actualización
            p.setId(Integer.parseInt(id));
            try {
                dao.actualizarProducto(p);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {

            // Inserción
            try {
                dao.insertarProducto(p);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        response.sendRedirect("productos"); // Redirigir a lista
    }
}
