package com.equipo5.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

import com.equipo5.proyecto.modelos.Organizacion;
import com.equipo5.proyecto.modelos.Usuario;
import com.equipo5.proyecto.servicios.ServicioOrganizacion;
import com.equipo5.proyecto.servicios.ServicioUsuario;


@Controller
public class ControladorOrganizacion {

	@Autowired
	private final ServicioUsuario servicioUsuario;
	private final ServicioOrganizacion servicioOrganizacion;

	public ControladorOrganizacion(ServicioUsuario servicioUsuario, ServicioOrganizacion servicioOrganizacion) {
		this.servicioUsuario = servicioUsuario;
		this.servicioOrganizacion = servicioOrganizacion;
	}
	@GetMapping({"/","/inicio"})
	public String inicio() {
		return "index.jsp";
	}

	@GetMapping({"/registro/organizacion"})
	public String despliegaRegistroOrganizacion(@ModelAttribute("organizacion") Organizacion organizacion) {
		return "registroOrganizacion.jsp";
	}
	
	@GetMapping({"/organizacion"})
	public String despliegaOrganizacion() {
		return "organizacion.jsp";
	}

	@PostMapping("/registrar/organizacion")
	public String procesaRegistrarOrganizacion(@Valid @ModelAttribute("organizacion") Organizacion organizacion,
										  BindingResult validaciones,
										  HttpSession sesion) {
		validaciones = this.servicioOrganizacion.validarOrganizacion(validaciones, organizacion);
		if(validaciones.hasErrors()) {
			return "registroOrganizacion.jsp";
		}

		Organizacion organizacionCreado = this.servicioOrganizacion.insertarOrganizacion(organizacion);
		sesion.setAttribute("id_organizacion", organizacionCreado.getId());
		sesion.setAttribute("nombreOrganizacion_organizacion", organizacionCreado.getNombreOrganizacion());

		return "redirect:/canciones";
	}
}