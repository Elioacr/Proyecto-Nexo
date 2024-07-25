package com.equipo5.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

import com.equipo5.proyecto.modelos.LoginUsuario;
import com.equipo5.proyecto.modelos.Organizacion;
import com.equipo5.proyecto.modelos.Usuario;
import com.equipo5.proyecto.servicios.ServicioOrganizacion;
import com.equipo5.proyecto.servicios.ServicioUsuario;

import ch.qos.logback.core.model.Model;


@Controller
public class ControladorUsuario {

	@Autowired
	private final ServicioUsuario servicioUsuario;
	private final ServicioOrganizacion servicioOrganizacion;

	public ControladorUsuario(ServicioUsuario servicioUsuario, ServicioOrganizacion servicioOrganizacion) {
		this.servicioUsuario = servicioUsuario;
		this.servicioOrganizacion = servicioOrganizacion;
	}

	@GetMapping({"/", "/login", "/registro"})
	public String despliegaLoginRegistro(@ModelAttribute("usuario") Usuario usuario,
										 @ModelAttribute("loginUsuario") LoginUsuario loginUsuario) {
		return "index.jsp";
	}

	@PostMapping("/registrar/usuario")
	public String procesaRegistrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
										  BindingResult validaciones,
										  @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
										  HttpSession sesion) {
		validaciones = this.servicioUsuario.validarRegistro(validaciones, usuario);
		if(validaciones.hasErrors()) {
			return "index.jsp";
		}

		Usuario usuarioCreado = this.servicioUsuario.insertarUsuario(usuario);
		sesion.setAttribute("id_usuario", usuarioCreado.getId());
		sesion.setAttribute("nombre_usuario", usuarioCreado.getNombre());

		return "redirect:/canciones";
	}

	@PostMapping("/procesa/login")
	public String procesaLogin(@Valid @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
	                           BindingResult validaciones,
	                           HttpSession sesion) {
	    validaciones = this.servicioUsuario.validarLogin(validaciones, loginUsuario);
	    if (validaciones.hasErrors()) {
	        return "index.jsp";
	    }

	    String tipoUsuario = loginUsuario.getTipoUsuario();
	    if ("VOLUNTARIO".equals(tipoUsuario)) {
	        Usuario usuarioActual = servicioUsuario.obtenerPorCorreo(loginUsuario.getUsuarioCorreo());
	        sesion.setAttribute("id_usuario", usuarioActual.getId());
	        sesion.setAttribute("nombre_usuario", usuarioActual.getNombre());
	        return "redirect:/voluntarioDashboard";
	    } else if ("ORGANIZACION".equals(tipoUsuario)) {
	        Organizacion organizacionActual = servicioOrganizacion.obtenerPorCorreo(loginUsuario.getUsuarioCorreo());
	        sesion.setAttribute("id_organizacion", organizacionActual.getId());
	        sesion.setAttribute("nombre_organizacion", organizacionActual.getOrganizacion());
	        return "redirect:/organizacionDashboard";
	    }
	    // En caso de que el tipo de usuario no sea reconocido, redirigir al login
	    return "index.jsp";
	}
}
