package ru.urabov.lab7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.urabov.lab7.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Количество студентов в каждой группе: [id группы, количество]
    @Query("select s.group.id, count(s) from Student s where s.group is not null group by s.group.id")
    List<Object[]> countByGroups();

    // Убирает студентов из группы перед её удалением
    @Modifying
    @Query("update Student s set s.group = null where s.group.id = :groupId")
    int detachFromGroup(@Param("groupId") Integer groupId);
}
