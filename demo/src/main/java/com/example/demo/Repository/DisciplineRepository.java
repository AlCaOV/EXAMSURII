package com.example.demo.Repository; // Замініть на ваш пакет

import com.example.demo.Model.discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<discipline, Long> {
}