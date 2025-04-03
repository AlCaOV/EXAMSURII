package com.example.demo.Controller;


import com.example.demo.Model.students;
import com.example.demo.Repository.StudentsRepository;
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
        List<students> studentsList = students.ListstudentsList();
        model.addAttribute("studentsList", studentsList);
        return "html/students/students_index";
    }
    @GetMapping("/shows")
    public String getstudentsShowsView(Model model) {
        students students = new students();
        students firstFactory = students.getFirststudents();
        model.addAttribute("students", firstFactory); // Передаємо продукт, а не рядок
        return "html/students/students_show";
    }

    @Autowired
    private StudentsRepository StudentsRepository;

    @PostMapping("/submitStudents")
    public String submitStudents(
            @RequestParam String facultyName,
            @RequestParam int Lgroup,
            @RequestParam int facultyNumber,
            @RequestParam float gradeInDiscipline1,
            @RequestParam int course,
            @RequestParam float gradeInDiscipline2,
            @RequestParam float gradeInDiscipline3,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String middle_name,
            @RequestParam int gradebook_number
    ) {
        // Створюємо новий об'єкт
        students students = new students();
        students.setFaculty_name(facultyName);
        students.setLgroup(Lgroup);
        students.setFaculty_number(facultyNumber);
        students.setGrade_in_discipline1(gradeInDiscipline1);
        students.setCourse(course);
        students.setGrade_in_discipline2(gradeInDiscipline2);
        students.setGrade_in_discipline3(gradeInDiscipline3);
        students.setFirst_name(first_name);
        students.setLast_name(last_name);
        students.setMiddle_name(middle_name);
        students.setGradebook_number(gradebook_number);

        // Зберігаємо у базу
        StudentsRepository.save(students);

        return "redirect:/students/form"; // Повертає сторінку після відправки
    }


    @PostMapping
    public Integer Createstudents(@RequestBody students students) {
        return students.addstudents(students.getFaculty_name(),students.getFirst_name(),students.getLast_name(),students.getMiddle_name(),students.getGradebook_number(),students.getLgroup(),students.getFaculty_number(),students.getGrade_in_discipline1(),students.getGrade_in_discipline2(),students.getGrade_in_discipline3(),students.getCourse());
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
        updatedstudents.updatestudentsFacName(id, updatedstudents.getFaculty_name());
        updatedstudents.updatestudentsFirstName(id, updatedstudents.getFirst_name());
        updatedstudents.updatestudentsLastName(id, updatedstudents.getLast_name());
        updatedstudents.updatestudentsMiddleName(id, updatedstudents.getMiddle_name());
        updatedstudents.updatestudentsGradebookNumber(id, updatedstudents.getGradebook_number());
        updatedstudents.updatestudentsGroupNumber(id, updatedstudents.getLgroup());
        updatedstudents.updatestudentsFacultyNumber(id, updatedstudents.getFaculty_number());
        updatedstudents.updatestudentsGradeInDiscipline1(id, updatedstudents.getGrade_in_discipline1());
        updatedstudents.updatestudentsGradeInDiscipline2(id, updatedstudents.getGrade_in_discipline2());
        updatedstudents.updatestudentsGradeInDiscipline3(id, updatedstudents.getGrade_in_discipline3());
        updatedstudents.updatestudentsCourse(id, updatedstudents.getCourse());
    }

    // Видалення продукту за ID
    @DeleteMapping("/{id}")
    public void deletestudents(@PathVariable Integer id) {
        students students = new students();
        students.deletestudents(id);
    }


}

