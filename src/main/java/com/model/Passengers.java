package com.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by vijay on 2/27/17.
 */
@Entity
@Table(name = "PASSENGERS")
public class Passengers implements BaseEntity, Comparable<Passengers> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private final Long id;

    @Column(name = "FIRST_NAME")
    private final String firstName;

    @Column(name = "LAST_NAME")
    private final String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private final LocalDate dob;

    @Column(name = "GENDER")
    private final String gender;

    @Column(name = "ADDRESS_LINE_1")
    private final String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private final String addressLine2;

    @Column(name = "STATE")
    private final String state;

    @Column(name = "COUNTRY")
    private final String country;

    @Column(name = "ZIP_CODE")
    private final String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "RESERVATION")
    private final Reservation reservation;


    private Passengers( String firstName, String lastName, LocalDate dob, String gender, String addressLine1, String addressLine2, String state, String country, String zipCode, Reservation reservation) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.reservation = reservation;
    }

    private Passengers(){
        id = null;
        firstName = null;
        lastName = null;
        dob = null;
        gender = null;
        addressLine1 = null;
        state = null;
        addressLine2 = null;
        country = null;
        zipCode = null;
        reservation = null;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Reservation getReservation() {
        return reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passengers that = (Passengers) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (dob != null ? !dob.equals(that.dob) : that.dob != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (addressLine1 != null ? !addressLine1.equals(that.addressLine1) : that.addressLine1 != null) return false;
        if (addressLine2 != null ? !addressLine2.equals(that.addressLine2) : that.addressLine2 != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (zipCode != null ? !zipCode.equals(that.zipCode) : that.zipCode != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (addressLine1 != null ? addressLine1.hashCode() : 0);
        result = 31 * result + (addressLine2 != null ? addressLine2.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        //result = 31 * result + (reservation != null ? reservation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Passengers{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode +
                '}';
    }

    @Override
    public int compareTo(Passengers o) {
        return this.lastName.compareToIgnoreCase(o.lastName);
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private LocalDate dob;
        private String gender;
        private String addressLine1;
        private String addressLine2;
        private String state;
        private String country;
        private String zipCode;
        private Reservation reservation;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setDob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public Builder setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder setReservation(Reservation reservation) {
            this.reservation = reservation;
            return this;
        }

        public Passengers build(){
            return new Passengers(
                    firstName,
                    lastName,
                    dob,
                    gender,
                    addressLine1,
                    addressLine2,
                    state,
                    country,
                    zipCode,
                    reservation
            );
        }
    }


}
