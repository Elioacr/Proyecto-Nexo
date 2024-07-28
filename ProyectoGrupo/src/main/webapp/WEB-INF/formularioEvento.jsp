<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nuevo Evento</title>
</head>
<body>
	<div class="container">
	
		<form:form action="/eventos/nuevo" method="Post" modelAttribute="evento">
			<div class="mt-4">
				<form:label class="form-label" path="nombre">
					Nombre:
				</form:label>
				<form:input class="form-control mb-4" path="nombre" name="nombre"/>
				<div>
					<form:errors class="alert alert-danger" path="nombre"/>
				</div>
			</div>
			<div class="mt-4">
				<form:label class="form-label" path="ciudad">
					Ciudad:
				</form:label>
				<form:input class="form-control mb-4" path="ciudad" name="ciudad"/>
				<div>
					<form:errors class="alert alert-danger" path="ciudad"/>
				</div>
			</div>
			<div class="mt-4">
				<form:label class="form-label" path="ubicacion">
					Ubicacion:
				</form:label>
				<form:input class="form-control mb-4" path="ubicacion" name="ubicacion"/>
				<div>
					<form:errors class="alert alert-danger" path="ubicacion"/>
				</div>
			</div>
			<div class="mt-4">
				<form:label class="form-label" path="descripcion">
					Descripcion:
				</form:label>
				<form:textarea path="descripcion" class="form-control border border-black" style="height: 100px"></form:textarea>
				<div>
					<form:errors class="alert alert-danger" path="descripcion"/>
				</div>
			</div>
			<div class="mt-4">
				<form:label class="form-label" path="fechaHora">
					Fecha y Hora:
				</form:label>
				<form:input type="datetime-local" class="form-control mb-4" path="fechaHora" name="fechaHora"/>
				<div>
					<form:errors class="alert alert-danger" path="fechaHora"/>
				</div>
			</div>
			<div class="mt-4">
				<form:label class="form-label" path="categoria">
					Categoria:
				</form:label>
				<form:select path="categoria" name="categoria">
					<form:option value="" selected="true"></form:option>
					<c:forEach var="categoria" items="${categorias}">
						<form:option value="${categoria.id}">${categoria.categoria}</form:option>
					</c:forEach>
				</form:select>
				<div>
					<form:errors class="alert alert-danger" path="categoria"/>
				</div>
			</div>
			<button class="btn btn-success mt-4">
				Crear Evento
			</button>
		</form:form>
	</div>
</body>
</html>