<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Acerca de Nosotros</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="shortcut icon" href="/images/minilogo.png" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/aos@2.3.4/dist/aos.css" rel="stylesheet">
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/estilos.css">
        <style>
            body {
                background-color: #f4f4f4;
                font-family: Arial, sans-serif;
            }
            .about-header {
                background: url('/images/about-bg.jpg') no-repeat center center;
                background-size: cover;
                color: white;
                padding: 60px 0;
                text-align: center;
                border-bottom: 5px solid #009688;
            }
            .about-header h1 {
                font-size: 3em;
                margin-bottom: 0;
            }
            .about-content {
                padding: 40px 20px;
            }
            .about-content h2 {
                color: #009688;
                margin-bottom: 20px;
                text-align: center;
            }
            .about-content .card {
                border: none;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .about-content .card img {
                width: 100%;
                height: auto;
            }
            .about-content .card-body {
                padding: 20px;
            }
            .about-content .card-title {
                font-size: 1.5em;
                margin-bottom: 10px;
            }
            .about-content .card-text {
                font-size: 1em;
                color: #333;
            }
            .about-content .btn-primary {
                background-color: #009688;
                border: none;
            }
            .about-content .btn-primary:hover {
                background-color: #00796b;
            }
            footer {
                background-color: #009688;
                color: white;
                text-align: center;
                padding: 20px 0;
            }
            .hero h1 {
                font-size: 3em;
                margin: 135px auto;
            }
        </style>
    </head>
    <body>
        <header class="hero">
            <nav class="nav container">
                <a class="navbar-brand" href="/inicio">
                    <img src="/images/minilogo.png" alt="Bootstrap" width="50" height="">
                </a>
                <div class="nav__logo">
                    <h2 class="nav__title">Nexo</h2>
                </div>
                <ul class="nav__link nav__link--menu">
                    <li class="nav__items">
                        <a href="/inicio" class="nav__links">Inicio</a>
                    </li>
                    <li class="nav__items">
                        <a href="/nosotros" class="nav__links">Acerca de nosotros</a>
                    </li>
                    <li class="nav__items">
                        <a href="/contacto" class="nav__links">Contacto</a>
                    </li>
                    <li class="nav__items">
                        <a href="/topvoluntarios" class="nav__links">Top Voluntarios</a>
                    </li>
                    <c:if test="${id_usuario == null && id_organizacion == null}">
                        <li class="nav__items">
                            <a href="/login" class="nav__links">Login</a>
                        </li>
                        <li class="nav__items">
                            <a href="/registro/usuario" class="nav__links">Registro</a>
                        </li>
                        <li class="nav__items">
                            <a href="/registro/organizacion" class="nav__links">Quiero ser empresa aliada</a>
                        </li>
                    </c:if>
                    <c:if test="${id_usuario != null}">
                        <li class="nav__items">
                            <a href="/voluntario" class="nav__links">Perfil</a>
                        </li>
                    </c:if>
                    <c:if test="${id_organizacion != null}">
                        <li class="nav__items">
                            <a href="/organizacion" class="nav__links">Perfil</a>
                        </li>
                    </c:if>
                    <c:if test="${id_usuario != null || id_organizacion != null}">
                        <li class="nav__items">
                            <form action="/logout" method="post" style="display:inline;">
                                <button type="submit" class="nav__links btn btn-link" style="text-decoration:none; padding:0;">Cerrar Sesión</button>
                            </form>
                        </li>
                    </c:if>
                    <img src="/images/close.svg" class="nav__close">
                </ul>
                <div class="nav__menu">
                    <img src="/images/menu.svg" class="nav__img">
                </div>
            </nav>
            <h1>Acerca de Nosotros</h1>
        </header>

        <main>
            <section class="about-content container">
                <h2>¿Quiénes somos?</h2>
                <div class="row">
                    <div class="col-lg-4">
                        <div class="card" data-aos="fade-up">
                            <img src="/images/volun.jpg" class="card-img-top" alt="Misión">
                            <div class="card-body">
                                <h5 class="card-title">Nuestra Misión</h5>
                                <p class="card-text">Nuestra misión es conectar a los voluntarios con organizaciones que necesitan apoyo para mejorar nuestras comunidades y el medio ambiente.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="card" data-aos="fade-up" data-aos-delay="200">
                            <img src="/images/bebe.jpg" class="card-img-top" alt="Visión">
                            <div class="card-body">
                                <h5 class="card-title">Nuestra Visión</h5>
                                <p class="card-text">Vislumbramos un mundo donde cada individuo tenga la oportunidad de contribuir al bienestar común, creando un impacto positivo en la sociedad.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="card" data-aos="fade-up" data-aos-delay="400">
                            <img src="/images/basura.jpg" class="card-img-top" alt="Valores">
                            <div class="card-body">
                                <h5 class="card-title">Nuestros Valores</h5>
                                <p class="card-text">Nos basamos en valores de solidaridad, compromiso, y respeto. Creemos en la transparencia y la colaboración para alcanzar nuestros objetivos.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="text-center mt-4">
                    <a href="/registro/usuario" class="btn btn-primary">Únete a nosotros</a>
                </div>
            </section>
        </main>

        <footer class="footer">
            <section class="footer__container container py-3">
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
                        <li class="nav__items">
                            <a href="/registro/usuario" class="nav__links">Registrarse</a>
                        </li>
                        <li class="nav__items">
                            <a href="/login" class="nav__links">Login</a>
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

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-8B0KM2js0K8LJGr3rmyEbrc4A8B93lEDFLB4uBtPzOFL9zPrZ4bEn7bU6C39k+U3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/aos@2.3.4/dist/aos.js"></script>
        <script src="/js/main.js"></script>
        <script>
            AOS.init();
        </script>
    </body>
</html>