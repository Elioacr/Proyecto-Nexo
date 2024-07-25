package com.equipo5.proyecto.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo5.proyecto.modelos.Categoria;
import com.equipo5.proyecto.modelos.Evento;
import com.equipo5.proyecto.repositorios.RepositorioEvento;

@Service
public class ServicioEventos {
	@Autowired
	private final RepositorioEvento repositorioEvento;
	
	public ServicioEventos(RepositorioEvento repositorioEvento) {
		this.repositorioEvento = repositorioEvento;
	}
	public List<Evento> obtenerEventos() {
		return this.repositorioEvento.findAll();
	}
	public List<Evento> obtenerEventosPorCategoria(Categoria categoria) {
		return this.repositorioEvento.findByCategoria(categoria);
	}
	public Evento obtenerEventoPorId(Long id) {
		return this.repositorioEvento.findById(id).orElse(null);
	}
	public Evento insertarEvento(Evento evento) {
		return this.repositorioEvento.save(evento);
	}
	public Evento actualizarEvento(Evento evento) {
		return this.repositorioEvento.save(evento);
	}
	public void eliminarEvento(Evento evento) {
		this.repositorioEvento.deleteById(evento.getId());
	}
}
