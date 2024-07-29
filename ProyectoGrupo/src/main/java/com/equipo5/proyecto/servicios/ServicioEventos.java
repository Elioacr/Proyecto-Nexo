package com.equipo5.proyecto.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo5.proyecto.modelos.Categoria;
import com.equipo5.proyecto.modelos.Evento;
import com.equipo5.proyecto.modelos.Usuario;
import com.equipo5.proyecto.repositorios.RepositorioEvento;
import com.equipo5.proyecto.repositorios.RepositorioUsuario;

@Service
public class ServicioEventos {
	@Autowired
	private final RepositorioEvento repositorioEvento;
	private final RepositorioUsuario repositorioUsuario;
	
	public ServicioEventos(RepositorioUsuario repositorioUsuario, RepositorioEvento repositorioEvento) {
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioEvento = repositorioEvento;
	}

    public void registrarUsuarioEnEvento(Long usuarioId, Long eventoId) {
        Usuario usuario = repositorioUsuario.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Evento evento = repositorioEvento.findById(eventoId)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));

        if (!usuario.getEventos().contains(evento)) {
            usuario.getEventos().add(evento);
            repositorioUsuario.save(usuario);
        }
    }
    
    public List<Evento> obtenerEventosPorOrganizacion(Long organizacionId) {
        return this.repositorioEvento.findByOrganizacionId(organizacionId);
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
