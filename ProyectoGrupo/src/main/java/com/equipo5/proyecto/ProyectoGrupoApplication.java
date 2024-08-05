package com.equipo5.proyecto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.equipo5.proyecto.clientes.SSLUtils;
import com.equipo5.proyecto.modelos.Categoria;
import com.equipo5.proyecto.servicios.ServicioCategoria;

@SpringBootApplication
public class ProyectoGrupoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoGrupoApplication.class, args);
		SSLUtils.disableCertificateValidation();
	}
	
	//Añadir categorias por defecto, Solo para las pruebas
	@Bean
    public CommandLineRunner commandLineRunner(ServicioCategoria servicioCategoria) {
        return args -> {
            if(servicioCategoria.obtenerCategorias().size() == 0) {
            	List<Categoria> categorias = new ArrayList<>();
            	categorias.add(new Categoria("Animales"));
            	categorias.add(new Categoria("Agricultura"));
            	categorias.add(new Categoria("Comunidad"));
            	categorias.add(new Categoria("Cuidado Infantil"));
            	categorias.add(new Categoria("Educación"));
            	categorias.add(new Categoria("Trabajo Social"));
            	categorias.add(new Categoria("Construcción"));
            	categorias.add(new Categoria("Reforestación"));
            	categorias.add(new Categoria("Salud"));
            	categorias.add(new Categoria("Ayuda Humanitaria"));
                categorias.add(new Categoria("Cultura y Arte"));
                categorias.add(new Categoria("Tecnología y Ciencias"));
                categorias.add(new Categoria("Derechos Humanos"));
                categorias.add(new Categoria("Desarrollo Económico"));
                categorias.add(new Categoria("Asistencia a Personas Mayores"));
                categorias.add(new Categoria("Apoyo a Personas con Discapacidad"));
                categorias.add(new Categoria("Empoderamiento de la Mujer"));
                categorias.add(new Categoria("Refugio y Vivienda"));
                categorias.add(new Categoria("Emergencias y Desastres"));
                categorias.add(new Categoria("Desarrollo Juvenil"));
                categorias.add(new Categoria("Asistencia Alimentaria"));
                categorias.add(new Categoria("Educación para Adultos"));
                
                for(Categoria categoria : categorias) {
                	servicioCategoria.insertarCategoria(categoria);
                }
            }
        };
    }
}
