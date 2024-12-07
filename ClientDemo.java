package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    public static void main(String[] args) {
        // Create SessionFactory object
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();

        // Create a session
        Session session = factory.getCurrentSession();
        try {
            // Start a transaction
            Transaction transaction = session.beginTransaction();

            // Update department using HQL with positional parameters
            String hql = "UPDATE Department SET name = ?, location = ? WHERE deptId = ?";
            int result = session.createQuery(hql)
                    .setParameter(1, "HR")
                    .setParameter(2, "New York")
                    .setParameter(3, 1)  // Example department ID
                    .executeUpdate();

            // Commit the transaction
            transaction.commit();
            System.out.println("Rows affected: " + result);
        } finally {
            // Close the session
            session.close();
            factory.close();
        }
    }
}
