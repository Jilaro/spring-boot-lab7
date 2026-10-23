package ru.urabov.lab7.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
 * Сущность «Студент», соответствует таблице STUDENTS.
 */
@Entity
@Table(name = "STUDENTS")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Student {

    /**
     * Идентификатор, генерируется базой данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Имя, до 15 символов
     */
    @NotBlank(message = "Укажите имя")
    @Size(max = 15, message = "Не больше 15 символов")
    @Column(name = "name")
    private String name;

    /**
     * Фамилия, до 25 символов
     */
    @NotBlank(message = "Укажите фамилию")
    @Size(max = 25, message = "Не больше 25 символов")
    @Column(name = "surname")
    private String surname;

    /**
     * Факультет, до 20 символов
     */
    @Size(max = 20, message = "Не больше 20 символов")
    @Column(name = "faculty")
    private String faculty;

    /**
     * Возраст
     */
    @NotNull(message = "Укажите возраст")
    @Min(value = 14, message = "Возраст от 14 до 100")
    @Max(value = 100, message = "Возраст от 14 до 100")
    @Column(name = "age")
    private Integer age;

    /**
     * Учебная группа студента, необязательная.
     * Много студентов – одна группа, связь по столбцу group_id.
     */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private AcademicGroup group;
}
