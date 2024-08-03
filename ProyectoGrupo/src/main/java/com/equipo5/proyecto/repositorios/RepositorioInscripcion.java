package com.equipo5.proyecto.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.equipo5.proyecto.modelos.Evento;
import com.equipo5.proyecto.modelos.Inscripcion;
import com.equipo5.proyecto.modelos.Usuario;

@Repository
public interface RepositorioInscripcion extends CrudRepository<Inscripcion, Long>{
	Inscripcion findByUsuarioAndEvento(Usuario usuario, Evento evento);
}
