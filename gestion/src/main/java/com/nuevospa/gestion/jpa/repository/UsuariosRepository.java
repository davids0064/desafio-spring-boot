package com.nuevospa.gestion.jpa.repository;

import com.nuevospa.gestion.jpa.entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long> {
}
