package com.nuevospa.gestion.jpa.repository;

import com.nuevospa.gestion.jpa.entity.EstadosTareasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadosTareasRepository extends JpaRepository<EstadosTareasEntity, Integer> {

    Optional<EstadosTareasEntity> findByEstado(String estado);

}
