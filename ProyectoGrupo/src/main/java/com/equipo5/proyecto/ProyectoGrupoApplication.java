package com.equipo5.proyecto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.equipo5.proyecto.clientes.SSLUtils;
import com.equipo5.proyecto.modelos.Categoria;
import com.equipo5.proyecto.modelos.Evento;
import com.equipo5.proyecto.modelos.Inscripcion;
import com.equipo5.proyecto.modelos.Organizacion;
import com.equipo5.proyecto.modelos.Usuario;
import com.equipo5.proyecto.servicios.ServicioCategoria;
import com.equipo5.proyecto.servicios.ServicioEventos;
import com.equipo5.proyecto.servicios.ServicioInscripcion;
import com.equipo5.proyecto.servicios.ServicioOrganizacion;
import com.equipo5.proyecto.servicios.ServicioUsuario;

@SpringBootApplication
public class ProyectoGrupoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoGrupoApplication.class, args);
		SSLUtils.disableCertificateValidation();
	}
	
	//Añadir datos por defecto, Solo para las pruebas
	@Bean
    public CommandLineRunner commandLineRunner(ServicioCategoria servicioCategoria, ServicioOrganizacion servicioOrganizacion,
    											ServicioUsuario servicioUsuario, ServicioEventos servicioEvento, ServicioInscripcion servicioInscripcion) {
        return args -> {
            if(servicioCategoria.obtenerCategorias().size() == 0) {
            	List<Categoria> categorias = new ArrayList<>();
            	categorias.add(new Categoria("Animales"));
            	categorias.add(new Categoria("Agricultura"));
            	categorias.add(new Categoria("Comunidad"));
            	categorias.add(new Categoria("Cuidado Infantil"));
            	categorias.add(new Categoria("Educación"));
            	categorias.add(new Categoria("Trabajo Social"));
            	categorias.add(new Categoria("Construcción"));
            	categorias.add(new Categoria("Reforestación"));
            	categorias.add(new Categoria("Salud"));
            	categorias.add(new Categoria("Ayuda Humanitaria"));
                categorias.add(new Categoria("Cultura y Arte"));
                categorias.add(new Categoria("Tecnología y Ciencias"));
                categorias.add(new Categoria("Derechos Humanos"));
                categorias.add(new Categoria("Desarrollo Económico"));
                categorias.add(new Categoria("Asistencia a Personas Mayores"));
                categorias.add(new Categoria("Apoyo a Personas con Discapacidad"));
                categorias.add(new Categoria("Empoderamiento de la Mujer"));
                categorias.add(new Categoria("Refugio y Vivienda"));
                categorias.add(new Categoria("Emergencias y Desastres"));
                categorias.add(new Categoria("Desarrollo Juvenil"));
                categorias.add(new Categoria("Asistencia Alimentaria"));
                categorias.add(new Categoria("Educación para Adultos"));
                
                for(Categoria categoria : categorias) {
                	servicioCategoria.insertarCategoria(categoria);
                }
            }
            List<Usuario> usuarios = new ArrayList<Usuario>();
            usuarios.add(new Usuario("12345678-9", "Admin", "Admin", "admin@gmail.com", 98765432, "Santiago", LocalDate.of(1990, 5, 15), "12345678", true));
            usuarios.add(new Usuario("12345678-9", "Juan", "Pérez", "juanPerez@gmail.com", 98765432, "Santiago", LocalDate.of(1990, 5, 15), "12345678"));
            usuarios.add(new Usuario("23456789-0", "Ana", "González", "anaGonzalez@gmail.com", 87654321, "Valparaíso", LocalDate.of(1985, 10, 22), "12345678"));
            usuarios.add(new Usuario("34567890-1", "Luis", "Fernández", "luisFernandez@gmail.com", 76543210, "Concepción", LocalDate.of(1995, 8, 30), "12345678"));
            usuarios.add(new Usuario("45678901-2", "Isabel", "Morales", "isabelMorales@gmail.com", 65432109, "Temuco", LocalDate.of(1992, 3, 5), "12345678"));
            usuarios.add(new Usuario("56789012-3", "Ricardo", "Alvarez", "ricardoAlvarez@gmail.com", 54321098, "La Serena", LocalDate.of(1988, 7, 19), "12345678"));
            usuarios.add(new Usuario("67890123-4", "María", "Rivas", "mariaRivas@gmail.com", 43210987, "Antofagasta", LocalDate.of(1991, 12, 8), "12345678"));
            usuarios.add(new Usuario("78901234-5", "Pedro", "Castro", "pedroCastro@gmail.com", 32109876, "Arica", LocalDate.of(1983, 6, 23), "12345678"));
            usuarios.add(new Usuario("89012345-6", "Laura", "Vásquez", "lauraVasquez@gmail.com", 21098765, "Iquique", LocalDate.of(1994, 11, 16), "12345678"));
            usuarios.add(new Usuario("90123456-7", "Sergio", "Orellana", "sergioOrellana@gmail.com", 10987654, "Puerto Montt", LocalDate.of(1989, 4, 25), "12345678"));
            usuarios.add(new Usuario("41234567-8", "Catalina", "Mendoza", "catalinaMendoza@gmail.com", 98765432, "Osorno", LocalDate.of(1996, 9, 2), "12345678"));
            for(Usuario usuario : usuarios) {
            	if(servicioUsuario.obtenerPorCorreo(usuario.getCorreo()) == null) {
            		servicioUsuario.registrarUsuario(usuario);
            	}
            }
            
            List<Organizacion> organizaciones = new ArrayList<Organizacion>();
            organizaciones.add(new Organizacion("73.329.300-4", "Fundación Redes de Santa Clara", "santaClara@gmail.com", "12345678", "123456789", "https://www.gmail.com", true));
            organizaciones.add(new Organizacion("81.496.800-6", "Fundación de Beneficencia Hogar de Cristo", "hogarCristo@gmail.com", "12345678", "123456789", "https://www.gmail.com", true));
            organizaciones.add(new Organizacion("70.543.600-2", "Fundación las Rosas de Ayuda Fraterna", "rosasAyudaFraterna@gmail.com", "12345678", "123456789", "https://www.gmail.com", true));
            organizaciones.add(new Organizacion("65.018.488-2", "Corporación de Organizaciones Solidarias", "organizacionesSolidarias@gmail.com", "12345678", "123456789", "https://www.gmail.com", true));
            organizaciones.add(new Organizacion("70.017.730-0", "Fundación Ciudad del Niño Ricardo Espinosa", "ricardoEspinosa@gmail.com", "12345678", "123456789", "https://www.gmail.com", false));
            organizaciones.add(new Organizacion("65.533.130-1", "Fundación un Techo para Chile", "techoChile@gmail.com", "12345678", "123456789", "https://www.gmail.com", false));
            organizaciones.add(new Organizacion("65.816.320-5", "Hermanitas del Cordero", "hermanitasCordero@gmail.com", "12345678", "123456789", "https://www.gmail.com", false));
            organizaciones.add(new Organizacion("65.878.800-0", "Fundación Villa de Ancianos Padre Alberto Hurtado", "albertoHurtado@gmail.com", "12345678", "123456789", "https://www.gmail.com", false));
            for(Organizacion organizacion : organizaciones) {
            	if(servicioOrganizacion.obtenerPorCorreo(organizacion.getCorreo()) == null) {
            		servicioOrganizacion.insertarOrganizacion(organizacion);
            	}
            }
            
            List<Evento> eventos = new ArrayList<Evento>();
            eventos.add(new Evento("Festival de Música", "Santiago", "Parque O'Higgins", "Un gran festival con múltiples bandas locales e internacionales.", LocalDateTime.of(2024, 8, 6, 20, 0), LocalDateTime.of(2024, 8, 7, 22, 0), servicioOrganizacion.obtenerPorCorreo("santaClara@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Cultura y Arte"), 500));
            eventos.add(new Evento("Cine al Aire Libre", "Valparaíso", "Plaza Sotomayor", "Proyección de películas clásicas en un ambiente al aire libre.", LocalDateTime.of(2024, 10, 5, 20, 0), LocalDateTime.of(2024, 10, 5, 23, 0), servicioOrganizacion.obtenerPorCorreo("santaClara@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Comunidad"), 300));
            
            eventos.add(new Evento("Maratón Internacional", "Concepción", "Estadio Municipal", "Competencia de maratón con corredores de todo el mundo.", LocalDateTime.of(2024, 11, 1, 7, 0), LocalDateTime.of(2024, 11, 1, 13, 0), servicioOrganizacion.obtenerPorCorreo("hogarCristo@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Comunidad"), 1000));
            eventos.add(new Evento("Feria de Ciencia", "Temuco", "Centro Cultural", "Exposición de proyectos científicos y tecnológicos.", LocalDateTime.of(2024, 12, 10, 10, 0), LocalDateTime.of(2024, 12, 10, 16, 0), servicioOrganizacion.obtenerPorCorreo("hogarCristo@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Tecnología y Ciencias"), 200));
            
            eventos.add(new Evento("Mercado de Artesanía", "La Serena", "Plaza de Armas", "Venta de artesanías y productos locales en un mercado al aire libre.", LocalDateTime.of(2024, 12, 15, 11, 0), LocalDateTime.of(2024, 12, 15, 13, 0), servicioOrganizacion.obtenerPorCorreo("rosasAyudaFraterna@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Cultura y Arte"), 150));
            eventos.add(new Evento("Conferencia de Tecnología", "Antofagasta", "Hotel Costa Pacifico", "Conferencia sobre las últimas tendencias en tecnología.", LocalDateTime.of(2024, 11, 20, 9, 0), LocalDateTime.of(2024, 11, 20, 12, 0), servicioOrganizacion.obtenerPorCorreo("rosasAyudaFraterna@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Tecnología y Ciencias"), 400));
            
            eventos.add(new Evento("Torneo de E-Sports", "Santiago", "Centro de Convenciones", "Competencia de videojuegos con equipos locales y nacionales.", LocalDateTime.of(2024, 9, 25, 15, 0), LocalDateTime.of(2024, 9, 25, 17, 0), servicioOrganizacion.obtenerPorCorreo("organizacionesSolidarias@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Tecnología y Ciencias"), 400));
            eventos.add(new Evento("Concierto de Jazz", "Valparaíso", "Teatro Municipal", "Concierto en vivo con artistas de jazz internacionales.", LocalDateTime.of(2024, 10, 10, 20, 0), LocalDateTime.of(2024, 10, 10, 23, 0), servicioOrganizacion.obtenerPorCorreo("organizacionesSolidarias@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Cultura y Arte"), 200));

            
            eventos.add(new Evento("Festival Gastronómico", "Arica", "Plaza de la Pampa", "Festival con una variedad de comidas y bebidas locales e internacionales.", LocalDateTime.of(2024, 10, 25, 12, 0), LocalDateTime.of(2024, 10, 25, 14, 0), servicioOrganizacion.obtenerPorCorreo("ricardoEspinosa@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Comunidad"), 250));
            eventos.add(new Evento("Exposición de Arte", "Iquique", "Museo Regional", "Exposición de obras de artistas locales y nacionales.", LocalDateTime.of(2024, 9, 30, 17, 0), LocalDateTime.of(2024, 9, 30, 20, 0), servicioOrganizacion.obtenerPorCorreo("ricardoEspinosa@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Cultura y Arte"), 180));
            
            eventos.add(new Evento("Rally Deportivo", "Puerto Montt", "Circuito Deportivo", "Competencia de rally con vehículos de diferentes categorías.", LocalDateTime.of(2024, 10, 15, 8, 0), LocalDateTime.of(2024, 10, 15, 10, 0), servicioOrganizacion.obtenerPorCorreo("techoChile@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Salud"), 600));
            eventos.add(new Evento("Día de la Juventud", "Osorno", "Plaza de la Juventud", "Celebración del Día de la Juventud con actividades y música en vivo.", LocalDateTime.of(2024, 11, 30, 14, 0), LocalDateTime.of(2024, 11, 30, 17, 0), servicioOrganizacion.obtenerPorCorreo("techoChile@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Comunidad"), 350));

            eventos.add(new Evento("Festival de Invierno", "Santiago", "Centro de Eventos", "Celebración del invierno con música en vivo, comida y actividades al aire libre.", LocalDateTime.of(2024, 6, 20, 16, 0), LocalDateTime.of(2024, 6, 20, 18, 0), servicioOrganizacion.obtenerPorCorreo("hermanitasCordero@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Comunidad"), 350));
            eventos.add(new Evento("Concurso de Fotografía", "Valparaíso", "Galería Municipal", "Exposición y concurso de fotografía con participantes locales y nacionales.", LocalDateTime.of(2024, 7, 15, 10, 0), LocalDateTime.of(2024, 7, 15, 14, 0), servicioOrganizacion.obtenerPorCorreo("hermanitasCordero@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Cultura y Arte"), 350));
            
            eventos.add(new Evento("Fiesta de Año Nuevo", "Concepción", "Club Social", "Celebración de Año Nuevo con cena, baile y entretenimiento.", LocalDateTime.of(2024, 12, 31, 22, 0), LocalDateTime.of(2025, 1, 1, 2, 0), servicioOrganizacion.obtenerPorCorreo("albertoHurtado@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Comunidad"), 350));
            eventos.add(new Evento("Simposio de Medicina", "Temuco", "Auditorio Universitario", "Simposio sobre avances en medicina con conferencias y talleres.", LocalDateTime.of(2024, 8, 22, 9, 0), LocalDateTime.of(2024, 8, 22, 12, 0), servicioOrganizacion.obtenerPorCorreo("albertoHurtado@gmail.com"), servicioCategoria.obtenerCategoriaPorNombre("Salud"), 350));
            
            for(Evento nuevoEvento : eventos) {
            	List<Evento> eventosExistentes = servicioEvento.obtenerEventos();
            	boolean existe = eventosExistentes.stream()
							                    .anyMatch(evento -> evento.getNombre().equals(nuevoEvento.getNombre())
							                    		 && evento.getDescripcion().equals(nuevoEvento.getDescripcion())
							                             && evento.getCiudad().equals(nuevoEvento.getCiudad())
							                             && evento.getUbicacion().equals(nuevoEvento.getUbicacion())
							                             && evento.getFechaHora().isEqual(nuevoEvento.getFechaHora()));

                if (!existe) {
                    servicioEvento.insertarEvento(nuevoEvento);
                }
            }
            
            List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("juanPerez@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Festival de Música", "Santiago", "Un gran festival con múltiples bandas locales e internacionales."), true));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("anaGonzalez@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Festival de Música", "Santiago", "Un gran festival con múltiples bandas locales e internacionales."), true));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("catalinaMendoza@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Festival de Música", "Santiago", "Un gran festival con múltiples bandas locales e internacionales."), false));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("mariaRivas@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Festival de Música", "Santiago", "Un gran festival con múltiples bandas locales e internacionales."), false));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("isabelMorales@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Festival de Música", "Santiago", "Un gran festival con múltiples bandas locales e internacionales."), true));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("sergioOrellana@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Festival de Música", "Santiago", "Un gran festival con múltiples bandas locales e internacionales."), false));
            
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("sergioOrellana@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Cine al Aire Libre", "Valparaíso", "Proyección de películas clásicas en un ambiente al aire libre."), false));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("mariaRivas@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Cine al Aire Libre", "Valparaíso", "Proyección de películas clásicas en un ambiente al aire libre."), false));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("isabelMorales@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Cine al Aire Libre", "Valparaíso", "Proyección de películas clásicas en un ambiente al aire libre."), false));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("ricardoAlvarez@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Cine al Aire Libre", "Valparaíso", "Proyección de películas clásicas en un ambiente al aire libre."), false));
            
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("luisFernandez@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Maratón Internacional", "Concepción", "Competencia de maratón con corredores de todo el mundo."), false));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("anaGonzalez@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Maratón Internacional", "Concepción", "Competencia de maratón con corredores de todo el mundo."), false));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("catalinaMendoza@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Maratón Internacional", "Concepción", "Competencia de maratón con corredores de todo el mundo."), false));
            
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("anaGonzalez@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Feria de Ciencia", "Temuco", "Exposición de proyectos científicos y tecnológicos."), false));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("catalinaMendoza@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Feria de Ciencia", "Temuco", "Exposición de proyectos científicos y tecnológicos."), false));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("sergioOrellana@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Feria de Ciencia", "Temuco", "Exposición de proyectos científicos y tecnológicos."), false));
            
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("catalinaMendoza@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Mercado de Artesanía", "La Serena", "Venta de artesanías y productos locales en un mercado al aire libre."), false));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("sergioOrellana@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Mercado de Artesanía", "La Serena", "Venta de artesanías y productos locales en un mercado al aire libre."), false));
            
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("catalinaMendoza@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Conferencia de Tecnología", "Antofagasta", "Conferencia sobre las últimas tendencias en tecnología."), false));
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("sergioOrellana@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Conferencia de Tecnología", "Antofagasta", "Conferencia sobre las últimas tendencias en tecnología."), false));
            
            inscripciones.add(new Inscripcion(servicioUsuario.obtenerPorCorreo("isabelMorales@gmail.com"), servicioEvento.obtenerPorNombreCiudadYDescripcion("Torneo de E-Sports", "Santiago", "Competencia de videojuegos con equipos locales y nacionales."), false));
            
            for(Inscripcion inscripcion : inscripciones) {
            	if(servicioInscripcion.obtenerInscripcionPorUsuarioYEvento(inscripcion.getUsuario(), inscripcion.getEvento()) == null) {
            		servicioInscripcion.insertarInscripcion(inscripcion);
            	}
            }
        };
    }
}
