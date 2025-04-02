package com.example.demo.Controller;

import com.example.demo.Model.discipline;
import com.example.demo.Model.students;
import com.example.demo.Repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class studentsC {
    @GetMapping("/form")
    public String showForm(Model model ) {
        model.addAttribute("students",  new students());
        return "html/students/students_form";
    };

    // READ (Отримання списку дисциплін)
    @GetMapping("/index")
    public String getstudentsIndexView(Model model) {
        students students = new students();
        List<students> studentslist = students.liststudents();
        model.addAttribute("students", studentslist);
        return "html/students/students_index";
    }

    @Autowired
    private DisciplineRepository DisciplineRepository;

    @PostMapping("/submitDiscipline")
    public String submitDiscipline(
            @RequestParam String facultyName,
            @RequestParam int Lgroup,
            @RequestParam int facultyNumber,
            @RequestParam float gradeInDiscipline,
            @RequestParam int course
    ) {
        // Створюємо новий об'єкт
        discipline discipline = new discipline();
        discipline.setfaculty_name(facultyName);
        discipline.setLgroup(Lgroup);
        discipline.setfaculty_number(facultyNumber);
        discipline.setgrade_in_discipline(gradeInDiscipline);
        discipline.setCourse(course);

        // Зберігаємо у базу
        DisciplineRepository.save(discipline);

        return "html/discipline/discipline_form"; // Повертає сторінку після відправки
    }


    @PostMapping
    public Integer Createstudents(@RequestBody students students) {
        return students.addstudents(students.getfaculty_name(),students.getfirst_name(),students.getlast_name(),students.getmiddle_name(),students.getgradebook_number(),students.getLgroup(),students.getfaculty_number(),students.getgrade_in_discipline1(),students.getgrade_in_discipline2(),students.getgrade_in_discipline3(),students.getCourse());
    }
    // Отримання всіх продуктів
    @GetMapping
    public void getAllstudents() {
        students students = new students();
        students.liststudents();
    }
    // Оновлення продукту за ID
    @PutMapping("/{id}")
    public void updatestudents(@PathVariable Integer id, @RequestBody students updatedstudents) {
        updatedstudents.updatestudentsFacName(id, updatedstudents.getfaculty_name());
        updatedstudents.updatestudentsFirstName(id, updatedstudents.getfirst_name());
        updatedstudents.updatestudentsLastName(id, updatedstudents.getlast_name());
        updatedstudents.updatestudentsMiddleName(id, updatedstudents.getmiddle_name());
        updatedstudents.updatestudentsGradebookNumber(id, updatedstudents.getgradebook_number());
        updatedstudents.updatestudentsGroupNumber(id, updatedstudents.getLgroup());
        updatedstudents.updatestudentsFacultyNumber(id, updatedstudents.getfaculty_number());
        updatedstudents.updatestudentsGradeInDiscipline1(id, updatedstudents.getgrade_in_discipline1());
        updatedstudents.updatestudentsGradeInDiscipline2(id, updatedstudents.getgrade_in_discipline2());
        updatedstudents.updatestudentsGradeInDiscipline3(id, updatedstudents.getgrade_in_discipline3());
        updatedstudents.updatestudentsCourse(id, updatedstudents.getCourse());
    }

    // Видалення продукту за ID
    @DeleteMapping("/{id}")
    public void deletestudents(@PathVariable Integer id) {
        students students = new students();
        students.deletestudents(id);
    }


}

