package com.dao;

import com.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Queue;

/**
 * Created by vijay on 2/19/17.
 */
public class HibernateCustomerDao implements CustomerDao {


    private SessionFactory sessionFactory;


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional(value = "transactionManager")
    public void insert(Customer customer) {
        Session session =sessionFactory.openSession();
        Transaction tx;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(customer);
            tx.commit();
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }finally{
            session.close();
        }

    }

    @Override
    public Customer findCustomerById(String name) {

        Session session = sessionFactory.openSession();

        Query<Customer> query = session.createQuery("from Customer where name = :name", Customer.class);
        query.setParameter("name", name);
        List<Customer> customers =query.list();
        return customers.get(0);
    }

    @Override
    public void performBatchUpdate(List<Customer> customers) {

    }
}
