package com.example.demo.Controller;


import com.example.demo.Model.discipline;
import com.example.demo.Repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getdisciplineIndexView(Model model) {
        discipline discipline = new discipline();
        List<discipline> disciplinelist = discipline.listdiscipline();
        model.addAttribute("discipline", disciplinelist);
        return "html/discipline/discipline_index";
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