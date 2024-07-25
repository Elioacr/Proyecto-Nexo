package com.equipo5.proyecto.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.equipo5.proyecto.modelos.Usuario;

@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Long> {
	List<Usuario> findAll();
	Usuario getByCorreo(String correo);
	Usuario findByCorreo(String correo);
}