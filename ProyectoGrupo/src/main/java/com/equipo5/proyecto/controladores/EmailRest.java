package com.equipo5.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.equipo5.proyecto.modelos.EmailBody;
import com.equipo5.proyecto.repositorios.EmailPort;

@RestController
@RequestMapping(value = "/email")
public class EmailRest {

	@Autowired
	private EmailPort emailPort;

	@PostMapping(value = "/send")
	@ResponseBody
	public boolean SendEmail(@RequestBody EmailBody emailBody)  {
		return emailPort.sendEmail(emailBody);
	}

}