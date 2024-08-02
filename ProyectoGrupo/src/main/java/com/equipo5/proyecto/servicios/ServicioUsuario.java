package com.equipo5.proyecto.servicios;


import java.time.Period;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.equipo5.proyecto.modelos.Categoria;
import com.equipo5.proyecto.modelos.LoginUsuario;
import com.equipo5.proyecto.modelos.Usuario;
import com.equipo5.proyecto.repositorios.RepositorioUsuario;



@Service
public class ServicioUsuario {

	@Autowired
	private final RepositorioUsuario resRepositorioUsuario;

	public ServicioUsuario(RepositorioUsuario resRepositorioUsuario) {
		this.resRepositorioUsuario = resRepositorioUsuario;
	}

	public BindingResult validarRegistro(BindingResult validaciones, Usuario usuario) {

		if(! usuario.getContraseña().equals(usuario.getConfirmarContraseña())) {
			validaciones.rejectValue("confirmarContraseña", "NoCoinciden", "Las contraseñas no coinciden.");
		}
		if(this.obtenerPorCorreo(usuario.getCorreo()) != null) {
			validaciones.rejectValue("correo", "Existente", "Este correo ya está en uso!");
		}
		if(!rutValido(usuario.getRut())) {
			validaciones.rejectValue("rut", "RutInvalido", "Rut Invalido");
		}
		return validaciones;
	}
	public boolean rutValido(String rut) {
		if(rut.length() != 12) {
			return false;
		}
		StringBuilder sb = new StringBuilder(rut);
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		try {
			if(Integer.parseInt(Character.toString(rut.charAt(0))) > 3) { // Si el primer digito es mayor que 3 no es una persona
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		String reverseRut = sb.reverse().toString();
		
		int mult = 2;
		int suma = 0;
		for(char c : reverseRut.toCharArray()) {
			if(!Character.toString(c).equals(".")) {
				try {
					suma += Character.getNumericValue(c) * mult;
					mult++;
					if(mult == 8) {
						mult = 2;
					}
				} catch (Exception e) {
					return false;
				}
			}
		}
		int resultado = 11 - suma % 11;
		String digitoVerificador;
		switch(resultado) {
			case 11: 
				digitoVerificador = "0";
				break;
			case 10: 
				digitoVerificador = "K";
				break;
			default:
				digitoVerificador = Integer.toString(resultado);
		}
		
		char ultimoDigito = rut.charAt(rut.length() - 1);
		return digitoVerificador.equals(String.valueOf(ultimoDigito));
	}
	
	public BindingResult validarLoginVoluntario(BindingResult validaciones, LoginUsuario loginUsuario) {
        Usuario usuarioActual = this.obtenerPorCorreo(loginUsuario.getUsuarioCorreo());
        if (usuarioActual == null) {
            validaciones.rejectValue("usuarioCorreo", "NoExistente", "Este usuario no existe");
        } else if (!BCrypt.checkpw(loginUsuario.getUsuarioContraseña(), usuarioActual.getContraseña())) {
            validaciones.rejectValue("usuarioContraseña", "NoValida", "Credenciales no válidas");
        }
        return validaciones;
    }

	public Integer calcularEdad(LocalDate fechaNacimiento) {
	    if (fechaNacimiento == null) {
	        return 0;
	    }
	    LocalDate today = LocalDate.now();
	    return Period.between(fechaNacimiento, today).getYears();
	}

    public Usuario registrarUsuario(Usuario usuario) {
        if (!esMayorDeEdad(usuario.getFechaNacimiento())) {
            throw new IllegalArgumentException("El usuario debe ser mayor de edad para registrarse.");
        }
        String contraseñaEncriptada = BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt());
        usuario.setContraseña(contraseñaEncriptada);
        return this.resRepositorioUsuario.save(usuario);
    }

    public boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        return calcularEdad(fechaNacimiento) >= 18;
    }

	
	public Usuario actualizarUsuario(Usuario usuario) {
		return this.resRepositorioUsuario.save(usuario);
	}
	
	public List<Usuario> obtenerPorEventosDeCategoria(Categoria categoria) {
		List<Usuario> usuarios = this.obtenerTodos();
		
		return usuarios.stream()
	            .filter(user -> user.getEventos().stream()
	                .anyMatch(event -> event.getCategoria().equals(categoria)))
	            .toList();
	}
	
	public int contarEventosDeCategoria(Usuario usuario, Categoria categoria) {
		return (int)usuario.getEventos().stream()
				.filter(e -> e.getCategoria().equals(categoria))
				.count();
	}
	public List<Usuario> obtenerTodos() {
		return this.resRepositorioUsuario.findAll();
	}
	public Usuario obtenerPorCorreo(String correo) {
		return this.resRepositorioUsuario.getByCorreo(correo);
	}

	public Usuario obtenerPorId(Long id) {
		return this.resRepositorioUsuario.findById(id).orElse(null);
	}
}