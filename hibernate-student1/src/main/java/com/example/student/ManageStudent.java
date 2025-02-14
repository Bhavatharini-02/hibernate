package com.example.student;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ManageStudent {
    public static void main(String[] args) {
        ManageStudent manageStudent = new ManageStudent();

        /* Add few student records */
        manageStudent.addStudent("John Doe");
        manageStudent.addStudent("Alice Smith");
        manageStudent.addStudent("Bob Brown");

        /* List students */
        manageStudent.listStudents();
    }

    /* Method to CREATE a student */
    public void addStudent(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student student = new Student(name);
        session.save(student);

        tx.commit();
        session.close();

        System.out.println("Student " + name + " added successfully!");
    }

    /* Method to READ students */
    public void listStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("FROM Student", Student.class).list();

        System.out.println("Student Records:");
        for (Student student : students) {
            System.out.println("Roll Number: " + student.getRollNumber() + ", Name: " + student.getName());
        }

        session.close();
    }
}

