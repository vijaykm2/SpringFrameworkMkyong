package com.dao;

import com.model.Passengers;
import com.model.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.PreDestroy;

/**
 * Created by vijay on 2/27/17.
 */
public class ReservationDao {

    private SessionFactory sessionFactory;

    public ReservationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(Reservation reservation){
        Session session =sessionFactory.getCurrentSession();
        try{

            session.saveOrUpdate(reservation);

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public Reservation findReservationById(String id){
        Session session =sessionFactory.getCurrentSession();
        Query<Reservation> query = session.createQuery("from Reservation where reservationId = :id ", Reservation.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Closing session factory!!");
        sessionFactory.getCurrentSession().close();
        sessionFactory.close();
    }
}
