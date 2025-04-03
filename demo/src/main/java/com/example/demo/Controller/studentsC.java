package com.example.demo.Controller;


import com.example.demo.Model.discipline;
import com.example.demo.Model.students;
import com.example.demo.Repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class studentsC {
    
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("students", new students());
        return "html/students/students_form";
    }

    ;

    // READ (Отримання списку дисциплін)
    @GetMapping("/index")
    public String getstudentsIndexView(Model model) {
        students students = new students();
        List<students> studentsList = students.ListstudentsList();
        model.addAttribute("students", studentsList);
        return "html/students/students_index";
    }
    @GetMapping("/shows/{id}")
    public String getStudentsById(@PathVariable("id") Long id, Model model) {
        Optional<students> studentOpt = StudentsRepository.findById(id);

        if (studentOpt.isPresent()) {
            students student = studentOpt.get();

            // Обчислюємо середній бал
            student.calculateAverageGrade();

            // Передаємо студента в модель
            model.addAttribute("students", student);
        } else {
            model.addAttribute("error", "Student not found");
        }

        return "html/students/students_show"; // Повертаємо шаблон
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
        return students.addstudents(students.getFaculty_name(), students.getFirst_name(), students.getLast_name(), students.getMiddle_name(), students.getGradebook_number(), students.getLgroup(), students.getFaculty_number(), students.getGrade_in_discipline1(), students.getGrade_in_discipline2(), students.getGrade_in_discipline3(), students.getCourse());
    }

    // Отримання всіх продуктів
    @GetMapping
    public void getAllstudents() {
        students students = new students();
        students.liststudents();
    }

    // Оновлення продукту за ID
    @PutMapping("update/{id}")
    public ResponseEntity<String> updatestudents(@PathVariable Integer id, @RequestBody students updatedstudents) {
        try {
            students existingStudents = StudentsRepository.findById(Long.valueOf(id)).orElse(null);
            if (existingStudents == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Students not found");
            }

            // Оновлення полів
            existingStudents.setFaculty_name(updatedstudents.getFaculty_name());
            existingStudents.setFaculty_number(updatedstudents.getFaculty_number());
            existingStudents.setLgroup(updatedstudents.getLgroup());
            existingStudents.setGrade_in_discipline1(updatedstudents.getGrade_in_discipline1());
            existingStudents.setGrade_in_discipline2(updatedstudents.getGrade_in_discipline2());
            existingStudents.setGrade_in_discipline3(updatedstudents.getGrade_in_discipline3());
            existingStudents.setFirst_name(updatedstudents.getFirst_name());
            existingStudents.setLast_name(updatedstudents.getLast_name());
            existingStudents.setMiddle_name(updatedstudents.getMiddle_name());
            existingStudents.setGradebook_number(updatedstudents.getGradebook_number());
            existingStudents.setCourse(updatedstudents.getCourse());

            // Збереження змін
            StudentsRepository.save(existingStudents);

            return ResponseEntity.ok("Students updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update students");
        }
    }

    // Видалення продукту за ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity deletestudents (@PathVariable Integer id){
        try {
            students students = new students();
            students.deletestudents(id);
            return ResponseEntity.ok("Student deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete students");
        }
    }

}







