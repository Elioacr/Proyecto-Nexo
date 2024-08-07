<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/administrador.css">
</head>
<body>
<h2>Organizaciones Registradas</h2>
    <table>
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Email</th>
                <th>Teléfono</th>
                <th>RUT</th>
                <th>Link Web o Red Social</th>
                <th>Verificado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="organizacion" items="${organizaciones}">
                <tr>
                    <td>${organizacion.nombreOrganizacion}</td>
                    <td>${organizacion.correo}</td>
                    <td>${organizacion.telefono}</td>
                    <td>${organizacion.rut}</td>
                    <td><a href="${organizacion.webLink}" target="_blank">${organizacion.webLink}</a></td>
                    <td>${organizacion.verificado ? "Sí" : "No"}</td>
                    <td>
                        <c:if test="${!organizacion.verificado}">
                            <form action="/admin/organizaciones/${organizacion.id}/verificar" method="post">
                                <button type="submit">Verificar</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="/voluntario" class="cta">Volver</a>  
</body>
</html>