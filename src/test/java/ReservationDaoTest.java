import com.configuration.AppConfig;
import com.dao.PassengerDao;
import com.dao.ReservationDao;
import com.model.Passengers;
import com.model.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by vijay on 2/27/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ReservationDaoTest {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private PassengerDao passengerDao;



    private String getReservationId(){
        return UUID.randomUUID().toString();
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testInsertReservation(){



        Reservation reservation = new Reservation.Builder().setReservationId(getReservationId()).setArrival("DFW").setDeparture("EWR").setCreatedTime(ZonedDateTime.now()).setLastModifiedTime(ZonedDateTime.now()).build();
        reservationDao.insert(reservation);
        Passengers pax1 = new Passengers.Builder().setFirstName("Vijay")
                .setLastName("KM")
                .setDob(LocalDate.of(1984, 11, 30))
                .setAddressLine1("add line 1")
                .setAddressLine2("add line 2")
                .setGender("Male")
                .setCountry("India")
                .setState("KA")
                .setZipCode("192291")
                .setReservation(reservation).build();
        Passengers pax2 = new Passengers.Builder().setFirstName("Dhana")
                .setLastName("KM")
                .setDob(LocalDate.of(1992, 8, 9))
                .setAddressLine1("add line 1")
                .setAddressLine2("add line 2")
                .setGender("Female")
                .setCountry("India")
                .setState("KA")
                .setZipCode("192291")
                .setReservation(reservation).build();
        Passengers pax3 = new Passengers.Builder().setFirstName("sdsds")
                .setLastName("KM")
                .setDob(LocalDate.of(2017, 11, 30))
                .setAddressLine1("add line 1")
                .setAddressLine2("add line 2")
                .setGender("Male")
                .setCountry("India")
                .setState("KA")
                .setZipCode("192291")
                .setReservation(reservation).build();

        passengerDao.insert(pax1);
        passengerDao.insert(pax2);
        passengerDao.insert(pax3);

        Set<Passengers> passengers = reservation.getPassengers();
        passengers.add(pax1);
        passengers.add(pax2);
        passengers.add(pax3);
        reservationDao.insert(reservation);

        System.out.println(reservationDao.findReservationById(reservation.getReservationId()).toString());
        List<Passengers> pax = passengerDao.findPassengersByReservationId(reservation.getReservationId());
        pax.forEach(p -> System.out.println(p.toString()));


    }
}
