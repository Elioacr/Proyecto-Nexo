<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Top Voluntarios</title>
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
                        <a class="nav-link" href="/topvoluntarios">Top Voluntarios</a>
                    </li>
	                <c:if test="${id_usuario == null && id_organizacion == null}">
	                    <li class="nav-item">
	                        <a href="/login" class="nav-link">Login</a>
	                    </li>
	                    <li class="nav-item">
	                        <a href="/registro/usuario" class="nav-link">Registro</a>
	                    </li>
	                </c:if>
	                <c:if test="${id_usuario != null}">
	                    <li class="nav-item">
	                        <a href="/voluntario" class="nav-link">Perfil</a>
	                    </li>
	                </c:if>
	                <c:if test="${id_organizacion != null}">
	                    <li class="nav-item">
	                        <a href="/organizacion" class="nav-link">Perfil</a>
	                    </li>
	                </c:if>
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
	
	<main class="container">
		<div class="row">
		    <h2 class="my-4 col-12 col-sm-9">Top Voluntarios</h2>
		    <div class="ms-auto col-12 col-sm-3">
				<select class="form-select my-4" id="selectFiltro">
				    <option value="Global" selected>Global</option>
				    <c:forEach var="categoria" items="${categorias}">
						<option value="${categoria.categoria}">${categoria.categoria}</option>
				    </c:forEach>
				</select>
		    </div>
		</div>
		<c:if test="${topVoluntarios.size() == 0}">
			<p>No hay voluntarios...</p>
		</c:if>
		<c:if test="${topVoluntarios.size() > 0}">
			<ol class="list-group list-group-numbered">
				<c:forEach var="voluntario" items="${topVoluntarios}">
					<c:if test="${categoria != null}">
					  	<li class="list-group-item">${voluntario.nombre} <span class=" ms-auto text-end">${voluntario.obtenerAsistenciasConfirmadas(categoria)}</span></li>
					</c:if>
					<c:if test="${categoria == null}">
					  	<li class="list-group-item">${voluntario.nombre} <span class=" ms-auto text-end">${voluntario.obtenerAsistenciasConfirmadas()}</span></li>
					</c:if>
				</c:forEach>
			</ol>
		</c:if>
	</main>
	
	<footer class="bg-dark text-white mt-5">
        <div class="container py-4">
            <div class="row">
                <div class="col-md-4">
                    <h5>Sobre Nosotros</h5>
                    <p class="text-light">Somos NEXO, una plataforma que conecta organizaciones con personas dispuestas a ayudar a través del voluntariado.</p>
                </div>
                <div class="col-md-4">
                    <h5>Enlaces</h5>
                    <ul class="list-unstyled">
                        <li><a href="/" class="text-white">Inicio</a></li>
                        <li><a href="#" class="text-white">Contacto</a></li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <h5>Contacto</h5>
                    <address>
                        <strong class="text-light">NEXO Inc.</strong><br>
                        1234 Calle Principal<br>
                        Ciudad, Estado 56789<br>
                        <span>Tel:</span> (123) 456-7890
                    </address>
                </div>
            </div>
        </div>
    </footer>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/js/topVoluntarios.js"></script>
</body>
</html>