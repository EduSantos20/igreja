package com.edusantos.igreja.repository;

import com.edusantos.igreja.entities.Church;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChurchRepository extends JpaRepository<Church, Long> {
}
