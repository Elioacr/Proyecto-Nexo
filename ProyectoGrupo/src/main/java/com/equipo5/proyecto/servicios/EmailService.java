package com.equipo5.proyecto.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.equipo5.proyecto.modelos.EmailBody;
import com.equipo5.proyecto.repositorios.EmailPort;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailPort{

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender sender;

	@Override
	public boolean sendEmail(EmailBody emailBody)  {
		LOGGER.info("EmailBody: {}", emailBody.toString());
		return sendEmailTool(emailBody.getContent(),emailBody.getEmail(), emailBody.getSubject());
	}


	private boolean sendEmailTool(String textMessage, String email,String subject) {
		boolean send = false;
	    MimeMessage message = sender.createMimeMessage();		
		try {
	        // El parámetro `true` permite enviar correos en HTML
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);  

	        // Configurar el mensaje
	        helper.setTo(email);
	        helper.setSubject(subject);

	        // Contenido HTML del correo
	        String htmlContent = "<html><body>"
	            + "<p>Tu cuenta ha sido verificada con éxito.</p>"
	            + "<p>Ahora todos los eventos que crees serán publicados.</p>"
	            + "<p>Felicidades.</p>"
	            + "<br>"
	            + "<br>"
	            + "<br>"
	            + "<p style='text-align: center;'><img src='cid:logo' width='300'></p>"  // Utiliza CID para referenciar la imagen
	            + "</body></html>";

	        // Establecer el contenido HTML
	        helper.setText(htmlContent, true);

	        // Adjuntar la imagen como recurso embebido
	        Resource logo = new ClassPathResource("static/images/minilogo.png");
	        helper.addInline("logo", logo);

	        // Enviar el correo
	        sender.send(message);
	        send = true;
	        LOGGER.info("Mail enviado!");
	    } catch (MessagingException e) {
	        LOGGER.error("Hubo un error al enviar el mail: {}", e);
	    }
	    return send;
	}
}