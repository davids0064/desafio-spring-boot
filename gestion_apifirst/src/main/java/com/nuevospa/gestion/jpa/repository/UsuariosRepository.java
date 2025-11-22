package com.nuevospa.gestion.jpa.repository;

import com.nuevospa.gestion.jpa.entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long> {

    Optional<UsuariosEntity> findByUsername(String username);

}
