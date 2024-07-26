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
<div class="container">
			<div class="row">
				<div class="col-6">
					<h1 class="text-primary mt-4"> Registro Voluntario </h1>
					<form:form action="/registrar/usuario" method="POST" modelAttribute="usuario">
						<div class="mt-4">
							<form:label class="form-label" path="nombre">
								Nombre:
							</form:label>
							<form:input class="form-control mb-4" path="nombre" type="text" name="nombre"/>
							<div>
								<form:errors class="alert alert-danger" path="nombre"/>
							</div>
						</div>
						<div class="mt-4">
							<form:label class="form-label" path="apellido">
								Apellido:
							</form:label>
							<form:input class="form-control mb-4" path="apellido" type="text" name="apellido"/>
							<div>
								<form:errors class="alert alert-danger" path="apellido"/>
							</div>
						</div>
						<div class="mt-4">
							<form:label class="form-label" path="ciudad">
								Ciudad:
							</form:label>
							<form:input class="form-control mb-4" path="ciudad" type="text" name="ciudad"/>
							<div>
								<form:errors class="alert alert-danger" path="ciudad"/>
							</div>
						</div>
						<div class="mt-4">
							<form:label class="form-label" path="correo">
								Correo:
							</form:label>
							<form:input class="form-control mb-4" path="correo" type="text" name="correo"/>
							<div>
								<form:errors class="alert alert-danger" path="correo"/>
							</div>
						</div>
						<div class="mt-4">
							<form:label class="form-label" path="rut">
								Rut:
							</form:label>
							<form:input class="form-control mb-4" path="rut" type="int" name="rut"/>
							<div>
								<form:errors class="alert alert-danger" path="rut"/>
							</div>
						</div>
						<div class="mt-4">
							<form:label class="form-label" path="telefono">
								Telefono:
							</form:label>
							<form:input class="form-control mb-4" path="telefono" type="int" name="telefono"/>
							<div>
								<form:errors class="alert alert-danger" path="telefono"/>
							</div>
						</div>
						<div class="mt-4">
							<form:label class="form-label" path="contraseña">
								Contraseña:
							</form:label>
							<form:input class="form-control mb-4" path="contraseña" type="password" name="contraseña"/>
							<div>
								<form:errors class="alert alert-danger" path="contraseña"/>
							</div>
						</div>
						<div class="mt-4">
							<form:label class="form-label" path="confirmarContraseña">
								Confirmar contraseña:
							</form:label>
							<form:input class="form-control mb-4" path="confirmarContraseña" type="password" name="confirmarContraseña"/>
							<div>
								<form:errors class="alert alert-danger" path="confirmarContraseña"/>
							</div>
						</div>
						<button class="btn btn-primary mt-4">
							Registro
						</button>
					</form:form>
				</div>
			</div>
		</div>
</body>
</html>