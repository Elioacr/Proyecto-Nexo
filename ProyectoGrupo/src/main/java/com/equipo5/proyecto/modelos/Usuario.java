package com.equipo5.proyecto.modelos;

import java.time.LocalDate;
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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity  
@Table(name = "usuarios")  
public class Usuario {  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
    
    @NotBlank
    private String rut;

    @NotBlank
    @Size(min = 2, max = 20, message = "El nombre tiene que tener al menos 2 dígitos")
    private String nombre;  
    
    @NotBlank
    @Size(min = 2, max = 20, message = "El apellido tiene que tener al menos 2 dígitos")
    private String apellido;  
    
    @NotBlank
    @Size(min = 6, max = 30, message = "El correo tiene que tener al menos 6 dígitos")
    private String correo;  
    
    @NotNull
    @Min(value = 10000000, message = "Telefono Invalido")
    @Digits(integer = 12, fraction = 0, message = "Telefono Invalido")
    private Integer telefono;
    
    @NotBlank
    @Size(min = 2, max = 30, message = "La ciudad tiene que tener al menos 6 dígitos")
    private String ciudad;
    
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;
    
    @NotBlank
    @Size(min = 8, message = "La contraseña tiene que tener al menos 8 caracteres")
    private String contraseña;
    
    @Transient
	private String confirmarContraseña;
    
    private Integer edad;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "inscripciones",
    			joinColumns = @JoinColumn(name = "usuario_id"),
    			inverseJoinColumns = @JoinColumn(name = "evento_id"))
    private List<Evento> eventos;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;
    
	public Usuario() {}
	

	public Usuario(Long id, String rut, String nombre, String apellido, String correo, Integer telefono, String ciudad,
			String contraseña, String confirmarContraseña, List<Evento> eventos, LocalDate fechaNacimiento, Integer edad) {
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
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
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


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}


	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}
	
	public int obtenerAsistenciasConfirmadas() {
		return (int)this.inscripciones.stream().filter(i -> this.asistenciaConfirmada(i.getEvento())).count();
	}
	public int obtenerAsistenciasConfirmadas(Categoria categoria) {
		return (int)this.inscripciones.stream().filter(i -> i.getEvento().getCategoria().equals(categoria) && this.asistenciaConfirmada(i.getEvento())).count();
	}
	public boolean asistenciaConfirmada(Evento evento) {
		Inscripcion inscripcion = this.inscripciones.stream().filter(i -> i.getUsuario().equals(this) && i.getEvento().equals(evento)).findFirst().orElse(null);
		return inscripcion == null ? false : inscripcion.isAsistenciaConfirmada();
	}
}