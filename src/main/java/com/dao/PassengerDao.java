package com.dao;

import com.model.Passengers;
import com.model.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Created by vijay on 2/27/17.
 */
public class PassengerDao {
    private SessionFactory sessionFactory;

    public PassengerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(Passengers passenger){
        Session session =sessionFactory.getCurrentSession();
        try{

            session.saveOrUpdate(passenger);

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public Passengers findPassengerById(String id){
        Session session =sessionFactory.getCurrentSession();
        Query<Passengers> query = session.createQuery("from Passengers where id = :id ", Passengers.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<Passengers> findPassengersByReservationId(String id){
        Session session =sessionFactory.getCurrentSession();
        Query<Passengers> query = session.createQuery("select pax from Passengers as pax inner join pax.reservation as res where  res.reservationId = :id ", Passengers.class);
        query.setParameter("id", id);
        return query.list();

    }

    @PreDestroy
    public void destroy() {
        System.out.println("Closing session factory!!");
        sessionFactory.getCurrentSession().close();
        sessionFactory.close();
    }

}
