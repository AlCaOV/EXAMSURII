package com.example.demo.Controller;


import com.example.demo.Model.discipline;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/discipline")
public class disciplineC {


    @GetMapping("/form")
    public String showForm(Model model ) {
        model.addAttribute("discipline",  new discipline());
        return "html/discipline/discipline_form";
    };

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