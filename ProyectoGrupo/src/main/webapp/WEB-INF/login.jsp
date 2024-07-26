<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="/procesa/login" method="post" modelAttribute="loginUsuario">
    <div>
        <form:label class="form-label" path="usuarioCorreo">Correo:</form:label>
        <form:input class="form-control mb-4" path="usuarioCorreo" type="text" name="usuarioCorreo"/>
        <div>
            <form:errors class="alert alert-danger" path="usuarioCorreo"/>
        </div>
    </div>
    <div>
        <form:label class="form-label" path="usuarioContraseña">Contraseña:</form:label>
        <form:input class="form-control mb-4" path="usuarioContraseña" type="password" name="usuarioContraseña"/>
        <div>
            <form:errors class="alert alert-danger" path="usuarioContraseña"/>
        </div>
    </div>
    <div>
        <form:label class="form-label" path="tipoUsuario">Tipo de Usuario:</form:label>
        <form:select class="form-control mb-4" path="tipoUsuario" name="tipoUsuario">
            <option value="VOLUNTARIO">Voluntario</option>
            <option value="ORGANIZACION">Organización</option>
        </form:select>
        <div>
            <form:errors class="alert alert-danger" path="tipoUsuario"/>
        </div>
    </div>
    <button class="btn btn-success mt-4">Login</button>
</form:form>
</body>
</html>