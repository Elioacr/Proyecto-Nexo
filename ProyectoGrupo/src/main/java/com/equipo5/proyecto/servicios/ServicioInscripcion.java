package com.equipo5.proyecto.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo5.proyecto.modelos.Evento;
import com.equipo5.proyecto.modelos.Inscripcion;
import com.equipo5.proyecto.modelos.Usuario;
import com.equipo5.proyecto.repositorios.RepositorioInscripcion;

@Service
public class ServicioInscripcion {
	@Autowired
	private final RepositorioInscripcion repositorioInscripcion;

	public ServicioInscripcion(RepositorioInscripcion repositorioInscripcion) {
		this.repositorioInscripcion = repositorioInscripcion;
	}
	
	public Inscripcion obtenerInscripcionPorUsuarioYEvento(Usuario usuario, Evento evento) {
		return this.repositorioInscripcion.findByUsuarioAndEvento(usuario, evento);
	}
	
	public Inscripcion insertarInscripcion(Inscripcion inscripcion) {
		return this.repositorioInscripcion.save(inscripcion);
	}
	public Inscripcion actualizarInscripcion(Inscripcion inscripcion) {
		return this.repositorioInscripcion.save(inscripcion);
	}
	public void eliminarInscripcion(Inscripcion inscripcion) {
		this.repositorioInscripcion.deleteById(inscripcion.getId());
	}
}
