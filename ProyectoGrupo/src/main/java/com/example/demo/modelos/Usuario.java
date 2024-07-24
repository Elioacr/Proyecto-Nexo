package com.example.demo.modelos;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity  
@Table(name = "usuarios")  
public class Usuario {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
    
    @NotNull
    @Size(min = 12)
    private Integer rut;

    @NotNull
    @Size(min = 2, max = 50)
    private String nombre;  
    
    @NotNull
    @Size(min = 2, max = 50)
    private String apellido;  
    
    @NotNull
    @Size(min = 2, max = 50)
    private String correo;  
    
    @NotNull
    @Size(min = 12)
    private Integer telefono;
    
    @NotNull
    @Size(min = 2, max = 50)
    private String ciudad;
    
    @NotNull
    @Size(min = 8)
    private String contraseña;
    
  //Uno relacion directa a eventos, para no crear el modelo de inscripciones
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "inscripciones",
    			joinColumns = @JoinColumn(name = "usuario_id"),
    			inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Evento> eventos;
    
    @Transient
	private String confirmarContraseña;

	public Usuario() {}
	
	public Usuario(Long id, Integer rut, String nombre, String apellido, String correo,
			Integer telefono, String ciudad, String contraseña, List<Evento> eventos, String confirmarContraseña) {
		super();
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.contraseña = contraseña;
		this.eventos = eventos;
		this.confirmarContraseña = confirmarContraseña;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
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

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
}