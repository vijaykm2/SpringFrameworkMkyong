package com.dao;

import com.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import javax.annotation.PreDestroy;
import java.util.List;

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
    public void insert(Customer customer) {
        Session session =sessionFactory.getCurrentSession();
        try{
            if(customer == null || customer.getAge() == null || customer.getName() == null){
                throw new Exception("Customer should have name and age ");
            }
            session.saveOrUpdate(customer);

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }finally{

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

    @PreDestroy
    public void destroy() {
        System.out.println("Closing session factory!!");
        sessionFactory.getCurrentSession().close();
        sessionFactory.close();
    }
}
