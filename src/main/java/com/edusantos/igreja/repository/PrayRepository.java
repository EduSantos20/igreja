package com.edusantos.igreja.repository;

import com.edusantos.igreja.entities.Pray;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrayRepository extends JpaRepository<Pray, Long> {
}
