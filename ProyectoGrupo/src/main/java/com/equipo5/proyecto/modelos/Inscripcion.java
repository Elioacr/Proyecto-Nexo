package com.equipo5.proyecto.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inscripciones")
public class Inscripcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evento_id")
	private Evento evento;
	
	private boolean asistenciaConfirmada = false;

	public Inscripcion() {}

	public Inscripcion(Usuario usuario, Evento evento) {
		this.usuario = usuario;
		this.evento = evento;
		this.asistenciaConfirmada = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public boolean isAsistenciaConfirmada() {
		return asistenciaConfirmada;
	}

	public void setAsistenciaConfirmada(boolean asistenciaConfirmada) {
		this.asistenciaConfirmada = asistenciaConfirmada;
	}
}
