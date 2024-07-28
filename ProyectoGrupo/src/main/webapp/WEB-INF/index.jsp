<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Voluntariado</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link rel="stylesheet" href="/css/index.css">
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
		<div class="container mt-3">
	        <div class="row">
	            <div class="col-md-12 mb-4">
	                <div class="p-4 border rounded">
	                    <h2>¿Quienes somos?</h2>
	                    <p>Somos NEXO una pagina web que busca que las organizaciones que tienen voluntariados 	
	                       tengan más visibilidad para las personas altruistas que buscan ayudar a comunidades que lo necesite.
	                       Registrate como voluntario y busca voluntariados cercanos a tu ubicacion para ayudar a comunidades que lo necesitan.</p>
	                </div>
	            </div>
	        </div>
	    </div>
		
		<div id="carouselExampleIndicators" class="carousel slide portada">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
			    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
			<div class="carousel-item active">
			    <img src="/images/Designer (1).jpeg" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="/images/Designer (2).jpeg" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="/images/Designer (3).jpeg" class="d-block w-100" alt="...">
			</div>
			</div>
			<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
		    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
			   	<span class="visually-hidden">Previous</span>
		  	</button>
		  	<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
		    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
		    	<span class="visually-hidden">Next</span>
		  	</button>
		</div>
		 <div class="container mt-5">
	        <div class="registro-container">
	            <div>
	                <h2>Registro de Voluntario</h2>
	                <p>Únete a nuestra comunidad de voluntarios y ayuda a marcar la diferencia.</p>
	                <a href="/registro/usuario" class="btn btn-primary">Registrar Voluntario</a>
	            </div>
	            <div>
	                <h2>Registro de Organización</h2>
	                <p>Registra tu organización para colaborar con nosotros y darle más visibilidad a tus voluntariados.</p>
	                <a href="/registro/organizacion" class="btn btn-primary">Registrar Organización</a>
	            </div>
	        </div>
	    </div>
			
			
			<footer class="bg-dark text-white mt-5">
		        <div class="container py-4">
		            <div class="row">
		                <div class="col-md-4">
		                    <h5>Sobre Nosotros</h5>
		                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
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
		                        <strong>Empresa, Inc.</strong><br>
		                        1234 Calle Principal<br>
		                        Ciudad, Estado 56789<br>
		                        <span>Tel:</span> (123) 456-7890
		                    </address>
		                </div>
		            </div>
		        </div>
		    </footer>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</body>
</html>