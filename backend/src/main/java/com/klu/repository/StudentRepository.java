package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klu.model.Student;

@Repository   // ✅ IMPORTANT (add this)
public interface StudentRepository extends JpaRepository<Student, Long> {
}