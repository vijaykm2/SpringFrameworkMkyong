package com.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Created by vijay on 2/27/17.
 */
@Entity
@Table(name = "RESERVATION")
public class Reservation implements Comparable<Reservation>{
    private Long id;
    private String reservationId;
    private ZonedDateTime createdTime;
    private ZonedDateTime lastModifiedTime;
    private String arrival;
    private String departure;
    private Set<Passengers> passengers;

    private Reservation(String reservationId, ZonedDateTime createdTime, ZonedDateTime lastModifiedTime, String arrival, String departure, Set<Passengers> passengers) {
        this.id = null;
        this.reservationId = reservationId;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
        this.arrival = arrival;
        this.departure = departure;
        this.passengers = passengers;
    }

    private Reservation() {

        reservationId = null;
        id=null;
        arrival = null;
        departure = null;
        createdTime = null;
        lastModifiedTime = null;
        passengers = null;
    }

    public Long getId() {
        return id;
    }

    public String getReservationId() {
        return reservationId;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public ZonedDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public Set<Passengers> getPassengers() {
        return passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (reservationId != null ? !reservationId.equals(that.reservationId) : that.reservationId != null)
            return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (lastModifiedTime != null ? !lastModifiedTime.equals(that.lastModifiedTime) : that.lastModifiedTime != null)
            return false;
        if (arrival != null ? !arrival.equals(that.arrival) : that.arrival != null) return false;
        if (departure != null ? !departure.equals(that.departure) : that.departure != null) return false;
        return passengers != null ? passengers.equals(that.passengers) : that.passengers == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reservationId != null ? reservationId.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (lastModifiedTime != null ? lastModifiedTime.hashCode() : 0);
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (passengers != null ? passengers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationId='" + reservationId + '\'' +
                ", createdTime=" + createdTime +
                ", lastModifiedTime=" + lastModifiedTime +
                ", arrival='" + arrival + '\'' +
                ", departure='" + departure + '\'' +
                ", passengers=" + passengers +
                '}';
    }

    @Override
    public int compareTo(Reservation o) {

        return this.reservationId.compareToIgnoreCase(o.reservationId);
    }
    
    public static class Builder {

        private String reservationId;
        private ZonedDateTime createdTime;
        private ZonedDateTime lastModifiedTime;
        private String arrival;
        private String departure;
        private Set<Passengers> passengers;

        public Builder setReservationId(String reservationId) {
            this.reservationId = reservationId;
            return this;
        }

        public Builder setCreatedTime(ZonedDateTime createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public Builder setLastModifiedTime(ZonedDateTime lastModifiedTime) {
            this.lastModifiedTime = lastModifiedTime;
            return this;
        }

        public Builder setArrival(String arrival) {
            this.arrival = arrival;
            return this;
        }

        public Builder setDeparture(String departure) {
            this.departure = departure;
            return this;
        }

        public Builder setPassengers(Set<Passengers> passengers) {
            this.passengers = passengers;
            return this;
        }

        public Reservation build(){
            return new Reservation(reservationId, createdTime, lastModifiedTime, arrival, departure, passengers);
        }
    }
}
