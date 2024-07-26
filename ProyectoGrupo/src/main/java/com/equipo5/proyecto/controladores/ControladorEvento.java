package com.equipo5.proyecto.controladores;

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
import com.equipo5.proyecto.servicios.ServicioCategoria;
import com.equipo5.proyecto.servicios.ServicioEventos;
import com.equipo5.proyecto.servicios.ServicioOrganizacion;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/eventos/")
public class ControladorEvento {
	@Autowired
	private final ServicioEventos servicioEvento;
	@Autowired
	private final ServicioCategoria servicioCategoria;
	@Autowired
	private final ServicioOrganizacion servicioOrganizacion;
	public ControladorEvento(ServicioEventos servicioEvento, ServicioCategoria servicioCategoria, ServicioOrganizacion servicioOrganizacion) {
		this.servicioEvento = servicioEvento;
		this.servicioCategoria = servicioCategoria;
		this.servicioOrganizacion = servicioOrganizacion;
	}
	
	@GetMapping("/nuevo")
	public String desplegarFormularioEvento(@ModelAttribute("evento") Evento evento,
											Model model,
											HttpSession sesion) {
		if(sesion.getAttribute("id_organizacion") == null) {
			return "redirect:/";
		}
		List<Categoria> categorias = this.servicioCategoria.obtenerCategorias();
		model.addAttribute("categorias", categorias);
		return "formularioEvento.jsp";
	}
	
	@PostMapping("/nuevo")
	public String agregarEvento(@Valid @ModelAttribute("evento") Evento evento,
									BindingResult validaciones,
									HttpSession sesion) {
		if(validaciones.hasErrors()) {
			return "formularioEvento.jsp";
		}
		Long idOrganizacion = (Long)sesion.getAttribute("id_organizacion");
		Organizacion organizacion = this.servicioOrganizacion.obtenerPorId(idOrganizacion);
		evento.setOrganizacion(organizacion);
		this.servicioEvento.insertarEvento(evento);
		return "redirect:/voluntarioDashboard";
	}
	@GetMapping("/filtrarCategoria/{categoria}")
	public String filtrarPorCategoria(@PathVariable("categoria") String nombreCategoria,
										Model model,
										HttpSession sesion) {
		if(sesion.getAttribute("id_usuario") == null) {
			return "redirect:/";
		}
		Categoria categoria = this.servicioCategoria.obtenerCategoriaPorNombre(nombreCategoria);
		List<Evento> eventosFiltrados = categoria.getEventos();
		model.addAttribute("eventosFiltrados", eventosFiltrados);
		return "eventosFiltrados.jsp";
	}
	
	@GetMapping("/{id}")
	public String detallesEvento(@PathVariable("id") Long eventoId,
								Model model,
								HttpSession sesion) {
		if(sesion.getAttribute("id_usuario") == null) {
			return "redirect:/";
		}
		Evento evento = this.servicioEvento.obtenerEventoPorId(eventoId);
		model.addAttribute("evento", evento);
		return "detallesEvento.jsp";
	}
}
