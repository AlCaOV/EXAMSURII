package com.example.demo.Repository; // Замініть на ваш пакет

import com.example.demo.Model.students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<students, Long> {
}