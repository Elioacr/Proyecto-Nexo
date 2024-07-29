package com.equipo5.proyecto.controladores;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

import com.equipo5.proyecto.modelos.Evento;
import com.equipo5.proyecto.modelos.LoginUsuario;
import com.equipo5.proyecto.modelos.Organizacion;
import com.equipo5.proyecto.modelos.Usuario;
import com.equipo5.proyecto.servicios.ServicioEventos;
import com.equipo5.proyecto.servicios.ServicioOrganizacion;
import com.equipo5.proyecto.servicios.ServicioUsuario;


@Controller
public class ControladorUsuario {

	@Autowired
	private final ServicioUsuario servicioUsuario;
	private final ServicioOrganizacion servicioOrganizacion;
	private final ServicioEventos servicioEventos;

	public ControladorUsuario(ServicioUsuario servicioUsuario, ServicioOrganizacion servicioOrganizacion,ServicioEventos servicioEventos) {
		this.servicioUsuario = servicioUsuario;
		this.servicioOrganizacion = servicioOrganizacion;
		this.servicioEventos = servicioEventos;
	}

	@GetMapping("/registro/usuario")
	public String despliegaRegistroUsuario(@ModelAttribute("usuario") Usuario usuario) {
		return "registroVoluntario.jsp";
	}
	
	@GetMapping("/login")
	public String despliegaLogin(@ModelAttribute("loginUsuario") LoginUsuario loginUsuario) {
		return "login.jsp";
	}
	
	@GetMapping("/voluntario")
	public String despliegaVoluntario(HttpSession sesion, Model model) {
	    if (sesion.getAttribute("id_usuario") == null) {
	        return "redirect:/registro/usuario";
	    }

	    Long usuarioId = (Long) sesion.getAttribute("id_usuario");
	    Usuario usuario = servicioUsuario.obtenerPorId(usuarioId);
	    List<Evento> eventosUsuario = usuario.getEventos();
	    List<Evento> eventosOrganizacion = servicioEventos.obtenerEventos();
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMM 'de' yyyy 'a las' hh:mm a");
	    List<Map<String, Object>> eventosConFechasFormateadas = eventosOrganizacion.stream().map(evento -> {
	        Map<String, Object> eventoMap = new HashMap<>();
	        eventoMap.put("id", evento.getId());
	        eventoMap.put("nombre", evento.getNombre());
	        eventoMap.put("descripcion", evento.getDescripcion());
	        eventoMap.put("ciudad", evento.getCiudad());
	        eventoMap.put("categoria", evento.getCategoria().getCategoria());
	        eventoMap.put("fechaHora", evento.getFechaHora().format(formatter)); // Formatear la fecha
	        return eventoMap;
	    }).collect(Collectors.toList());

	    model.addAttribute("eventosUsuario", eventosUsuario);
	    model.addAttribute("eventosConFechasFormateadas", eventosConFechasFormateadas);
	    return "voluntario.jsp";
	}

	@PostMapping("/participar/{eventoId}")
	public String participarEnEvento(@PathVariable Long eventoId, HttpSession sesion) {
	    Long usuarioId = (Long) sesion.getAttribute("id_usuario");
	    servicioEventos.registrarUsuarioEnEvento(usuarioId, eventoId);
	    return "redirect:/voluntario";
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
