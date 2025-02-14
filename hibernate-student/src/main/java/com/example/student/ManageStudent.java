package com.example.student;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class ManageStudent {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object: " + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageStudent manageStudent = new ManageStudent();

        /* Add few student records */
        Integer student1 = manageStudent.addStudent("John Doe");
        Integer student2 = manageStudent.addStudent("Alice Smith");
        Integer student3 = manageStudent.addStudent("Bob Brown");

        /* List students */
        manageStudent.listStudents();

        /* Update student */
        manageStudent.updateStudent(student1, "Johnathan Doe");

        /* Delete student */
        manageStudent.deleteStudent(student2);

        /* List updated students */
        manageStudent.listStudents();
    }

    /* Method to CREATE a student */
    public Integer addStudent(String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer studentID = null;

        try {
            tx = session.beginTransaction();
            Student student = new Student(name);
            studentID = (Integer) session.save(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return studentID;
    }

    /* Method to READ students */
    public void listStudents() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Student> students = session.createQuery("FROM Student", Student.class).list();
            for (Student student : students) {
                System.out.println("Roll Number: " + student.getRollNumber() + ", Name: " + student.getName());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE a student */
    public void updateStudent(Integer rollNumber, String newName) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, rollNumber);
            student.setName(newName);
            session.update(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE a student */
    public void deleteStudent(Integer rollNumber) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, rollNumber);
            session.delete(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

