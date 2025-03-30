package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.example.discipline;
import java.util.List;

public class Main {
    public static void main(String[] args) {
     discipline ds = new discipline();
     students ss = new students();
        //ds.deletediscipline(8);
        //ds.adddiscipline("DC", 1, 134,4,1);
//        ds.updatedisciplineFacName(9,"MR");
//        ds.updatedisciplineLgroup(9,1);
//        ds.updatedisciplineFacNum(9,135);
//        ds.updatedisciplineGrade(9, 5F);
//        ds.updatedisciplineCourse(9,3);
        ds.listdiscipline();


         // ss.addstudents("DC", "A", "B", "C", 1, 1, 1, 4F, 5F, 6F, 1);
//          ss.deletestudents(9);


//        ss.updatestudentsCourse(9, 3);
//        ss.updatestudentsGradeInDiscipline1(9, 5F);
//        ss.updatestudentsGradeInDiscipline2(9, 6F);
//        ss.updatestudentsGradeInDiscipline3(9, 7F);
//        ss.updatestudentsFacName(9, "MR");
//        ss.updatestudentsGroupNumber(9, 1);
//        ss.updatestudentsFacultyNumber(9, 1);
//        ss.updatestudentsFirstName(9, "A");
//        ss.updatestudentsLastName(9, "B");
//        ss.updatestudentsMiddleName(9, "C");
//        ss.updatestudentsGradebookNumber(9, 1);

        ss.liststudents();
    }
}