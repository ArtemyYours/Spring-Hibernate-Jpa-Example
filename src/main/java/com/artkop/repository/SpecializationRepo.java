package com.artkop.repository;

import com.artkop.model.Department;
import com.artkop.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepo extends JpaRepository<Specialization, Long> {
}
