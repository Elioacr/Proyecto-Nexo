package com.example.demo.modelos;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity  
@Table(name = "organizaciones")  
public class Organizacion {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  

    @NotNull
    @Size(min = 12)
    private Integer rut;  
    
    @NotNull
    @Size(min = 2, max = 50)
    private String organizacion;  
    
    @NotNull
    @Size(min = 2, max = 50)
    private String correo;  
    
    @NotNull
    @Size(min = 12)
    private String telefono;
    
    @NotNull
    @Size(min = 8)
    private String contraseña;
    
    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Evento> eventos;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private LoginUsuario loginUsuario;
    
    @Transient
	private String confirmarContraseña;

	public Organizacion() {}

	public Organizacion(Long id, Integer rut, String organizacion, String correo,
			String telefono, String contraseña, List<Evento> eventos,
			String confirmarContraseña, LoginUsuario loginUsuario) {
		super();
		this.id = id;
		this.rut = rut;
		this.organizacion = organizacion;
		this.correo = correo;
		this.telefono = telefono;
		this.contraseña = contraseña;
		this.eventos = eventos;
		this.confirmarContraseña = confirmarContraseña;
		this.loginUsuario = loginUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRut() {
		return rut;
	}

	public void setRut(Integer rut) {
		this.rut = rut;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Evento> getEventos() {
		return eventos;
	}
	
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getConfirmarContraseña() {
		return confirmarContraseña;
	}

	public void setConfirmarContraseña(String confirmarContraseña) {
		this.confirmarContraseña = confirmarContraseña;
	}

	public LoginUsuario getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(LoginUsuario loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
}
