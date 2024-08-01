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

import com.equipo5.proyecto.modelos.Categoria;
import com.equipo5.proyecto.modelos.Evento;
import com.equipo5.proyecto.modelos.LoginUsuario;
import com.equipo5.proyecto.modelos.Organizacion;
import com.equipo5.proyecto.modelos.Usuario;
import com.equipo5.proyecto.servicios.ServicioCategoria;
import com.equipo5.proyecto.servicios.ServicioEventos;
import com.equipo5.proyecto.servicios.ServicioOrganizacion;
import com.equipo5.proyecto.servicios.ServicioUsuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class ControladorUsuario {

	@Autowired
	private final ServicioUsuario servicioUsuario;
	private final ServicioOrganizacion servicioOrganizacion;
	private final ServicioEventos servicioEventos;
	private final ServicioCategoria servicioCategoria;

	public ControladorUsuario(ServicioUsuario servicioUsuario, ServicioOrganizacion servicioOrganizacion,ServicioEventos servicioEventos,  ServicioCategoria servicioCategoria) {
		this.servicioUsuario = servicioUsuario;
		this.servicioOrganizacion = servicioOrganizacion;
		this.servicioEventos = servicioEventos;
		this.servicioCategoria = servicioCategoria;
	}
	
	@GetMapping({"/","/inicio"})
	public String inicio() {
		return "index.jsp";
	}

	@GetMapping("/registro/usuario")
	public String despliegaRegistroUsuario(@ModelAttribute("usuario") Usuario usuario) {
		return "registroVoluntario.jsp";
	}
	
	@GetMapping("/login")
	public String despliegaLogin(@ModelAttribute("loginUsuario") LoginUsuario loginUsuario) {
		return "login.jsp";
	}
	
	@PostMapping("/logout")
	public String cerrarSesion(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/voluntario")
	public String despliegaVoluntario(HttpSession sesion, Model model) {
	    if (sesion.getAttribute("id_usuario") == null) {
	        return "redirect:/registro/usuario";
	    }

	    Long usuarioId = (Long) sesion.getAttribute("id_usuario");
	    Usuario usuario = servicioUsuario.obtenerPorId(usuarioId);
	    List<Evento> eventosUsuario = usuario.getEventos();
	    List<Evento> eventos = servicioEventos.obtenerEventos();
	    List<Categoria> categorias = servicioCategoria.obtenerCategorias();
	    List<Organizacion> organizaciones = servicioOrganizacion.obtenerTodos();

	    model.addAttribute("eventosUsuario", eventosUsuario);
	    model.addAttribute("eventos", eventos);
	    model.addAttribute("categorias", categorias);
	    model.addAttribute("usuario", usuario);
	    model.addAttribute("organizaciones", organizaciones);
	    return "voluntario.jsp";
	}

	@PostMapping("/registrar/usuario")
	public String procesaRegistrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
										  BindingResult validaciones,
										  @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
										  HttpSession sesion) {
		validaciones = this.servicioUsuario.validarRegistro(validaciones, usuario);
		if(validaciones.hasErrors()) {
			return "registroVoluntario.jsp";
		}

		Usuario usuarioCreado = this.servicioUsuario.insertarUsuario(usuario);
		sesion.setAttribute("id_usuario", usuarioCreado.getId());
		sesion.setAttribute("nombre_usuario", usuarioCreado.getNombre());

		return "redirect:/voluntario";
	}

	@PostMapping("/procesa/login")
	public String procesaLogin(@Valid @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
	                           BindingResult validaciones,
	                           HttpSession sesion) {
	    String tipoUsuario = loginUsuario.getTipoUsuario();
	    
	    if ("VOLUNTARIO".equals(tipoUsuario)) {
	        validaciones = this.servicioUsuario.validarLoginVoluntario(validaciones, loginUsuario);
	        if (validaciones.hasErrors()) {
	            return "login.jsp";
	        }

	        Usuario usuarioActual = servicioUsuario.obtenerPorCorreo(loginUsuario.getUsuarioCorreo());
	        sesion.setAttribute("id_usuario", usuarioActual.getId());
	        sesion.setAttribute("nombre_usuario", usuarioActual.getNombre());
	        return "redirect:/voluntario";
	        
	    } else if ("ORGANIZACION".equals(tipoUsuario)) {
	        validaciones = this.servicioOrganizacion.validarLoginOrganizacion(validaciones, loginUsuario);
	        if (validaciones.hasErrors()) {
	            return "login.jsp";
	        }

	        Organizacion organizacionActual = servicioOrganizacion.obtenerPorCorreo(loginUsuario.getUsuarioCorreo());
	        sesion.setAttribute("id_organizacion", organizacionActual.getId());
	        sesion.setAttribute("nombreOrganizacion_organizacion", organizacionActual.getNombreOrganizacion());
	        return "redirect:/organizacion";
	    }
	    
	    // En caso de que el tipo de usuario no sea reconocido, redirigir al login
	    return "login.jsp";
	}
}
