package com.nuevospa.gestion;

import com.nuevospa.gestion.jpa.entity.EstadosTareasEntity;
import com.nuevospa.gestion.jpa.entity.UsuariosEntity;
import com.nuevospa.gestion.jpa.repository.EstadosTareasRepository;
import com.nuevospa.gestion.jpa.repository.UsuariosRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionApplication.class, args);
	}

	@Bean
	public CommandLineRunner precargarDatos(UsuariosRepository repository, EstadosTareasRepository estadosTareasRepository) {
		return (args) -> {
			UsuariosEntity usuario1 = new UsuariosEntity();
			usuario1.setUsername("davidlopez");
			usuario1.setPrimerNombre("David");
			usuario1.setSegundoNombre("Lopez");
			repository.save(usuario1);
			UsuariosEntity usuario2 = new UsuariosEntity();
			usuario2.setUsername("ana_garcia");
			usuario2.setPrimerNombre("Ana");
			usuario2.setSegundoNombre("Garcia");
			repository.save(usuario2);
			EstadosTareasEntity estadoActivo = new EstadosTareasEntity();
			estadoActivo.setEstado("Activo");
			estadosTareasRepository.save(estadoActivo);
			EstadosTareasEntity estadoInactivo = new EstadosTareasEntity();
			estadoInactivo.setEstado("Inactivo");
			estadosTareasRepository.save(estadoInactivo);
		};
	}

}
