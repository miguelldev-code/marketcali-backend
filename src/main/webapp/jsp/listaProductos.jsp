<%@ page import="java.util.List, miguel.marketcali.model.Producto" %>
<!DOCTYPE html>
<html>
<head>
    <title>Productos - MarketCali</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}./css/styles.css">
</head>
<body>
<jsp:include page="/src/main/webapp/jsp/menuPrincipal.jsp" />

<h2>Gestión de Productos</h2>
<a href="productos?action=nuevo">➕ Nuevo Producto</a>

<table>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Marca</th>
        <th>Precio</th>
        <th>Stock</th>
        <th>Acciones</th>
    </tr>
    <%
        List<Producto> productos = (List<Producto>) request.getAttribute("productos");
        for (Producto p : productos) {
    %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getNombre() %></td>
        <td><%= p.getMarca() %></td>
        <td>$<%= String.format("%,.2f", p.getPrecio()) %></td>
        <td><%= p.getCantidad() %></td>
        <td>
            <a href="productos?action=editar&id=<%= p.getId() %>">✏️</a>
            <a href="#" onclick="confirmarEliminacion(<%= p.getId() %>)">🗑️</a>
        </td>
    </tr>
    <% } %>
</table>

<script>
    function confirmarEliminacion(id) {
        if(confirm("¿Eliminar producto?")) {
            window.location = "eliminarProducto?id=" + id;
        }
    }
</script>
</body>
</html>