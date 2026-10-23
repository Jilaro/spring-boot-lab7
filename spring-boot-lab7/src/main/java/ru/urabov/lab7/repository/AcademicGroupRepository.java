package ru.urabov.lab7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urabov.lab7.entity.AcademicGroup;

/**
 * Запросы к таблице ACADEMIC_GROUPS. Методы findAll, findById, save, deleteById
 * Spring Data создаёт сам.
 */
public interface AcademicGroupRepository extends JpaRepository<AcademicGroup, Integer> {
}
