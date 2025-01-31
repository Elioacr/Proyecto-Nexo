<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Voluntario - Eventos Disponibles</title>
	<link rel="shortcut icon" href="/images/minilogo.png" type="image/x-icon">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js"></script>
	<link rel="stylesheet" href="/css/voluntario.css">
	<link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/css/estilos.css">
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
                    <c:if test="${usuario.esAdministrador == true}">
						<li class="nav-item">
			              	<a class="nav-link" href="/admin/organizaciones">Usuario Administrador</a>
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
    
    <main class="container py-2">
        <section class="row">
		    <h2 class="my-4 col-12 col-sm-6 text-light">Eventos Disponibles</h2>
		    <div class="ms-auto col-12 col-sm-3">
				<select class="form-select my-4" id="selectFiltroOrganizacion">
				    <option value="Todas las Organizaciones" selected>Todas las Organizaciones</option>
				    <c:forEach var="organizacion" items="${organizaciones}">
					    <c:if test="${organizacion.verificado}">
							<option value="${organizacion.id}">${organizacion.nombreOrganizacion}</option>
					    </c:if>
				    </c:forEach>
				</select>
		    </div>
		    <div class="ms-auto col-12 col-sm-3">
				<select class="form-select my-4" id="selectFiltroCategoria">
				    <option value="Todas las Categorias" selected>Todas las Categorias</option>
				    <c:forEach var="categoria" items="${categorias}">
						<option value="${categoria.categoria}">${categoria.categoria}</option>
				    </c:forEach>
				</select>
		    </div>
		</section>
        <section class="row eventosDisponibles">
	    	<c:choose>
	        	<c:when test="${eventos == null || eventos.size() == 0}">
	            	<p>No hay eventos...</p>
	        	</c:when>
		        <c:otherwise>
		            <c:forEach var="evento" items="${eventos}">
		                <c:if test="${!eventosUsuario.contains(evento) && evento.organizacion.verificado}">
		                    <div class="col-12 col-sm-6 col-md-4 mb-2">
		                        <div class="event-card p-3 border rounded h-100">
		                            <h5><a href="/eventos/${evento.id}" class="text-decoration-none">${evento.nombre}</a></h5>
		                            <p>${evento.descripcion}</p>
		                            <p><strong>Ciudad:</strong> ${evento.ciudad}</p>
		                            <p><strong>Categoría:</strong> ${evento.categoria.categoria}</p>
		                            <p><strong>Fecha:</strong> ${evento.getFechaHoraFormateada()}</p>
		                            <p><strong>Voluntarios:</strong> ${evento.getVoluntariosRegistrados()}/${evento.limiteVoluntarios}</p>
		                            <form action="/eventos/participar/${evento.id}" method="post">
		                                <c:if test="${evento.getVoluntariosRegistrados() lt evento.limiteVoluntarios}">
		                                    <button type="submit" class="btn btn-participar">Participar</button>
		                                </c:if>
		                                <c:if test="${evento.getVoluntariosRegistrados() ge evento.limiteVoluntarios}">
		                                    <p>El máximo de voluntarios ha sido alcanzado.</p>
		                                </c:if>
		                            </form>
		                        </div>
		                    </div>
		                </c:if>
		            </c:forEach>
	        	</c:otherwise>
	    	</c:choose>
		</section>
        <h2 class="my-4 text-light">Eventos a Participar</h2>
	    <div class="calendar-container mt-5 list-group-item event-card">
	        <div id="calendar"></div>
	    </div>
    </main>


    <footer class="footer">
        <section class="footer__container container py-2">
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

    <script>
    var eventos = [];
    <c:forEach var="evento" items="${eventosUsuario}">
        eventos.push({
            id: '${evento.id}',
            title: '${evento.nombre}',
            start: '${evento.fechaHora}',
            url: '/eventos/${evento.id}',
            hora: '${evento.fechaHora}'  // Incluye la hora en el objeto
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
            navLinks: true,
            editable: true,
            dayMaxEvents: false, // Mostrar todos los eventos
            events: eventos,
            eventContent: function(arg) {
                var title = arg.event.title;
                var id = arg.event.id;
                var hora = arg.event.startStr;  // Obtener la fecha y hora

                var arrayOfDomNodes = [];
                var eventContainer = document.createElement('div');
                var timeElement = document.createElement('span');
                var titleElement = document.createElement('span');
                var button = document.createElement('button');

                // Configurar el contenedor
                eventContainer.style.display = 'flex';
	            eventContainer.style.flexDirection = 'column';
	            eventContainer.style.alignItems = 'center'; // Centrar horizontalmente
	            eventContainer.style.textAlign = 'center'; // Centrar texto
                
                // Configurar la hora
                timeElement.innerText = formatHora(hora); // Usa una función para formatear la hora
                timeElement.style.marginBottom = '5px';
                timeElement.classList.add('text-muted');
                
                // Configurar el título
                titleElement.innerHTML = title;

                // Configurar el botón
                button.innerText = 'Abandonar';
                button.classList.add('btn', 'btn-participar', 'mt-2');

                button.addEventListener('click', function(event) {
                    event.stopPropagation();
                    event.preventDefault(); // Asegura que no se redirija al detalle
                    if (confirm('¿Desea abandonar este evento?')) {
                        if (id) {
                            var form = document.createElement('form');
                            form.method = 'POST';
                            form.action = '/eventos/quitar/' + id;
                            document.body.appendChild(form);
                            form.submit();
                        } else {
                            alert('No se pudo identificar el evento.');
                        }
                    }
                });

                eventContainer.appendChild(titleElement);
                eventContainer.appendChild(timeElement);
                eventContainer.appendChild(button);

                return { domNodes: [eventContainer] };
            }
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

    // Función para formatear solo la hora
    function formatHora(fechaHora) {
    	 var date = new Date(fechaHora);
    	    var options = {
    	        hour: '2-digit',
    	        minute: '2-digit',
    	        hour12: true // Usa formato de 12 horas
    	    };
        return date.toLocaleTimeString('es-ES', options); // Ajusta el locale según sea necesario
    }

    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/js/voluntario.js"></script>
</body>
</html>
