<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Organizacion</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link rel="stylesheet" href="/css/organizacion.css">
		<link rel="icon" type="image/png" href="/images/minilogo.png"  type="image/x-icon">
		<link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/estilos.css">
		<link rel="stylesheet" href="/css/footer.css">
	</head>
	<body>
		<div class="contenedor">
			<header>
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
		    	<c:choose>
					<c:when test="${!organizacion.verificado}">
						<div class="noVerificado">
							<h5>${nombreOrganizacion} en un lapso de 24 horas tus Eventos podran ser vistos por los Voluntarios</h5>
						</div>
				    </c:when>
				     <c:otherwise>
					     <div class="verificado">
					     	<h6>Organización Verificada</h6>
					     </div>
				     </c:otherwise>
			    </c:choose>
			</header>
			
			<main class="container-custom mt-2">
				<h1>${nombreOrganizacion} - Lista de Eventos Creados </h1>
				<div class="scrollable-container">
					<div class="row">
						<c:forEach var="evento" items="${eventos}">
							<div class="col-sm-6 mb-3">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">${evento.nombre}</h4>
										<p class="card-text">
											<c:choose>
	                                        <c:when test="${fn:length(evento.descripcion) > 40}">
	                                            ${fn:substring(evento.descripcion, 0, 40)}...
	                                        </c:when>
	                                        <c:otherwise>
	                                            ${evento.descripcion}
	                                        </c:otherwise>
	                                    </c:choose>
										 </p>
										<p class="card-text"><strong>Ciudad:</strong>${evento.ciudad}</p>
										<p class="card-text"><strong>Categoría:</strong>${evento.categoria.categoria}</p>
										<a href="/eventos/${evento.id}" class="btn btn-primary">Ver Detalles</a>
									</div>
								</div>
							</div>
						</c:forEach>
					
					</div>
				</div>
				<a href="/eventos/nuevo" class="btn btn-primary mt-3">Crear Evento</a>
			</main>
			
		   	<footer class="footer">
	            <section class="footer__container container">
	                <nav class="nav nav--footer">
	                    <h2 class="footer__title">Nexo</h2>
	                    <ul class="nav__link nav__link--footer">
	                        <li class="nav__items">
	                            <a href="/" class="nav__links">Inicio</a>
	                        </li>
	                        <li class="nav__items">
	                            <a href="#" class="nav__links">Acerca de nosotros</a>
	                        </li>
	                        <li class="nav__items">
	                            <a href="#" class="nav__links">Contacto</a>
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
		</div>
		
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>