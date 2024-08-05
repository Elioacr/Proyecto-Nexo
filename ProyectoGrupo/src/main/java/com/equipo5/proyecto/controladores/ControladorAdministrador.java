package com.equipo5.proyecto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.equipo5.proyecto.modelos.EmailBody;
import com.equipo5.proyecto.modelos.Organizacion;
import com.equipo5.proyecto.modelos.Usuario;
import com.equipo5.proyecto.servicios.EmailService;
import com.equipo5.proyecto.servicios.ServicioOrganizacion;
import com.equipo5.proyecto.servicios.ServicioUsuario;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorAdministrador {

    @Autowired
    private ServicioOrganizacion servicioOrganizacion;

    @Autowired
    private ServicioUsuario servicioUsuario;
    @Autowired
    private EmailService emailService;

    @GetMapping("/admin/organizaciones")
    public String listarOrganizaciones(Model model, HttpSession sesion) {
        Long idUsuario = (Long) sesion.getAttribute("id_usuario");

        if (idUsuario == null) {
            return "redirect:/login";
        }

        Usuario usuario = servicioUsuario.obtenerPorId(idUsuario);

        if (usuario == null || !usuario.getEsAdministrador()) {
            return "redirect:/";
        }

        List<Organizacion> organizaciones = servicioOrganizacion.obtenerTodos();
        model.addAttribute("organizaciones", organizaciones);
        return "administrador.jsp";
    }

    @PostMapping("/admin/organizaciones/{id}/verificar")
    public String verificarOrganizacion(@PathVariable("id") Long organizacionId, HttpSession sesion) {
        Long idUsuario = (Long) sesion.getAttribute("id_usuario");

        if (idUsuario == null) {
            return "redirect:/login";
        }

        Usuario usuario = servicioUsuario.obtenerPorId(idUsuario);

        if (usuario == null || !usuario.getEsAdministrador()) {
            return "redirect:/";
        }

        Organizacion organizacion = servicioOrganizacion.obtenerPorId(organizacionId);
        organizacion.setVerificado(true);
        servicioOrganizacion.actualizarOrganizacion(organizacion);
        
        EmailBody emailBody = new EmailBody();
        emailBody.setEmail(organizacion.getCorreo());
        emailBody.setSubject("Verificación de Organización");
        emailBody.setContent("Tu cuenta ha sido verificada con éxito.");

        boolean enviado = emailService.sendEmail(emailBody);

        if (enviado) {
            System.out.println("Correo de verificación enviado a la organización.");
        } else {
            System.out.println("Error al enviar el correo de verificación.");
        }

        return "redirect:/admin/organizaciones";
    }
}