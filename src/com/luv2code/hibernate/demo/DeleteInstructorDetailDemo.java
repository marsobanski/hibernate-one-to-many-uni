package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

    public static void main(String[] args) {

        //sql do zapisywania polskich znaków w bazie:
        //ALTER TABLE student CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            int id = 4;
            //get instructor detail
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            //print instructor detail
            System.out.println("instructor detail " + instructorDetail);

            //print instructor
            System.out.println("instructor " + instructorDetail.getInstructor());

            //jeśli bi-directional, to przy delecie InstructorDetail, który nie ma Cascade.DELETE na swoim Instructor trzeba ręcznie usunąć InstructorDetail z tego Instructor.
            //przy usuwaniu InstructorDetail Hibernate odpina od niego Instructor, ale w drugą stronę to nie działa automatycznie.
            instructorDetail.getInstructor().setInstructorDetail(null);

            System.out.println("deleting instructor detail" + instructorDetail);
            session.delete(instructorDetail);

            session.getTransaction().commit();

            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //handle connection leak issues
            session.close();
            factory.close();
        }
    }
}
