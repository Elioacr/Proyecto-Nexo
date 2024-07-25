package com.equipo5.proyecto.modelos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginUsuario {
	@NotBlank(message="Por favor proporciona tu correo.")
	@Email(message="Por favor proporciona tu correo")
	private String usuarioCorreo;
	private String usuarioContraseña;
	
	@NotBlank
    private String tipoUsuario; // Puede ser "VOLUNTARIO" o "ORGANIZACION"

	public LoginUsuario() {}

	public String getUsuarioCorreo() {
		return usuarioCorreo;
	}

	public void setUsuarioCorreo(String usuarioCorreo) {
		this.usuarioCorreo = usuarioCorreo;
	}

	public String getUsuarioContraseña() {
		return usuarioContraseña;
	}

	public void setUsuarioContraseña(String usuarioContraseña) {
		this.usuarioContraseña = usuarioContraseña;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}