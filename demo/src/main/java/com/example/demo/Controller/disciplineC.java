package com.example.demo.Controller;


import com.example.demo.Model.discipline;
import com.example.demo.Repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        List<discipline> disciplineslist = discipline.ListdisciplineList();
        model.addAttribute("discipline", disciplineslist);
        return "html/discipline/discipline_index";
    }
    @GetMapping("/shows/{id}")
    public String getDisciplineById(@PathVariable("id") Long id, Model model) {
        // Отримуємо дисципліну за ID з бази даних
        Optional<discipline> discipline = DisciplineRepository.findById(id);

        if (discipline.isPresent()) {
            model.addAttribute("discipline", discipline.get()); // Передаємо дисципліну в модель
        } else {
            model.addAttribute("error", "Дисципліна не знайдена"); // Якщо дисципліна не знайдена
        }

        return "html/discipline/discipline_show"; // Повертаємо шаблон
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
        discipline.setFaculty_name(facultyName);
        discipline.setLgroup(Lgroup);
        discipline.setFaculty_number(facultyNumber);
        discipline.setGrade_in_discipline(gradeInDiscipline);
        discipline.setCourse(course);

        // Зберігаємо у базу
        DisciplineRepository.save(discipline);

        return "redirect:/discipline/form"; // Повертає сторінку після відправки
    }

    @PostMapping
    public Integer Creatediscipline(@RequestBody discipline discipline) {
        return discipline.adddiscipline(discipline.getFaculty_name(),discipline.getLgroup(),discipline.getFaculty_number(),discipline.getGrade_in_discipline(),discipline.getCourse());
    }
    // Отримання всіх продуктів
    @GetMapping
    public void getAlldiscipline() {
        discipline discipline = new discipline();
        discipline.listdiscipline();
    }
    // Оновлення продукту за ID
    @PutMapping("update/{id}")
    public ResponseEntity<String> updatediscipline(@PathVariable Integer id, @RequestBody discipline updateddiscipline) {
        try {
            discipline existingDiscipline = DisciplineRepository.findById(Long.valueOf(id)).orElse(null);
            if (existingDiscipline == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Discipline not found");
            }

            // Оновлення полів
            existingDiscipline.setFaculty_name(updateddiscipline.getFaculty_name());
            existingDiscipline.setFaculty_number(updateddiscipline.getFaculty_number());
            existingDiscipline.setLgroup(updateddiscipline.getLgroup());
            existingDiscipline.setGrade_in_discipline(updateddiscipline.getGrade_in_discipline());
            existingDiscipline.setCourse(updateddiscipline.getCourse());

            // Збереження змін
            DisciplineRepository.save(existingDiscipline);

            return ResponseEntity.ok("Discipline updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update discipline");
        }
    }

    // Видалення продукту за ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity deletediscipline(@PathVariable Integer id) {
        try {
            discipline discipline = new discipline();
           discipline.deletediscipline(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete discipline");
        }
    }
}