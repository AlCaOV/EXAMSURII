package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.discipline;

    public class HibernateUtil {
        private static final SessionFactory sessionFactory = buildSessionFactory();

        private static SessionFactory buildSessionFactory() {
            try {
                return new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(discipline.class)
                        .buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Помилка ініціалізації SessionFactory: " + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }
    }

