<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Voluntariado</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="shortcut icon" href="/images/minilogo.png" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/aos@2.3.4/dist/aos.css" rel="stylesheet">
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/estilos.css">
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
            <section class="hero__container container">
                <h1 class="hero__title">Únete a nosotros y empieza tu voluntariado</h1>
                <p class="hero__paragraph">Elíjanos para el cambio</p>
                <a href="/login" class="cta">Comienza ahora</a>
            </section>
        </header>
        

        <main>
            <section class="container about" data-aos="fade-up">
                <h2 class="subtitle">¿Por qué ser voluntario?</h2>
                <p class="about__paragraph">Ser voluntario permite ayudar a los demás, aprender nuevas habilidades, conocer gente, y contribuir positivamente a la comunidad.</p>
                <div class="about__main">
                    <article class="about__icons" data-aos="fade-right">
                        <img src="/images/dog.svg" class="about__icon">
                        <h3 class="about__title">Ayudarás a animales</h3>
                        <p class="about__paragrah">Ser voluntario también permite ayudar a animales necesitados, mejorar su bienestar y contribuir a la protección y conservación de la fauna.</p>
                    </article>
                    <article class="about__icons" data-aos="fade-right">
                        <img src="/images/radiactive.svg" class="about__icon">
                        <h3 class="about__title">Ayudarás a reducir la contaminación</h3>
                        <p class="about__paragrah">El voluntariado puede incluir actividades para reducir la contaminación, como limpiezas de playas y parques, plantación de árboles, y campañas de concienciación ambiental.</p>
                    </article>
                    <article class="about__icons" data-aos="fade-right">
                        <img src="/images/body.svg" class="about__icon">
                        <h3 class="about__title">Ayudarás a las personas más necesitadas</h3>
                        <p class="about__paragrah">Ser voluntario permite ayudar a personas necesitadas proporcionando apoyo, recursos y servicios esenciales, mejorando así su calidad de vida y bienestar.</p>
                    </article>
                </div>
            </section>
        
            <section class="knowledge" data-aos="fade-up">
                <div class="knowledge__container container">
                    <div class="knowledege__texts">
                        <h2 class="subtitle">Sé parte de todos nosotros y ayúdanos a hacer del mundo un lugar mejor</h2>
                        <p class="knowledge__paragraph">Únete como voluntario y contribuye a causas importantes, desde ayudar a personas necesitadas hasta proteger el medio ambiente y los animales. Juntos, podemos marcar la diferencia.</p>
                        <a href="/registro/usuario" class="cta">Regístrate</a>  
                    </div>
                    <div id="knowledgeCarousel" class="carousel slide knowledge__picture" data-bs-ride="carousel">
                        <div class="carousel-indicators">
                            <button type="button" data-bs-target="#knowledgeCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#knowledgeCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#knowledgeCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
                        </div>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="/images/volun.jpg" class="d-block w-100 knowledge__img" alt="Voluntario 1">
                            </div>
                            <div class="carousel-item">
                                <img src="/images/bebe.jpg" class="d-block w-100 knowledge__img" alt="Voluntario 2">
                            </div>
                            <div class="carousel-item">
                                <img src="/images/basura.jpg" class="d-block w-100 knowledge__img" alt="Voluntario 3">
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#knowledgeCarousel" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#knowledgeCarousel" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </section>
        
            <section class="price container" data-aos="fade-up">
                <style></style>
                <h2 class="subtitle">Sé parte de nosotros</h2>
                <div class="price__table">
                    <div class="price__element">
                        <h3 class="price__price">Voluntario</h3>
                        <div class="price__items">
                            <p class="price__features">Podrás unirte al voluntariado que gustes</p>
                            <p class="price__features">Tendrás acceso de usuario</p>
                        </div>
                        <a href="/registro/usuario" class="price__cta">Regístrate</a>
                    </div>
                    <div class="price__element">
                        <h3 class="price__price">Organización</h3>
                        <div class="price__items">
                            <p class="price__features">Podrás crear un voluntariado</p>
                            <p class="price__features">Total confianza</p>
                        </div>
                        <a href="/registro/organizacion" class="price__cta">Registra tu organización</a>
                    </div>
                </div>
            </section>
        
            <section class="questions container" data-aos="fade-up">
                <h2 class="subtitle">Preguntas frecuentes</h2>
                <section class="questions__container">
                    <article class="questions__padding">
                        <div class="questions__answer">
                            <h3 class="questions__title">¿Quiénes somos?
                                <span class="questions__arrow">
                                    <img src="/images/arrow.svg" class="questions__img">
                                </span>
                            </h3>
                            <p class="questions__show">Aquí, las organizaciones y municipalidades pueden registrar sus eventos de voluntariado de manera fácil y efectiva. Nuestro objetivo es conectar a las personas interesadas en contribuir con su tiempo y esfuerzo a diversas causas comunitarias.</p>
                        </div>
                    </article>
                    <article class="questions__padding">
                        <div class="questions__answer">
                            <h3 class="questions__title">¿Qué empresas están asociadas?
                                <span class="questions__arrow">
                                    <img src="/images/arrow.svg" class="questions__img">
                                </span>
                            </h3>
                            <p class="questions__show">Estamos asociados con diversas empresas y organizaciones que comparten nuestro compromiso con el voluntariado y el impacto social positivo. Estas asociaciones nos permiten ampliar nuestro alcance y maximizar el impacto de nuestros esfuerzos voluntarios en diversas áreas.</p>
                        </div>
                    </article>
                    <article class="questions__padding">
                        <div class="questions__answer">
                            <h3 class="questions__title">¿Es en todo Chile?
                                <span class="questions__arrow">
                                    <img src="/images/arrow.svg" class="questions__img">
                                </span>
                            </h3>
                            <p class="questions__show">Sí, nuestras actividades de voluntariado se llevan a cabo en todo Chile. Colaboramos con diversas organizaciones y comunidades a lo largo del país para asegurar que nuestro impacto sea amplio y significativo, abarcando desde las grandes ciudades hasta las áreas rurales. Puedes unirte a nuestros esfuerzos desde cualquier parte de Chile y contribuir a hacer del mundo un lugar mejor.</p>
                        </div>
                    </article>
                    <article class="questions__padding">
                        <div class="questions__answer">
                            <h3 class="questions__title">¿Son seguras las organizaciones registradas?
                                <span class="questions__arrow">
                                    <img src="/images/arrow.svg" class="questions__img">
                                </span>
                            </h3>
                            <p class="questions__show">Garantizamos a nuestros voluntarios la mayor seguridad, ya que a través de nuestro sistema de verificación de organizaciones nos aseguramos de que todos los datos que esta proporciona sean legítimos.</p>
                        </div>
                    </article>
                </section>
                <section class="questions__offer">
                    <h2 class="subtitle">¿Estás listo para unirte a nosotros?</h2>
                    <p class="questions__copy">¡Estamos listos para darte la bienvenida a nuestra comunidad de voluntarios! Únete a nosotros y juntos trabajaremos para hacer del mundo un lugar mejor, apoyando a quienes más lo necesitan, protegiendo el medio ambiente y cuidando a los animales. ¡Tu contribución puede marcar la diferencia!</p>
                    <a href="/registro/usuario" class="cta">Regístrate ahora</a>
                </section>
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
                        <a href="#" class="footer__icons"><img src="/images/youtube.svg" class="footer__img"></a>
                    </div>
                    <h3 class="footer__copyright">Derechos reservados &copy; Fundación Forge grupo 5 c:</h3>
                </section>
            </section>
        </footer>

        <script src="/js/slider.js"></script>
        <script src="/js/questions.js"></script>
        <script src="/js/menu.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/aos@2.3.4/dist/aos.js"></script>
        <script>
            AOS.init();
        </script>
    </body>
</html>
