package com.equipo5.proyecto.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.equipo5.proyecto.modelos.Organizacion;

@Repository
public interface RepositorioOrganizacion extends CrudRepository<Organizacion, Long>{
	List<Organizacion> findAll();
	Organizacion getByCorreo(String correo);
	Organizacion findByCorreo(String correo);
}