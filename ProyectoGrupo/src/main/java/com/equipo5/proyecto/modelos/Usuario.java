package com.equipo5.proyecto.modelos;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity  
@Table(name = "usuarios")  
public class Usuario {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
    
    @NotNull
    @Digits(integer = 12, fraction = 0)
    private Integer rut;

    @NotBlank
    @Size(min = 2, max = 50)
    private String nombre;  
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String apellido;  
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String correo;  
    
    @NotNull
    @Digits(integer = 12, fraction = 0)
    private Integer telefono;
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String ciudad;
    
    @NotBlank
    @Size(min = 8)
    private String contraseña;
    
    @Transient
	private String confirmarContraseña;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "inscripciones",
    			joinColumns = @JoinColumn(name = "usuario_id"),
    			inverseJoinColumns = @JoinColumn(name = "evento_id"))
    private List<Evento> eventos;
	public Usuario() {}
	

	public Usuario(Long id, Integer rut, String nombre, String apellido, String correo, Integer telefono, String ciudad,
			String contraseña, String confirmarContraseña, List<Evento> eventos) {
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.contraseña = contraseña;
		this.confirmarContraseña = confirmarContraseña;
		this.eventos = eventos;
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

}