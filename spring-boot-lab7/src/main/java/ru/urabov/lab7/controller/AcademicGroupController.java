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
import ru.urabov.lab7.repository.AcademicGroupRepository;
import ru.urabov.lab7.service.AcademicGroupService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AcademicGroupController {

    private final AcademicGroupRepository groupRepository;
    private final AcademicGroupService groupService;

    @GetMapping("/groups")
    public ModelAndView getAllGroups() {
        log.info("/groups -> список учебных групп");
        ModelAndView mav = new ModelAndView("list-groups");
        mav.addObject("groups", groupRepository.findAll(Sort.by("course", "name")));
        mav.addObject("studentCounts", groupService.countStudents());
        return mav;
    }

    @GetMapping("/addGroupForm")
    public ModelAndView addGroupForm() {
        return groupForm(new AcademicGroup());
    }

    @GetMapping("/showUpdateGroupForm")
    public ModelAndView showUpdateGroupForm(@RequestParam Integer groupId) {
        AcademicGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Группа с id = " + groupId + " не найдена"));
        return groupForm(group);
    }

    @PostMapping("/saveGroup")
    public ModelAndView saveGroup(@Valid @ModelAttribute("group") AcademicGroup group, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Форма группы заполнена с ошибками: {}", bindingResult.getFieldErrors());
            return groupForm(group);
        }
        groupRepository.save(group);
        log.info("Сохранена группа: {}", group);
        return new ModelAndView("redirect:/groups");
    }

    @GetMapping("/deleteGroup")
    public String deleteGroup(@RequestParam Integer groupId) {
        groupService.delete(groupId);
        return "redirect:/groups";
    }

    private ModelAndView groupForm(AcademicGroup group) {
        ModelAndView mav = new ModelAndView("add-group-form");
        mav.addObject("group", group);
        return mav;
    }
}
