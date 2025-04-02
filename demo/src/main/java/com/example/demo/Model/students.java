package com.example.demo.Model;

import jakarta.persistence.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;


@Entity
@Table(name = "students")
public class students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String faculty_name;
    @Column
    private int Lgroup;
    @Column
    private int faculty_number;
    @Column
    private float grade_in_discipline1;
    @Column
    private float grade_in_discipline2;
    @Column
    private float grade_in_discipline3;
    @Column
    private int Course;
    @Column
    private int gradebook_number;
    @Column
    private String first_name;
    @Column
    private String last_name;
    @Column
    private String middle_name;

    public students() {
    }

    public students(String facname, String firstname, String lastname, String middlename, int gradebooknumb, int group, int fnumb, float discGrade1, float discGrade2, float discGrade3, int course) {
        this.faculty_name = facname;
        this.Lgroup = group;
        this.faculty_number = fnumb;
        this.grade_in_discipline1 = discGrade1;
        this.grade_in_discipline2 = discGrade2;
        this.grade_in_discipline3 = discGrade3;
        this.Course = course;
        this.first_name = firstname;
        this.last_name = lastname;
        this.middle_name = middlename;
        this.gradebook_number = gradebooknumb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String facultyname) {
        this.faculty_name = facultyname;
    }

    public int getLgroup() {
        return Lgroup;
    }

    public void setLgroup(int Lgroup) {
        this.Lgroup = Lgroup;
    }

    public int getFaculty_number() {
        return faculty_number;
    }

    public void setFaculty_number(int facultynumber) {
        this.faculty_number = facultynumber;
    }

    public float getGrade_in_discipline1() {
        return grade_in_discipline1;
    }

    public void setGrade_in_discipline1(float gradeindiscipline1) {
        this.grade_in_discipline1 = gradeindiscipline1;
    }

    public float getGrade_in_discipline2() {
        return grade_in_discipline2;
    }

    public void setGrade_in_discipline2(float gradeindiscipline2) {
        this.grade_in_discipline2 = gradeindiscipline2;
    }

    public float getGrade_in_discipline3() {
        return grade_in_discipline3;
    }

    public void setGrade_in_discipline3(float gradeindiscipline3) {
        this.grade_in_discipline3 = gradeindiscipline3;
    }

    public int getCourse() {
        return Course;
    }

    public void setCourse(int Course) {
        this.Course = Course;
    }

    public int getGradebook_number() {
        return gradebook_number;
    }

    public void setGradebook_number(int gradebooknumb) {
        this.gradebook_number = gradebooknumb;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String firstname) {
        this.first_name = firstname;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String lastname) {
        this.last_name = lastname;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middlename) {
        this.middle_name = middlename;
    }
    /* Method to CREATE an employee in the database */
    public Integer addstudents(String facname, String firstname, String lastname, String middlename, int gradebooknumb, int group, int fnumb, float discGrade1, float discGrade2, float discGrade3, int course){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;

        try {
            tx = session.beginTransaction();
            students students = new students(facname,firstname,lastname,middlename,gradebooknumb, group, fnumb, discGrade1, discGrade2, discGrade3, course);
            id = (Integer) session.save(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }
    /* Method to  READ all the employees */
    public List<students> liststudents( ){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List Student = session.createQuery("FROM students",  students.class).list();
            for (Iterator iterator = Student.iterator(); iterator.hasNext();){
                students students = (students) iterator.next();
                System.out.print("Faculty Name: " + students.getFaculty_name());
                System.out.print(" First Name: " + students.getFirst_name());
                System.out.print(" Last Name: " + students.getLast_name());
                System.out.print(" Middle Name: " + students.getMiddle_name());
                System.out.print(" Group number: " + students.getLgroup());
                System.out.print(" Faculty Number: " + students.getFaculty_number());
                System.out.print(" Discipline grade 1: " + students.getGrade_in_discipline1());
                System.out.print(" Discipline grade 2: " + students.getGrade_in_discipline2());
                System.out.print(" Discipline grade 3: " + students.getGrade_in_discipline3());
                System.out.print(" Course: " + students.getCourse());
                System.out.print(" Gradebook number: " + students.getGradebook_number());
                System.out.println(" ID: " + students.getId());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    /* Method to UPDATE faculty_name for an employee */
    public void updatestudentsFacName(Integer id, String faculty_name ){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setFaculty_name( faculty_name );
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE first_name for an employee */
    public void updatestudentsFirstName(Integer id, String first_name ){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setFirst_name( first_name );
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE last_name for an employee */
    public void updatestudentsLastName(Integer id, String Last_name ){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setLast_name( last_name );
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE middle_name for an employee */
    public void updatestudentsMiddleName(Integer id, String middle_name ){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setMiddle_name( middle_name );
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE group_number for an employee */
    public void updatestudentsGroupNumber(Integer id, int lgroup ){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setLgroup( Lgroup );
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE faculty_number for an employee */
    public void updatestudentsFacultyNumber(Integer id, int faculty_number ){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setFaculty_number( faculty_number );
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE grade_in_discipline1 for an employee */
    public void updatestudentsGradeInDiscipline1(Integer id, float grade_in_discipline1 ){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setGrade_in_discipline1( grade_in_discipline1 );
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE grade_in_discipline2 for an employee */
    public void updatestudentsGradeInDiscipline2(Integer id, float grade_in_discipline2){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setGrade_in_discipline2(this.grade_in_discipline2);
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE grade_in_discipline3 for an employee */
    public void updatestudentsGradeInDiscipline3(Integer id, float grade_in_discipline3 ){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setGrade_in_discipline3( grade_in_discipline3 );
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE course for an employee */
    public void updatestudentsCourse(Integer id, int Course ){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setCourse( Course );
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE gradebook_number for an employee */
    public void updatestudentsGradebookNumber(Integer id, int gradebook_number ){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setGradebook_number( gradebook_number );
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE ALL for an employee */
    public void updatestudentsALL(Integer id, String facname, String firstname, String lastname, String middlename, int gradebooknumb, int group, int fnumb, float discGrade1, float discGrade2, float discGrade3, int course){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            students.setFaculty_name( facname );
            students.setFirst_name( firstname );
            students.setLast_name( lastname );
            students.setMiddle_name( middlename );
            students.setGradebook_number( gradebooknumb );
            students.setLgroup( group );
            students.setFaculty_number( fnumb );
            students.setGrade_in_discipline1( discGrade1 );
            students.setGrade_in_discipline2( discGrade2 );
            students.setGrade_in_discipline3( discGrade3 );
            students.setCourse( course );
            session.update(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    /* Method to DELETE a factories from the records */
    public void deletestudents(Integer id){
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            students students = (students) session.get(students.class, id);
            session.delete(students);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public List<students> ListstudentsList() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        List<students> studentsList = null;

        try {
            tx = session.beginTransaction();
            studentsList = session.createQuery("FROM students", students.class).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return studentsList;
    }
    public students getFirststudents() { // Змінюємо тип повернення
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;
        students firststudents = null; // Міняємо тип змінної

        try {
            tx = session.beginTransaction();
            firststudents = session.createQuery("FROM students", students.class)
                    .setMaxResults(1) // Отримуємо тільки перший запис
                    .uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return firststudents; // Повертаємо Factories
    }
}

