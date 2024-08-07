package com.equipo5.proyecto.modelos;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity  
@Table(name = "organizaciones")  
public class Organizacion {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  

    @NotBlank
    private String rut;
    
    @NotBlank
    @Size(min = 2, max = 50, message = "El nombre tiene que tener entre 2 y 50 dígitos")
    private String nombreOrganizacion;  
    
    @NotBlank
    @Size(min = 6, max = 40, message = "El correo tiene que tener entre 6 y 40 dígitos")
    private String correo;  
    
    @NotBlank
    @Min(value = 10000000, message = "Telefono Invalido")
    @Digits(integer = 12, fraction = 0, message = "Telefono Invalido")
    private String telefono;
    
    @URL
    @Column(name = "web_link", nullable = true)
    private String webLink;
    
    @NotBlank
    @Size(min = 8, message = "La contraseña tiene que tener al menos 8 dígitos")
    private String contraseña;
    
    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Evento> eventos;
    
    @Transient
	private String confirmarContraseña;
    
    private boolean verificado;

	public Organizacion() {}
	
	
	public Organizacion(String rut, String nombreOrganizacion, String correo, String telefono, String contraseña) {
		this.rut = rut;
		this.nombreOrganizacion = nombreOrganizacion;
		this.correo = correo;
		this.telefono = telefono;
		this.contraseña = contraseña;
	}


	public Organizacion(String rut, String nombreOrganizacion, String correo, String contraseña, String telefono, String webLink,
			boolean verificado) {
		this.rut = rut;
		this.nombreOrganizacion = nombreOrganizacion;
		this.correo = correo;
		this.contraseña = contraseña;
		this.telefono = telefono;
		this.webLink = webLink;
		this.verificado = verificado;
	}


	public Organizacion(Long id, String rut, String nombreOrganizacion, String correo,
			String telefono, String contraseña, List<Evento> eventos,
			String confirmarContraseña, String webLink, boolean verificado) {
		this.id = id;
		this.rut = rut;
		this.nombreOrganizacion = nombreOrganizacion;
		this.correo = correo;
		this.telefono = telefono;
		this.contraseña = contraseña;
		this.eventos = eventos;
		this.confirmarContraseña = confirmarContraseña;
		this.webLink = webLink;
		this.verificado = verificado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombreOrganizacion() {
		return nombreOrganizacion;
	}

	public void setNombreOrganizacion(String nombreOrganizacion) {
		this.nombreOrganizacion = nombreOrganizacion;
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


	public String getWebLink() {
		return webLink;
	}


	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}

	public boolean getVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}
}
