package com.equipo5.proyecto.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.equipo5.proyecto.modelos.Categoria;
import com.equipo5.proyecto.modelos.Evento;

@Repository
public interface RepositorioEvento extends CrudRepository<Evento, Long>{
	List<Evento> findAll();
	List<Evento> findByCategoria(Categoria categoria);
	List<Evento> findByOrganizacionId(Long organizacionId);
	Evento findByNombreAndCiudadAndDescripcion(String nombre, String ciudad, String Descripcion);
}
