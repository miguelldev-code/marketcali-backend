<%@ page import="miguel.marketcali.model.Producto" %>
<%
    Producto p = (Producto) request.getAttribute("producto");
    boolean isEdit = (p != null && p.getId() > 0);
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= isEdit ? "Editar" : "Nuevo" %> Producto</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}./css/styles.css">
</head>
<body>
<jsp:include page="../jsp/menuPrincipal.jsp" />

<h2><%= isEdit ? "Editar Producto" : "Agregar Nuevo Producto" %></h2>

<form action="productos" method="post">
    <% if(isEdit) { %>
    <input type="hidden" name="id" value="<%= p.getId() %>">
    <% } %>

    <label>Nombre:</label>
    <input type="text" name="nombre" value="<%= isEdit ? p.getNombre() : "" %>" required>

    <label>Marca:</label>
    <input type="text" name="marca" value="<%= isEdit ? p.getMarca() : "" %>">

    <label>Precio:</label>
    <input type="number" step="0.01" name="precio" value="<%= isEdit ? p.getPrecio() : "" %>" required>

    <label>Stock:</label>
    <input type="number" name="stock" value="<%= isEdit ? p.getCantidad() : "" %>" required>

    <label>Categoría:</label>
    <select name="categoria">
        <option value="Tecnología" <%= isEdit && "Tecnología".equals(p.getCategoria()) ? "selected" : "" %>>Tecnología</option>
        <option value="Alimentos" <%= isEdit && "Alimentos".equals(p.getCategoria()) ? "selected" : "" %>>Alimentos</option>
        <option value="Ropa" <%= isEdit && "Ropa".equals(p.getCategoria()) ? "selected" : "" %>>Ropa</option>
    </select>

    <button type="submit"><%= isEdit ? "Actualizar" : "Guardar" %></button>
</form>
</body>
</html>