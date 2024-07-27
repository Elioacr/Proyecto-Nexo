<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Organización</h1>
	<ul>
		<c:forEach var="evento" items="${eventos}">
			<li><a href="/eventos/${evento.id}">${evento.nombre}</a> ${evento.descripcion} ${evento.ciudad} ${evento.categoria.categoria}</li>
		</c:forEach>
	</ul>
	<a href="/eventos/nuevo">Crear Evento</a>
</body>
</html>