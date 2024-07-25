package com.equipo5.proyecto.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.equipo5.proyecto.modelos.Categoria;

@Repository
public interface RepositorioCategoria extends CrudRepository<Categoria, Long>{
	List<Categoria> findAll();
}
