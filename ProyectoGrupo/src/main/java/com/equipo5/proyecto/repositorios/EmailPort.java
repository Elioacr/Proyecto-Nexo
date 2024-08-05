package com.equipo5.proyecto.repositorios;

import com.equipo5.proyecto.modelos.EmailBody;

public interface EmailPort {
	public boolean sendEmail(EmailBody emailBody);
}