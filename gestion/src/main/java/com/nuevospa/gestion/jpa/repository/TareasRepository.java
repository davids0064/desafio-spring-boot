package com.nuevospa.gestion.jpa.repository;

import com.nuevospa.gestion.jpa.entity.TareasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TareasRepository extends JpaRepository<TareasEntity, Integer> {

    Optional<TareasEntity> findByNombreTarea(String nombreTarea);

}
