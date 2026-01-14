package com.edusantos.igreja.repository;

import com.edusantos.igreja.entities.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccasionRepository extends JpaRepository<Occasion,Long> {
}
