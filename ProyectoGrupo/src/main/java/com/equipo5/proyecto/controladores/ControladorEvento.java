package com.equipo5.proyecto.controladores;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@Autowired
	private final ServicioInscripcion servicioInscripcion;
	
	public ControladorEvento(ServicioEventos servicioEvento, ServicioCategoria servicioCategoria, ServicioOrganizacion servicioOrganizacion,  
							ServicioUsuario servicioUsuario, ServicioInscripcion servicioInscripcion) {
		this.servicioEvento = servicioEvento;
		this.servicioCategoria = servicioCategoria;
		this.servicioOrganizacion = servicioOrganizacion;
		this.servicioUsuario = servicioUsuario;
		this.servicioInscripcion = servicioInscripcion;
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
	    if(idUsuario != null) {
	    	Usuario usuario = this.servicioUsuario.obtenerPorId(idUsuario);
	    	model.addAttribute("usuario", usuario);
	    }
	    Evento evento = this.servicioEvento.obtenerEventoPorId(eventoId);
	    model.addAttribute("evento", evento);

	    List<Usuario> voluntarios = evento.getUsuarios();
	    model.addAttribute("voluntarios", voluntarios);
	    return "detallesEvento.jsp";
	}
	
	@DeleteMapping("/eliminar/{id}")
	public String eliminarEvento(@PathVariable("id") Long eventoId,
					            HttpSession sesion) {
		Evento evento = this.servicioEvento.obtenerEventoPorId(eventoId);
		this.servicioEvento.eliminarEvento(evento);
		return "redirect:/organizacion";
	}
	
	@GetMapping("/filtrarCategoria/{categoria}")
	public String filtrarPorCategoria(@PathVariable("categoria") String nombreCategoria,
										Model model,
										HttpSession sesion) {
		if(sesion.getAttribute("id_usuario") == null) {
			return "redirect:/login";
		}
		Categoria categoria = this.servicioCategoria.obtenerCategoriaPorNombre(nombreCategoria);
		if(categoria == null) return "redirect:/voluntario";
		List<Evento> eventosFiltrados = categoria.getEventos();
		
		Long usuarioId = (Long) sesion.getAttribute("id_usuario");
		Usuario usuario = servicioUsuario.obtenerPorId(usuarioId);
		List<Evento> eventosUsuario = usuario.getEventos();
	    List<Categoria> categorias = servicioCategoria.obtenerCategorias();
	    List<Organizacion> organizaciones = servicioOrganizacion.obtenerTodos();
		
	    model.addAttribute("eventosUsuario", eventosUsuario);
	    model.addAttribute("eventos", eventosFiltrados);
	    model.addAttribute("categorias", categorias);
	    model.addAttribute("usuario", usuario);
		model.addAttribute("eventosFiltrados", eventosFiltrados);
		model.addAttribute("organizaciones", organizaciones);
	    return "voluntario.jsp";
	}
	
	@GetMapping("/filtrarOrganizacion/{organizacionId}")
	public String filtrarPorOrganizacion(@PathVariable("organizacionId") Long organizacionId,
										Model model,
										HttpSession sesion) {
		if(sesion.getAttribute("id_usuario") == null) {
			return "redirect:/login";
		}
		Organizacion organizacion = this.servicioOrganizacion.obtenerPorId(organizacionId);
		if(organizacion == null) return "redirect:/voluntario";
		List<Evento> eventosFiltrados = organizacion.getEventos();
		
		Long usuarioId = (Long) sesion.getAttribute("id_usuario");
		Usuario usuario = servicioUsuario.obtenerPorId(usuarioId);
		List<Evento> eventosUsuario = usuario.getEventos();
	    List<Categoria> categorias = servicioCategoria.obtenerCategorias();
	    List<Organizacion> organizaciones = servicioOrganizacion.obtenerTodos();
		
	    model.addAttribute("eventosUsuario", eventosUsuario);
	    model.addAttribute("eventos", eventosFiltrados);
	    model.addAttribute("categorias", categorias);
	    model.addAttribute("usuario", usuario);
		model.addAttribute("eventosFiltrados", eventosFiltrados);
		model.addAttribute("organizaciones", organizaciones);
	    return "voluntario.jsp";
	}
	
	@GetMapping("/filtrar/{organizacionId}/{categoria}")
	public String fitrarOrganizacionYCategoria(@PathVariable("organizacionId") Long organizacionId,
												@PathVariable("categoria") String categoriaNombre,
												Model model,
												HttpSession sesion) {
		if(sesion.getAttribute("id_usuario") == null) {
			return "redirect:/login";
		}
		
		Organizacion organizacion = this.servicioOrganizacion.obtenerPorId(organizacionId);
		Categoria categoria = this.servicioCategoria.obtenerCategoriaPorNombre(categoriaNombre);
		List<Evento> eventosFiltrados = categoria.getEventos()
												.stream().filter(e -> e.getOrganizacion()
																		.equals(organizacion)).toList();
		Long usuarioId = (Long) sesion.getAttribute("id_usuario");
		Usuario usuario = servicioUsuario.obtenerPorId(usuarioId);
		List<Evento> eventosUsuario = usuario.getEventos();
	    List<Categoria> categorias = servicioCategoria.obtenerCategorias();
	    List<Organizacion> organizaciones = servicioOrganizacion.obtenerTodos();
	    
		model.addAttribute("eventosUsuario", eventosUsuario);
	    model.addAttribute("eventos", eventosFiltrados);
	    model.addAttribute("categorias", categorias);
	    model.addAttribute("usuario", usuario);
		model.addAttribute("eventosFiltrados", eventosFiltrados);
		model.addAttribute("organizaciones", organizaciones);
		return "voluntario.jsp";
	}
	
	
	@PostMapping("/participar/{eventoId}")
	public String participarEnEvento(@PathVariable Long eventoId, HttpSession sesion, Model model) {
	    Long usuarioId = (Long) sesion.getAttribute("id_usuario");
	    Evento evento = servicioEvento.obtenerEventoPorId(eventoId);
	    
	    if (evento.getVoluntariosRegistrados() < evento.getLimiteVoluntarios()) {
	        Usuario usuario = servicioUsuario.obtenerPorId(usuarioId);
	        Inscripcion inscripcion = new Inscripcion(usuario, evento);
	        this.servicioInscripcion.insertarInscripcion(inscripcion);
	    } 
	    return "redirect:/voluntario";
	}
	
	@PostMapping("/quitar/{id}")
	public String quitarParticipante(@PathVariable("id") Long id,
									HttpSession sesion) {
		Long usuarioId = (Long)sesion.getAttribute("id_usuario");
		Usuario usuario = this.servicioUsuario.obtenerPorId(usuarioId);
		Evento evento = this.servicioEvento.obtenerEventoPorId(id);
		
		Inscripcion inscripcion = this.servicioInscripcion.obtenerInscripcionPorUsuarioYEvento(usuario, evento);
		if(inscripcion != null) {
			this.servicioInscripcion.eliminarInscripcion(inscripcion);
		}
		return "redirect:/voluntario";
	}
	
	@GetMapping("/{eventoId}/confirmarAsistencia/{voluntarioId}")
	public String confirmarAsistencia(@PathVariable("eventoId") Long eventoId,
										@PathVariable("voluntarioId") Long usuarioId,
										HttpSession sesion) {
		if(sesion.getAttribute("id_organizacion") == null) {
			return "redirect:/login";
		}
		Usuario usuario = this.servicioUsuario.obtenerPorId(usuarioId);
		Evento evento = this.servicioEvento.obtenerEventoPorId(eventoId);
		Inscripcion inscripcion = this.servicioInscripcion.obtenerInscripcionPorUsuarioYEvento(usuario, evento);
		if(!evento.estaActivo() || inscripcion == null) {
			return "redirect:/eventos/" + eventoId;
		}
		inscripcion.setAsistenciaConfirmada(true);
		this.servicioInscripcion.actualizarInscripcion(inscripcion);
		return "redirect:/eventos/" + eventoId;
	}
	
	@GetMapping("/{eventoId}/negarAsistencia/{voluntarioId}")
	public String eliminarAsistencia(@PathVariable("eventoId") Long eventoId,
										@PathVariable("voluntarioId") Long usuarioId,
										HttpSession sesion) {
		if(sesion.getAttribute("id_organizacion") == null) {
			return "redirect:/login";
		}
		Usuario usuario = this.servicioUsuario.obtenerPorId(usuarioId);
		Evento evento = this.servicioEvento.obtenerEventoPorId(eventoId);
		Inscripcion inscripcion = this.servicioInscripcion.obtenerInscripcionPorUsuarioYEvento(usuario, evento);
		if(!evento.estaActivo() || evento == null) {
			return "redirect:/eventos/" + eventoId;
		}
		inscripcion.setAsistenciaConfirmada(false);
		this.servicioInscripcion.actualizarInscripcion(inscripcion);
		return "redirect:/eventos/" + eventoId;
	}
}
