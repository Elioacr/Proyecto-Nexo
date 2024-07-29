<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Voluntario - Eventos Disponibles</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js"></script>
<link rel="stylesheet" href="/css/voluntario.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/inicio">
                <img src="/images/minilogo.png" alt="Bootstrap" width="50" height="">
            </a>
            <a class="navbar-brand" href="/login">Ingresar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/inicio">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Top voluntarios</a>
                    </li>
                </ul>
                <a class="navbar-brand" href="/registro/organizacion">Quiero ser Empresa aliada</a>
            </div>
        </div>
    </nav>
	<br>

    <div class="container">
        <h2 class="my-4">Eventos Disponibles</h2>
        <div class="row">
            <c:forEach var="evento" items="${eventosConFechasFormateadas}">
                <div class="col-md-4 mb-4">
                    <div class="event-card p-3 border rounded">
                        <h5><a href="/eventos/${evento.id}" class="text-decoration-none">${evento.nombre}</a></h5>
                        <p>${evento.descripcion}</p>
                        <p><strong>Ciudad:</strong> ${evento.ciudad}</p>
                        <p><strong>Categoría:</strong> ${evento.categoria}</p>
                        <p><strong>Fecha:</strong> ${evento.fechaHora}</p>
                        <form action="/participar/${evento.id}" method="post">
                            <button type="submit" class="btn btn-participar">Participar</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="calendar-container mt-5 list-group-item event-card">
        <div id="calendar"></div>
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

    <script>
    var eventos = [];
    <c:forEach var="evento" items="${eventosUsuario}">
        eventos.push({
            title: '${evento.nombre}',
            start: '${evento.fechaHora}',
            url: '/eventos/${evento.id}'
        });
    </c:forEach>

    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
            themeSystem: 'bootstrap5',
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
            },
            initialDate: new Date().toISOString(),
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            dayMaxEvents: true, // allow "more" link when too many events
            events: eventos
        });

        calendar.setOption('locale', 'es');
        calendar.setOption('firstDay', 1);
        calendar.setOption('buttonText', {
            today: 'Hoy',
            month: 'Mes',
            week: 'Semana',
            day: 'Día',
            list: 'Lista'
        });
        calendar.setOption('allDaySlot', false);
        calendar.render();
    });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>