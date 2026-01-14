package com.edusantos.igreja.repository;

import com.edusantos.igreja.entities.Financial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialRepository extends JpaRepository<Financial, Long> {
}
