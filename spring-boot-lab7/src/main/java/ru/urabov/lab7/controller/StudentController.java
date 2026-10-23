package ru.urabov.lab7.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.urabov.lab7.entity.AcademicGroup;
import ru.urabov.lab7.entity.Student;
import ru.urabov.lab7.repository.AcademicGroupRepository;
import ru.urabov.lab7.repository.StudentRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;
    private final AcademicGroupRepository groupRepository;

    @GetMapping({"/", "/list"})
    public ModelAndView getAllStudents() {
        log.info("/list -> список студентов");
        ModelAndView mav = new ModelAndView("list-students");
        mav.addObject("students", studentRepository.findAll(Sort.by("surname", "name")));
        return mav;
    }

    @GetMapping("/addStudentForm")
    public ModelAndView addStudentForm() {
        return studentForm(new Student());
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Студент с id = " + studentId + " не найден"));
        return studentForm(student);
    }

    @PostMapping("/saveStudent")
    public ModelAndView saveStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
        // В выпадающем списке выбран пункт «без группы»: приходит группа без id
        if (student.getGroup() != null && student.getGroup().getId() == null) {
            student.setGroup(null);
        }
        if (bindingResult.hasErrors()) {
            log.warn("Форма студента заполнена с ошибками: {}", bindingResult.getFieldErrors());
            return studentForm(student);
        }
        studentRepository.save(student);
        log.info("Сохранён студент: {}", student);
        return new ModelAndView("redirect:/list");
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Integer studentId) {
        studentRepository.deleteById(studentId);
        log.info("Удалён студент id = {}", studentId);
        return "redirect:/list";
    }

    // Форма добавления и изменения: одна страница, в неё передаются студент и список групп
    private ModelAndView studentForm(Student student) {
        // Поле select привязано к group.id, поэтому у студента без группы создаётся пустая группа
        if (student.getGroup() == null) {
            student.setGroup(new AcademicGroup());
        }
        ModelAndView mav = new ModelAndView("add-student-form");
        mav.addObject("student", student);
        mav.addObject("groups", groupRepository.findAll(Sort.by("name")));
        return mav;
    }
}
