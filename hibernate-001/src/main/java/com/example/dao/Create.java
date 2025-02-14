package com.example.dao;
import com.example.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.example.utilities.SessionFactoryProvider;
public class Create {
    public static void main(String[] args)
    {
        try {
            SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction t = session.beginTransaction();

            Employee emp = new Employee(101, "John");
            session.save(emp);
            t.commit();

            sessionFactory.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}