package ru.urabov.lab7.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Сущность «Учебная группа», соответствует таблице ACADEMIC_GROUPS.
 */
@Entity
@Table(name = "ACADEMIC_GROUPS")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AcademicGroup {

    /**
     * Идентификатор, генерируется базой данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Название группы, например РИЗ-250213у
     */
    @NotBlank(message = "Укажите название группы")
    @Size(max = 20, message = "Не больше 20 символов")
    @Column(name = "name")
    private String name;

    /**
     * Факультет (институт)
     */
    @Size(max = 20, message = "Не больше 20 символов")
    @Column(name = "faculty")
    private String faculty;

    /**
     * Курс, от 1 до 6
     */
    @NotNull(message = "Укажите курс")
    @Min(value = 1, message = "Курс от 1 до 6")
    @Max(value = 6, message = "Курс от 1 до 6")
    @Column(name = "course")
    private Integer course;

    /**
     * Куратор группы
     */
    @Size(max = 50, message = "Не больше 50 символов")
    @Column(name = "curator")
    private String curator;
}
