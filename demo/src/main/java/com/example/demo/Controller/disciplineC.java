package com.example.demo.Controller;


import com.example.demo.Model.discipline;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/discipline")
public class disciplineC {


    @GetMapping("/form")
    public String showForm(Model model ) {
        model.addAttribute("discipline",  new discipline());
        return "html/discipline/discipline_form";
    };
    // READ (Отримання списку дисциплін)
    @GetMapping("/index")
    public String showIndex(Model model ) {
        model.addAttribute("discipline",  new discipline());
        return "html/discipline/discipline_index";
    };
    @PostMapping("/submitDiscipline")
    public String submitDiscipline(
            @RequestParam String facultyName,
            @RequestParam int Lgroup,
            @RequestParam int facultyNumber,
            @RequestParam Float gradeInDiscipline,
            @RequestParam int course
    ) {
        System.out.println("Faculty Name: " + facultyName);
        System.out.println("Group Number: " + Lgroup);
        System.out.println("Faculty Number: " + facultyNumber);
        System.out.println("Grade: " + gradeInDiscipline);
        System.out.println("Course: " + course);

        return "html/discipline/discipline_form"; // Повертає сторінку після відправки
    }

    @PostMapping
    public Integer Creatediscipline(@RequestBody discipline discipline) {
        return discipline.adddiscipline(discipline.getfaculty_name(),discipline.getLgroup(),discipline.getfaculty_number(),discipline.getgrade_in_discipline(),discipline.getCourse());
    }
    // Отримання всіх продуктів
    @GetMapping
    public void getAlldiscipline() {
        discipline discipline = new discipline();
        discipline.listdiscipline();
    }
    // Оновлення продукту за ID
    @PutMapping("/{id}")
    public void updatediscipline(@PathVariable Integer id, @RequestBody discipline updateddiscipline) {
        updateddiscipline.updatedisciplineFacName(id, updateddiscipline.getfaculty_name());
        updateddiscipline.updatedisciplineLgroup(id, updateddiscipline.getLgroup());
        updateddiscipline.updatedisciplineFacNum(id, updateddiscipline.getfaculty_number());
        updateddiscipline.updatedisciplineGrade(id, updateddiscipline.getgrade_in_discipline());
        updateddiscipline.updatedisciplineCourse(id, updateddiscipline.getCourse());
    }

    // Видалення продукту за ID
    @DeleteMapping("/{id}")
    public void deletediscipline(@PathVariable Integer id) {
        discipline discipline = new discipline();
        discipline.deletediscipline(id);
    }


}