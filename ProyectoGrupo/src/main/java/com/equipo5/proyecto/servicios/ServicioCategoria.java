package com.equipo5.proyecto.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo5.proyecto.modelos.Categoria;
import com.equipo5.proyecto.repositorios.RepositorioCategoria;

@Service
public class ServicioCategoria {
	@Autowired
	private final RepositorioCategoria repositorioCategoria;
	
	public ServicioCategoria(RepositorioCategoria repositorioCategoria) {
		this.repositorioCategoria = repositorioCategoria;
	}
	public List<Categoria> obtenerCategorias() {
		return this.repositorioCategoria.findAll();
	}
	public Categoria obtenerCategoriaPorId(Long id) {
		return this.repositorioCategoria.findById(id).orElse(null);
	}
}
