package miguel.marketcali.servlets;

import miguel.marketcali.dao.ProductoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EliminarProductoServlet", urlPatterns = {"/eliminarProducto"})
public class EliminarProductoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            new ProductoDAO().eliminarProducto(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("productos");
    }
}