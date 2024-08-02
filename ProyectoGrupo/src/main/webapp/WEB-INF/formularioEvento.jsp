<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Evento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/estilos.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Crear Nuevo Evento</h1>
        <form:form action="/eventos/nuevo" method="Post" modelAttribute="evento">
            <div class="form-group mt-4">
                <form:label class="form-label" path="nombre">Nombre:</form:label>
                <form:input class="form-control mb-4" path="nombre" name="nombre" required="true"/>
                <form:errors class="alert alert-danger" path="nombre"/>
            </div>
            <div class="form-group mt-4">
                <form:label class="form-label" path="ciudad">Ciudad:</form:label>
                <form:input class="form-control mb-4" path="ciudad" name="ciudad" required="true"/>
                <form:errors class="alert alert-danger" path="ciudad"/>
            </div>
            <div class="form-group mt-4">
                <form:label class="form-label" path="ubicacion">Ubicación:</form:label>
                <form:input class="form-control mb-4" path="ubicacion" name="ubicacion" required="true"/>
                <form:errors class="alert alert-danger" path="ubicacion"/>
            </div>
            <div class="form-group mt-4">
                <form:label class="form-label" path="descripcion">Descripción:</form:label>
                <form:textarea path="descripcion" class="form-control border border-black" style="height: 100px"></form:textarea>
                <form:errors class="alert alert-danger" path="descripcion"/>
            </div>
            <div class="form-group mt-4">
                <form:label class="form-label" path="fechaHora">Fecha y Hora:</form:label>
                <form:input type="datetime-local" class="form-control mb-4" path="fechaHora" name="fechaHora" required="true"/>
                <form:errors class="alert alert-danger" path="fechaHora"/>
            </div>
            <div class="form-group mt-4">
                <form:label class="form-label" path="fechaTermino">Fecha de Término:</form:label>
                <form:input type="datetime-local" class="form-control mb-4" path="fechaTermino" name="fechaTermino" required="true"/>
                <form:errors class="alert alert-danger" path="fechaTermino"/>
            </div>
            <div class="form-group mt-4">
                <form:label class="form-label" path="limiteVoluntarios">Límite de Voluntarios:</form:label>
                <form:input class="form-control mb-4" path="limiteVoluntarios" name="limiteVoluntarios" required="true"/>
                <form:errors class="alert alert-danger" path="limiteVoluntarios"/>
            </div>
            <div class="form-group mt-4">
                <form:label class="form-label" path="categoria">Categoría:</form:label>
                <form:select path="categoria" name="categoria" class="form-select mb-4">
                    <form:option value="" selected="true"></form:option>
                    <c:forEach var="categoria" items="${categorias}">
                        <form:option value="${categoria.id}">${categoria.categoria}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors class="alert alert-danger" path="categoria"/>
            </div>
            <button class="btn btn-success mt-4">Crear Evento</button>
        </form:form>
    </div>
	<footer class="footer">
		<section class="footer__container container">
			<nav class="nav nav--footer">
				<h2 class="footer__title">Nexo</h2>
				<ul class="nav__link nav__link--footer">
					<li class="nav__items">
						<a href="/" class="nav__links">Inicio</a>
					</li>
					<li class="nav__items">
						<a href="#" class="nav__links">Acerca de</a>
					</li>
					<li class="nav__items">
						<a href="#" class="nav__links">Contacto</a>
					</li>
					<li class="nav__items">
						<a href="/registro/usuario" class="nav__links">Registrarse</a>
					</li>
					<li class="nav__items">
						<a href="/login" class="nav__links">Login</a>
					</li>
				</ul>
			</nav>
			<section class="footer__copy container">
				<div class="footer__social">
					<a href="#" class="footer__icons"><img src="/images/facebook.svg" class="footer__img"></a>
					<a href="#" class="footer__icons"><img src="/images/twitter.svg" class="footer__img"></a>
					<a href="#" class="footer__icons"><img src="/images/youtube.svg" class="footer__img"></a>
				</div>
				<h3 class="footer__copyright">Derechos reservados &copy; Fundación Forge grupo 5 c:</h3>
			</section>
		</section>
	</footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
