package com.nuevospa.gestion;

import com.nuevospa.gestion.jpa.entity.EstadosTareasEntity;
import com.nuevospa.gestion.jpa.entity.UsuariosEntity;
import com.nuevospa.gestion.jpa.repository.EstadosTareasRepository;
import com.nuevospa.gestion.jpa.repository.UsuariosRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionApplication.class, args);
	}

	@Bean
	public CommandLineRunner precargarDatos(UsuariosRepository repository, EstadosTareasRepository estadosTareasRepository, PasswordEncoder passwordEncoder) {
		return (args) -> {
			UsuariosEntity usuario1 = new UsuariosEntity();
			usuario1.setUsername("davidsala");
			usuario1.setPrimerNombre("David");
			usuario1.setSegundoNombre("Salamanca");
			usuario1.setPassword(passwordEncoder.encode("salamanca123"));
			repository.save(usuario1);
			UsuariosEntity usuario2 = new UsuariosEntity();
			usuario2.setUsername("carolpineda");
			usuario2.setPrimerNombre("Carolina");
			usuario2.setSegundoNombre("Pineda");
			usuario1.setPassword(passwordEncoder.encode("clave124"));
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
