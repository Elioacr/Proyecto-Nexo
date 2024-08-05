<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Contacto</title>
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
            .contact-header {
                background: url('/images/contact-bg.jpg') no-repeat center center;
                background-size: cover;
                color: white;
                height: 50vh;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                text-align: center;
                border-bottom: 5px solid #009688;
                margin: 0;
            }
            .contact-header h1 {
                font-size: 3em;
                margin-bottom: 0;
            }
            .contact-form {
                padding: 40px 20px;
            }
            .contact-form h2 {
                color: #009688;
                margin-bottom: 20px;
                text-align: center;
            }
            .contact-form .form-control {
                border-radius: 8px;
            }
            .contact-form .btn-primary {
                background-color: #009688;
                border: none;
            }
            .contact-form .btn-primary:hover {
                background-color: #00796b;
            }
            .contact-info {
                padding: 40px 20px;
                background-color: #e0f2f1;
            }
            .contact-info h2 {
                color: #009688;
                margin-bottom: 20px;
                text-align: center;
            }
            .contact-info .info-item {
                margin-bottom: 20px;
                text-align: center;
            }
            .contact-info .info-item img {
                width: 24px;
                height: 24px;
                vertical-align: middle;
                margin-right: 10px;
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
            <h1>Contacto</h1>
        </header>

        <main>
            <section class="contact-form container">
                <h2>Envíanos un mensaje</h2>
                <form action="/enviarMensaje" method="post">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" required>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="email" class="form-label">Correo Electrónico</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="mensaje" class="form-label">Mensaje</label>
                        <textarea class="form-control" id="mensaje" name="mensaje" rows="4" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            </section>

            <section class="contact-info container">
                <h2>Información de Contacto</h2>
                <div class="row">
                    <div class="col-md-4 info-item">
                        <p>San Diego 630</p>
                    </div>
                    <div class="col-md-4 info-item">
                        <p>(+56) 9 1234 6789</p>
                    </div>
                    <div class="col-md-4 info-item">
                        <p>nexo.firm@gmail.com</p>
                    </div>
                </div>
                <div class="mt-4">
                    <h2>Encuéntranos</h2>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3328.8505800971325!2d-70.6525881246852!3d-33.45319919765233!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9662c50c3820ec97%3A0xdf741df101247a84!2sSan%20Diego%20630%2C%208330617%20Santiago%2C%20Regi%C3%B3n%20Metropolitana!5e0!3m2!1ses-419!2scl!4v1722891530384!5m2!1ses-419!2scl" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
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
                            <a href="/nosotros" class="nav__links">Acerca de nosotros</a>
                        </li>
                        <li class="nav__items">
                            <a href="/contacto" class="nav__links">Contacto</a>
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
                        <a href="#" class="footer__icons"><img src="/images/instagram.svg" class="footer__img"></a>
                    </div>
                    <p>&copy; 2024 Nexo. Todos los derechos reservados.</p>
                </section>
            </section>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9C6r8xpybh2u2FE2M+8I3h2J9y1bPCWkF7ZV6C4iz82Pmp3PTOp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-oBqDVmMz4fnFO9C6r8xpybh2u2FE2M+8I3h2J9y1bPCWkF7ZV6C4iz82Pmp3PTOp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/aos@2.3.4/dist/aos.js"></script>
        <script>
            AOS.init();
        </script>
    </body>
</html>