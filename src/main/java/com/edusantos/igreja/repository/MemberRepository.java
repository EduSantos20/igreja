package com.edusantos.igreja.repository;

import com.edusantos.igreja.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
