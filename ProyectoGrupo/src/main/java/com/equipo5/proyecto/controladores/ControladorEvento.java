package com.equipo5.proyecto.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.equipo5.proyecto.modelos.Categoria;
import com.equipo5.proyecto.modelos.Evento;
import com.equipo5.proyecto.modelos.Organizacion;
import com.equipo5.proyecto.modelos.Usuario;
import com.equipo5.proyecto.servicios.ServicioCategoria;
import com.equipo5.proyecto.servicios.ServicioEventos;
import com.equipo5.proyecto.servicios.ServicioOrganizacion;
import com.equipo5.proyecto.servicios.ServicioUsuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/eventos")
public class ControladorEvento {
	@Autowired
	private final ServicioEventos servicioEvento;
	@Autowired
	private final ServicioCategoria servicioCategoria;
	@Autowired
	private final ServicioOrganizacion servicioOrganizacion;
	@Autowired
	private final ServicioUsuario servicioUsuario;
	
	public ControladorEvento(ServicioEventos servicioEvento, ServicioCategoria servicioCategoria, ServicioOrganizacion servicioOrganizacion,  ServicioUsuario servicioUsuario) {
		this.servicioEvento = servicioEvento;
		this.servicioCategoria = servicioCategoria;
		this.servicioOrganizacion = servicioOrganizacion;
		this.servicioUsuario = servicioUsuario;
	}
	
	@GetMapping("/nuevo")
	public String desplegarFormularioEvento(@ModelAttribute("evento") Evento evento,
											Model model,
											HttpSession sesion) {
		if(sesion.getAttribute("id_organizacion") == null) {
			return "redirect:/login";
		}
		List<Categoria> categorias = this.servicioCategoria.obtenerCategorias();
		model.addAttribute("categorias", categorias);
		return "formularioEvento.jsp";
	}
	
	@PostMapping("/nuevo")
	public String agregarEvento(@Valid @ModelAttribute("evento") Evento evento,
									BindingResult validaciones,
									Model model,
									HttpSession sesion) {
		if(evento.getFechaHora() != null) {
			if(evento.getFechaHora().isBefore(LocalDateTime.now())) {
				validaciones.rejectValue("fechaHora", "FechaPasada", "Fecha Invalida");
			}
			if(evento.getFechaTermino() != null && evento.getFechaTermino().isBefore(evento.getFechaHora())) {
				validaciones.rejectValue("fechaTermino", "FechaPasada", "Fecha Invalida");
			}
		}
		if(validaciones.hasErrors()) {
			List<Categoria> categorias = this.servicioCategoria.obtenerCategorias();
			model.addAttribute("categorias", categorias);
			return "formularioEvento.jsp";
		}
		Long idOrganizacion = (Long)sesion.getAttribute("id_organizacion");
		Organizacion organizacion = this.servicioOrganizacion.obtenerPorId(idOrganizacion);
		evento.setOrganizacion(organizacion);
		this.servicioEvento.insertarEvento(evento);
		return "redirect:/organizacion";
	}

	@GetMapping("/{id}")
	public String detallesEvento(@PathVariable("id") Long eventoId,
	                             Model model,
	                             HttpSession sesion) {
	    Long idUsuario = (Long) sesion.getAttribute("id_usuario");
	    Long idOrganizacion = (Long) sesion.getAttribute("id_organizacion");

	    if (idUsuario == null && idOrganizacion == null) {
	        return "redirect:/login";
	    }

	    Evento evento = this.servicioEvento.obtenerEventoPorId(eventoId);
	    List<Organizacion> organizaciones = this.servicioOrganizacion.obtenerTodos();
	    model.addAttribute("evento", evento);
	    model.addAttribute("organizaciones", organizaciones);

	    if (idOrganizacion != null) {
	        // Si es una organización, agregar voluntarios con su edad
	        List<Usuario> voluntariosEnEvento = evento.getUsuarios(); // Voluntarios registrados en el evento

	        for (Usuario usuario : voluntariosEnEvento) {
	            int edad = this.servicioUsuario.calcularEdad(usuario.getFechaNacimiento());
	            usuario.setEdad(edad); // Asignar la edad calculada
	        }

	        model.addAttribute("voluntariosConEdad", voluntariosEnEvento);
	    } else if (idUsuario != null) {
	        // Si es un usuario, agregar solo su nombre y apellido
	        Usuario usuario = this.servicioUsuario.obtenerPorId(idUsuario);
	        model.addAttribute("usuarioNombre", usuario.getNombre());
	        model.addAttribute("usuarioApellido", usuario.getApellido());
	    }

	    return "detallesEvento.jsp";
	}
	
	@GetMapping("/filtrarCategoria/{categoria}")
	public String filtrarPorCategoria(@PathVariable("categoria") String nombreCategoria,
										Model model,
										HttpSession sesion) {
		if(sesion.getAttribute("id_usuario") == null) {
			return "redirect:/login";
		}
		Categoria categoria = this.servicioCategoria.obtenerCategoriaPorNombre(nombreCategoria);
		List<Evento> eventosFiltrados = categoria.getEventos();
		
		Long usuarioId = (Long) sesion.getAttribute("id_usuario");
		Usuario usuario = servicioUsuario.obtenerPorId(usuarioId);
		List<Evento> eventosUsuario = usuario.getEventos();
	    List<Categoria> categorias = servicioCategoria.obtenerCategorias();
		
	    model.addAttribute("eventosUsuario", eventosUsuario);
	    model.addAttribute("eventos", eventosFiltrados);
	    model.addAttribute("categorias", categorias);
	    model.addAttribute("usuario", usuario);
		model.addAttribute("eventosFiltrados", eventosFiltrados);
	    return "voluntario.jsp";
	}
	
	@PostMapping("/participar/{eventoId}")
	public String participarEnEvento(@PathVariable Long eventoId, HttpSession sesion, Model model) {
	    Long usuarioId = (Long) sesion.getAttribute("id_usuario");
	    Evento evento = servicioEvento.obtenerEventoPorId(eventoId);
	    
	    if (evento.getVoluntariosRegistrados() < evento.getLimiteVoluntarios()) {
	        Usuario usuario = servicioUsuario.obtenerPorId(usuarioId);
	        evento.getUsuarios().add(usuario);
	        servicioEvento.actualizarEvento(evento);
	        usuario.getEventos().add(evento);
	        servicioUsuario.actualizarUsuario(usuario);
	    } 
	    return "redirect:/voluntario";
	}
	
	@PostMapping("/quitar/{id}")
	public String quitarParticipante(@PathVariable("id") Long id,
									HttpSession sesion) {
		Long usuarioId = (Long)sesion.getAttribute("id_usuario");
		Usuario usuario = this.servicioUsuario.obtenerPorId(usuarioId);
		
		Evento evento = this.servicioEvento.obtenerEventoPorId(id);
		evento.getUsuarios().remove(usuario);
		this.servicioEvento.actualizarEvento(evento);
		usuario.getEventos().remove(evento);
		this.servicioUsuario.actualizarUsuario(usuario);
		return "redirect:/voluntario";
	}
}
