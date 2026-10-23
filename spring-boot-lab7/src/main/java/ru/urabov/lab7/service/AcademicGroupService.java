package ru.urabov.lab7.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.urabov.lab7.repository.AcademicGroupRepository;
import ru.urabov.lab7.repository.StudentRepository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AcademicGroupService {

    private final AcademicGroupRepository groupRepository;
    private final StudentRepository studentRepository;

    // Количество студентов по id группы, для списка групп
    @Transactional(readOnly = true)
    public Map<Integer, Long> countStudents() {
        Map<Integer, Long> counts = new HashMap<>();
        for (Object[] row : studentRepository.countByGroups()) {
            counts.put((Integer) row[0], (Long) row[1]);
        }
        return counts;
    }

    // Удаление группы: сначала студенты остаются без группы, затем удаляется сама группа.
    // Обе операции в одной транзакции – либо выполняются обе, либо ни одна
    @Transactional
    public void delete(Integer groupId) {
        int detached = studentRepository.detachFromGroup(groupId);
        groupRepository.deleteById(groupId);
        log.info("Удалена группа id = {}, студентов без группы: {}", groupId, detached);
    }
}
