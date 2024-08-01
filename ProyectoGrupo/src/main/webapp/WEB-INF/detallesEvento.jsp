<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalles Evento</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/inicio">
                <img src="/images/minilogo.png" alt="Bootstrap" width="50" height="">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/inicio">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Top Voluntarios</a>
                    </li>
                </ul>
                <c:if test="${id_organizacion == null && id_usuario == null}">
	                <a class="navbar-brand" href="/registro/organizacion">Quiero ser Empresa aliada</a>
                </c:if>
                <c:if test="${id_usuario != null || id_organizacion != null}">
	                <form action="/logout" method="post">
	                	<button type="submit" class="navbar-brand btn btn-link">Cerrar Sesión</button>
					</form>
                </c:if>
            </div>
        </div>
    </nav>
	<br>

<div class="container">
    <h2 class="my-4">Detalles del Evento</h2>
    <div class="row">
        <div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-2">
            <div class="event-card p-3 border rounded">
                <h5>${evento.nombre}</h5>
                <p>${evento.descripcion}</p>
                <p><strong>Ciudad:</strong> ${evento.ciudad}</p>
                <p><strong>Categoría:</strong> ${evento.categoria.categoria}</p>
			    <p><strong>Fecha:</strong> ${evento.getFechaHoraFormateada()}</p>
                <p><strong>Voluntarios:</strong> ${evento.voluntariosRegistrados}/${evento.limiteVoluntarios}</p>
                <c:forEach var="organizacion" items="${organizaciones}">
                	<c:if test="${not empty organizacion.webLink}">
					    <p><strong>Web:</strong> <a href="${organizacion.webLink}" target="_blank">${organizacion.webLink}</a></p>
					</c:if>
                </c:forEach>
            </div>
        </div>
        <div class="col-12 col-sm-12 col-md-4 col-lg-5 mb-2">
            <div class="volunteers-card p-3 border rounded">
                <h5>Lista de Voluntarios Registrados</h5>
                <ul class="list-unstyled">
                    <c:forEach var="usuario" items="${evento.usuarios}">
                        <li>${usuario.nombre} ${usuario.apellido}</li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
	
		<footer class="bg-dark text-white mt-5">
        <div class="container py-4">
            <div class="row">
                <div class="col-md-4">
                    <h5>Sobre Nosotros</h5>
                    <p>Somos NEXO, una plataforma que conecta organizaciones con personas dispuestas a ayudar a través del voluntariado.</p>
                </div>
                <div class="col-md-4">
                    <h5>Enlaces</h5>
                    <ul class="list-unstyled">
                        <li><a href="#" class="text-white">Inicio</a></li>
                        <li><a href="#" class="text-white">Contacto</a></li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <h5>Contacto</h5>
                    <address>
                        <strong>NEXO Inc.</strong><br>
                        1234 Calle Principal<br>
                        Ciudad, Estado 56789<br>
                        <span>Tel:</span> (123) 456-7890
                    </address>
                </div>
            </div>
        </div>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>