<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card" id="card">
                    <div class="card-header text-center">
                        <h2>Inicio de Sesión</h2>
                    </div>
                    <div class="card-body">
                        <form:form action="/procesa/login" method="POST" modelAttribute="loginUsuario">
                            <div class="form-group">
                                <form:label path="usuarioCorreo">Correo:</form:label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                    </div>
                                    <form:input path="usuarioCorreo" type="email" class="form-control" required="true" />
                                </div>
                                <form:errors path="usuarioCorreo" class="alert alert-danger"/>
                            </div>
                            <div class="form-group">
                                <form:label path="usuarioContraseña">Contraseña:</form:label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                                    </div>
                                    <form:input path="usuarioContraseña" type="password" class="form-control" required="true" id="password"/>
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-secondary" type="button" id="togglePassword"><i class="fas fa-eye"></i></button>
                                    </div>
                                </div>
                                <form:errors path="usuarioContraseña" class="alert alert-danger"/>
                            </div>
                            <div class="form-group">
                                <form:label path="tipoUsuario">Tipo de Usuario:</form:label>
                                <form:select path="tipoUsuario" class="form-control">
                                    <option value="VOLUNTARIO">Voluntario</option>
                                    <option value="ORGANIZACION">Organización</option>
                                </form:select>
                                <form:errors path="tipoUsuario" class="alert alert-danger"/>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Login</button>
                            <div class="text-center mt-3">
                                <a href="/registrar" class="btn btn-secondary">¿No tienes cuenta? Regístrate</a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
    <script src="<c:url value='/js/app.js' />"></script>
</body>
</html>
