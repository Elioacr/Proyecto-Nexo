<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>Detalles Evento</title>
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
				integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
				crossorigin="anonymous">
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
			<link rel="stylesheet" href="/css/detalleEvento.css">
			<link rel="stylesheet" href="/css/normalize.css">
        	<link rel="stylesheet" href="/css/estilos.css">
		</head>

		<body>
			<nav class="navbar navbar-expand-lg bg-body-tertiary">
				<div class="container-fluid">
					<a class="navbar-brand" href="/inicio">
						<img src="/images/minilogo.png" alt="Bootstrap" width="50" height="">
					</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
						aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarText">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item">
								<a class="nav-link active" aria-current="page" href="/inicio">Inicio</a>
							</li>
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
							<li class="nav-item">
								<a class="nav-link" href="/topvoluntarios">Top Voluntarios</a>
							</li>
						</ul>
						<c:if test="${id_usuario != null || id_organizacion != null}">
							<form action="/logout" method="post">
								<button type="submit" class="navbar-brand btn btn-link">Cerrar Sesión</button>
							</form>
						</c:if>
					</div>
				</div>
			</nav>

			<div class="container">
				<div class="row">
					<h2 class="my-0 col12 col-sm-9">Detalles del Evento</h2>
					<div class="ms-auto col-12 col-sm-3 text-center">
						<form action="/eventos/participar/${evento.id}" method="post">
							<c:if
								test="${evento.getVoluntariosRegistrados() lt evento.limiteVoluntarios && id_organizacion == null && usuario != null && !evento.usuarios.contains(usuario)}">
								<button type="submit" class="btn btn-outline-secondary my-4">Participar</button>
							</c:if>
						</form>
					</div>
				</div>
				<div class="row">
					<div class="col-12 col-sm-12 col-md-6 col-lg-6 mb-2">
						<div class="event-card p-3 border rounded">
							<h5>${evento.nombre}</h5>
							<p>${evento.descripcion}</p>
							<p><strong>Organización:</strong> ${evento.organizacion.nombreOrganizacion}</p>
							<c:if test="${not empty evento.organizacion.webLink}">
								<p><strong>Web:</strong> <a href="${evento.organizacion.webLink}" target="_blank">${evento.organizacion.webLink}</a></p>
							</c:if>
							<p><strong>Ciudad:</strong> ${evento.ciudad}</p>
							<p><strong>Ubicación:</strong> ${evento.ubicacion}</p>
							<p><strong>Categoría:</strong> ${evento.categoria.categoria}</p>
							<p><strong>Fecha:</strong> ${evento.getFechaHoraFormateada()}</p>
							<p><strong>Fecha Termino:</strong> ${evento.getFechaTerminoFormateada()}</p>
							<p><strong>Voluntarios:</strong> ${evento.voluntariosRegistrados}/${evento.limiteVoluntarios}</p>
						</div>
					</div>
					<div class="col-12 col-sm-12 col-md-4 col-lg-5 mb-2">
						<div class="volunteers-card p-3 border rounded">
							<h5>Lista de Voluntarios Registrados</h5>
							<c:if test="${evento.usuarios.size() == 0}">
								<p>Aun no hay voluntarios registrados...</p>
							</c:if>
							<c:if test="${evento.usuarios.size() > 0 }">
								<table class="table">
									<thead>
										<tr>
											<th>Nombre</th>
											<th>Apellido</th>
											<c:if test="${id_organizacion != null}">
												<th>Detalles</th>
											</c:if>
											<c:if test="${evento.estaActivo() && id_organizacion != null}">
												<th>Asistencia</th>
											</c:if>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="voluntario" items="${voluntarios}">
											<tr>
												<td>${voluntario.nombre}</td>
												<td>${voluntario.apellido}</td>
												<c:if test="${id_organizacion != null}">
													<td>
														<div class="eye-icon" data-usuario-id="${voluntario.id}">
															<i class="fas fa-eye"></i>
															<div class="user-details">
																<p>Correo: ${voluntario.correo}</p>
																<p>Teléfono: ${voluntario.telefono}</p>
																<p>Ciudad: ${voluntario.ciudad}</p>
																<p>Edad: ${voluntario.edad}</p>
															</div>
														</div>
													</td>
												</c:if>
												<c:if test="${evento.estaActivo() && !voluntario.asistenciaConfirmada(evento) && id_organizacion != null}">
													<td>
														<a href="/eventos/${evento.id}/confirmarAsistencia/${voluntario.id}" class="d-block col-auto ms-auto">Confirmar</a>
													</td>
												</c:if>
												<c:if test="${evento.estaActivo() && voluntario.asistenciaConfirmada(evento) && id_organizacion != null}">
													<td>
														<a href="/eventos/${evento.id}/negarAsistencia/${voluntario.id}" class="d-block col-auto ms-auto">Negar</a>
													</td>
												</c:if>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
						</div>
					</div>
				</div>
				<c:if test="${id_organizacion != null && id_organizacion == evento.organizacion.id}">
					<form action="/eventos/eliminar/${evento.id}" method="Post">
						<input type="hidden" name="_method" value="Delete">
						<button class="btn btn-danger mt-3">Eliminar Evento</button>
					</form>
				</c:if>
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
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
		</body>

		</html>