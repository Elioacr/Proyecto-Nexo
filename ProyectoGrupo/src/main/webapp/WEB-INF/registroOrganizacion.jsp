<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<!DOCTYPE html>
			<html lang="es">

			<head>
				<meta charset="UTF-8">
				<meta name="viewport" content="width=device-width, initial-scale=1.0">
				<link rel="shortcut icon" href="/images/minilogo.png" type="image/x-icon">
				<title>Registro de Organización</title>
				<link rel="stylesheet" href="/css/style.css">
				<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
				<link rel="stylesheet"
					href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
			</head>

			<body>
				<div class="container mt-5">
					<div class="row justify-content-center">
						<div class="col-md-6">
							<div class="card" id="card">
								<div class="card-header text-center">
									<h2>Registro Organización</h2>
								</div>
								<div class="card-body">
									<form:form action="/registrar/organizacion" method="POST"
										modelAttribute="organizacion">
										<div class="form-group">
											<form:label path="nombreOrganizacion">Nombre:</form:label>
											<div class="input-group mb-4">
												<div class="input-group-prepend">
													<span class="input-group-text"><i class="fas fa-user"></i></span>
												</div>
												<form:input path="nombreOrganizacion" type="text" class="form-control"
													required="true" />
											</div>
											<div>
												<form:errors path="nombreOrganizacion" class="alert alert-danger" />
											</div>
										</div>
										<div class="form-group">
											<form:label path="correo">Correo:</form:label>
											<div class="input-group mb-4">
												<div class="input-group-prepend">
													<span class="input-group-text"><i
															class="fas fa-envelope"></i></span>
												</div>
												<form:input path="correo" type="email" class="form-control"
													required="true" />
											</div>
											<div>
												<form:errors path="correo" class="alert alert-danger" />
											</div>
										</div>
										<div class="form-group">
											<form:label path="rut">Rut:</form:label>
											<div class="input-group mb-4">
												<div class="input-group-prepend">
													<span class="input-group-text"><i class="fas fa-id-card"></i></span>
												</div>
												<form:input path="rut" class="form-control" placeholder="XX.XXX.XXX-X"
													required="true" />
											</div>
											<div>
												<form:errors path="rut" class="alert alert-danger" />
											</div>
										</div>
										<div class="form-group">
											<form:label path="telefono">Teléfono:</form:label>
											<div class="input-group mb-4">
												<div class="input-group-prepend">
													<span class="input-group-text"><i class="fas fa-phone"></i></span>
												</div>
												<form:input path="telefono" type="number" min="1" max="999999999"
													class="form-control" required="true" />
											</div>
											<div>
												<form:errors path="telefono" class="alert alert-danger" />
											</div>
										</div>
										<div class="form-group">
										    <form:label path="webLink">Página web o Red Social:</form:label>
										    <div class="input-group mb-4">
										        <div class="input-group-prepend">
										            <span class="input-group-text"><i class="fas fa-globe"></i></span>
										        </div>
										        <form:input path="webLink" type="url" class="form-control" placeholder="https://example.com" />
										    </div>
										    <div>
										        <form:errors path="webLink" class="alert alert-danger" />
										    </div>
										</div>
										<div class="form-group">
											<form:label path="contraseña">Contraseña:</form:label>
											<div class="input-group mb-4">
												<div class="input-group-prepend">
													<span class="input-group-text"><i class="fas fa-lock"></i></span>
												</div>
												<form:input path="contraseña" type="password" class="form-control"
													required="true" id="password" />
												<div class="input-group-append">
													<button class="btn btn-outline-secondary" type="button"
														id="togglePassword"><i class="fas fa-eye"></i></button>
												</div>
											</div>
											<div>
												<form:errors path="contraseña" class="alert alert-danger" />
											</div>
										</div>
										<div class="form-group">
											<form:label path="confirmarContraseña">Confirmar contraseña:</form:label>
											<div class="input-group mb-4">
												<div class="input-group-prepend">
													<span class="input-group-text"><i class="fas fa-lock"></i></span>
												</div>
												<form:input path="confirmarContraseña" type="password"
													class="form-control" required="true" id="confirm-password" />
												<div class="input-group-append">
													<button class="btn btn-outline-secondary" type="button"
														id="toggleConfirmPassword"><i class="fas fa-eye"></i></button>
												</div>
											</div>
											<div>
												<form:errors path="confirmarContraseña" class="alert alert-danger" />
											</div>
										</div>
										<button type="submit" class="btn btn-primary btn-block">Registrar</button>
										<div class="text-center mt-3">
											<a href="/login" class="btn btn-secondary">¿Ya tienes cuenta?</a>
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
			</body>

			</html>