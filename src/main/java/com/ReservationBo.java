package com;

import com.dao.ReservationDao;
import com.model.Reservation;

/**
 * Created by vijay on 3/2/17.
 */

public class ReservationBo {
    private ReservationDao reservationDao;

    public ReservationBo(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public Reservation saveOrUpdateReservation(Reservation reservation){
        return null;
    }


}
