package com.equipo5.proyecto.modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity  
@Table(name = "eventos")  
public class Evento {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  

    @NotBlank
    @Size(min = 5)
    private String nombre;
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String ciudad;  
    
    @NotBlank
    @Size(min = 2, max = 50)
    private String ubicacion;  
    
    @NotBlank
    @Size(min = 2, max = 500)
    private String descripcion;  
    
    @NotNull
    private LocalDateTime fechaHora; // Usamos LocalDateTime para manejar fecha y hora
    
    @NotBlank
    @Size(min = 1, max = 1000, message = "Al menos un Voluntario debe participar en tu Evento")
    private String limiteVoluntarios;  
    
    @ManyToOne  
    @JoinColumn(name = "organizacion_id")  
    private Organizacion organizacion;  

    @ManyToOne  
    @JoinColumn(name = "categoria_id")  
    private Categoria categoria;
    
    @ManyToMany(mappedBy = "eventos", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
	public Evento() {}
	
	
	public Evento(String nombre, String ciudad, String ubicacion, String descripcion, LocalDateTime fechaHora,
			Organizacion organizacion, Categoria categoria, String limiteVoluntarios) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.fechaHora = fechaHora;
		this.organizacion = organizacion;
		this.categoria = categoria;
		this.limiteVoluntarios = limiteVoluntarios;
	}


	public Evento(Long id, String ciudad, String ubicacion, String descripcion, LocalDateTime fechaHora, 
			Organizacion organizacion, Categoria categoria, List<Usuario> usuarios, String limiteVoluntarios) {
		super();
		this.id = id;
		this.ciudad = ciudad;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.fechaHora = fechaHora;
		this.organizacion = organizacion;
		this.categoria = categoria;
		this.usuarios = usuarios;
		this.limiteVoluntarios = limiteVoluntarios;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	
	public String getFechaHoraFormateada() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMM 'de' yyyy 'a las' hh:mm a");
		return fechaHora.format(formatter);
	}
	
	public int getVoluntariosRegistrados() {
	    return usuarios != null ? usuarios.size() : 0;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public String getLimiteVoluntarios() {
		return limiteVoluntarios;
	}


	public void setLimiteVoluntarios(String limiteVoluntarios) {
		this.limiteVoluntarios = limiteVoluntarios;
	}
}  