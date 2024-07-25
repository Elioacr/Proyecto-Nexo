package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelos.Organizacion;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositorios.RepositorioOrganizacion;

import jakarta.transaction.Transactional;

@Service
public class ServicioOrganizacion {
	@Autowired
	private final RepositorioOrganizacion repositorioOrganizacion;

	public ServicioOrganizacion(RepositorioOrganizacion repositorioOrganizacion) {
		this.repositorioOrganizacion = repositorioOrganizacion;
	}

	public Organizacion insertarOrganizacion(Organizacion nuevaOrganizacion){
		return this.repositorioOrganizacion.save(nuevaOrganizacion);
	}

	public List<Organizacion> obtenerTodos(){
		return this.repositorioOrganizacion.findAll();
	}

	public Organizacion obtenerPorId(Long id) {
		return this.repositorioOrganizacion.findById(id).orElse(null);
	}
	
	public Organizacion obtenerPorCorreo(String correo) {
		return this.repositorioOrganizacion.getByCorreo(correo);
	}

	public void eliminarPorId(Long id) {
		this.repositorioOrganizacion.deleteById(id);
	}

	@Transactional
	public Organizacion actualizarOrganizacion(Organizacion organizacion) {
		return this.repositorioOrganizacion.save(organizacion);
	}
}