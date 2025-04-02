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
@Table(name = "discipline")
public class discipline {
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
    private float grade_in_discipline;
    @Column
    private int Course;

    public discipline() {
    }

    public discipline(String facname, int group, int fnumb, float discGrade, int course) {
        this.faculty_name = facname;
        this.Lgroup = group;
        this.faculty_number = fnumb;
        this.grade_in_discipline = discGrade;
        this.Course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getFaculty_name() {
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

    public float getGrade_in_discipline() {
        return grade_in_discipline;
    }

    public void setGrade_in_discipline(float gradeindiscipline) {
        this.grade_in_discipline = gradeindiscipline;
    }

    public int getCourse() {
        return Course;
    }

    public void setCourse(int Course) {
        this.Course = Course;
    }
    public List<discipline> ListdisciplineList() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        List<discipline> disciplineList = null;

        try {
            tx = session.beginTransaction();
            disciplineList = session.createQuery("FROM discipline", discipline.class).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return disciplineList;
    }
    public discipline getFirstdiscipline() { // Змінюємо тип повернення
        SessionFactory factory;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;
        discipline firstdiscipline = null; // Міняємо тип змінної

        try {
            tx = session.beginTransaction();
            firstdiscipline = session.createQuery("FROM discipline", discipline.class)
                    .setMaxResults(1) // Отримуємо тільки перший запис
                    .uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return firstdiscipline; // Повертаємо Factories
    }

    /* Method to CREATE an employee in the database */
    public Integer adddiscipline(String facname, int group, int fnumb, float discGrade, int course){
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
            discipline discipline = new discipline(facname, group, fnumb, discGrade, course);
            id = (Integer) session.save(discipline);
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
    public List<discipline> listdiscipline( ){
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
            List Disciplines = session.createQuery("FROM discipline",  discipline.class).list();
            for (Iterator iterator = Disciplines.iterator(); iterator.hasNext();){
                discipline discipline = (discipline) iterator.next();
                System.out.print("Faculty Name: " + discipline.getFaculty_name());
                System.out.print(" Group number: " + discipline.getLgroup());
                System.out.print(" Faculty Number: " + discipline.getFaculty_number());
                System.out.print(" Discipline grade: " + discipline.getGrade_in_discipline());
                System.out.print(" Course: " + discipline.getCourse());
                System.out.println(" ID: " + discipline.getId());
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
    public void updatedisciplineFacName(Integer id, String faculty_name ){
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
            discipline discipline = (discipline)session.get(discipline.class, id);
            discipline.setFaculty_name( faculty_name );
            session.update(discipline);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE lgroup for an employee */
    public void updatedisciplineLgroup(Integer id, int Lgroup){
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
            discipline discipline = (discipline) session.get(discipline.class, id);
            discipline.setLgroup( Lgroup );
            session.update(discipline);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE faculty_number for an employee */
    public void updatedisciplineFacNum(Integer id, int faculty_number){
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
            discipline discipline = (discipline) session.get(discipline.class, id);
            discipline.setFaculty_number( faculty_number );
            session.update(discipline);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE grade_in_discipline for an employee */
    public void updatedisciplineGrade(Integer id, Float grade_in_discipline){
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
            discipline discipline = (discipline) session.get(discipline.class, id);
            discipline.setGrade_in_discipline( grade_in_discipline );
            session.update(discipline);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE Course for an employee */
    public void updatedisciplineCourse(Integer id, int Course){
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
            discipline discipline = (discipline) session.get(discipline.class, id);
            discipline.setCourse( Course );
            session.update(discipline);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /* Method to UPDATE ALL for an employee */
    public void updatedisciplineALL(Integer id, String facname, int group, int fnumb, float discGrade, int Course){
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
            discipline discipline = (discipline)session.get(discipline.class, id);
            discipline.setFaculty_name( facname );
            discipline.setLgroup( group );
            discipline.setFaculty_number( fnumb );
            discipline.setGrade_in_discipline( discGrade );
            discipline.setCourse( Course );
            session.update(discipline);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    /* Method to DELETE a factories from the records */
    public void deletediscipline(Integer id){
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
            discipline discipline = (discipline)session.get(discipline.class, id);
            session.delete(discipline);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}


