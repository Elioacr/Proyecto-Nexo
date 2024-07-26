package com.equipo5.proyecto.servicios;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


import com.equipo5.proyecto.modelos.Organizacion;
import com.equipo5.proyecto.modelos.Usuario;
import com.equipo5.proyecto.repositorios.RepositorioOrganizacion;
import com.equipo5.proyecto.modelos.LoginUsuario;

import jakarta.transaction.Transactional;

@Service
public class ServicioOrganizacion {
	@Autowired
	private final RepositorioOrganizacion repositorioOrganizacion;

	public ServicioOrganizacion(RepositorioOrganizacion repositorioOrganizacion) {
		this.repositorioOrganizacion = repositorioOrganizacion;
	}

	/*public Organizacion insertarOrganizacion(Organizacion nuevaOrganizacion){
		return this.repositorioOrganizacion.save(nuevaOrganizacion);
	}*/

	public List<Organizacion> obtenerTodos(){
		return this.repositorioOrganizacion.findAll();
	}

	public Organizacion obtenerPorId(Long id) {
		return this.repositorioOrganizacion.findById(id).orElse(null);
	}
	
	public void eliminarPorId(Long id) {
		this.repositorioOrganizacion.deleteById(id);
	}

	public Organizacion obtenerPorCorreo(String correo) {
	    return this.repositorioOrganizacion.getByCorreo(correo);
	}

	public BindingResult validarLoginOrganizacion(BindingResult validaciones, LoginUsuario loginUsuario) {
        Organizacion organizacionActual = this.obtenerPorCorreo(loginUsuario.getUsuarioCorreo());
        if (organizacionActual == null) {
            validaciones.rejectValue("usuarioCorreo", "NoExistente", "Esta organización no existe");
        } else if (!BCrypt.checkpw(loginUsuario.getUsuarioContraseña(), organizacionActual.getContraseña())) {
            validaciones.rejectValue("usuarioContraseña", "NoValida", "Credenciales no válidas");
        }
        return validaciones;
    }
	
	public BindingResult validarOrganizacion(BindingResult validaciones, Organizacion organizacion) {

		if(! organizacion.getContraseña().equals(organizacion.getConfirmarContraseña())) {
			validaciones.rejectValue("confirmarContraseña", "NoCoinciden", "Las contraseñas no coinciden.");
		}

		if(this.obtenerPorCorreo(organizacion.getCorreo()) != null) {
			validaciones.rejectValue("correo", "Existente", "Este correo ya está en uso!");
		}

		return validaciones;
	}
	
	public Organizacion insertarOrganizacion(Organizacion organizacion) {
		String contraseñaEncriptada = BCrypt.hashpw(organizacion.getContraseña(), BCrypt.gensalt());
		organizacion.setContraseña(contraseñaEncriptada);
		return this.repositorioOrganizacion.save(organizacion);
	}
	
	@Transactional
	public Organizacion actualizarOrganizacion(Organizacion organizacion) {
		return this.repositorioOrganizacion.save(organizacion);
	}
}