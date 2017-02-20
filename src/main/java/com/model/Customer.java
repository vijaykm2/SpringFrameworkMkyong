package com.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by vijay on 2/19/17.
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUST_ID")
    private long custId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private int age;

    @Column(name = "INSERT_TIME")
    //@Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime insertionTime ;

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", insertionTime=" + insertionTime.format(DateTimeFormatter.ISO_DATE_TIME) +
                '}';
    }

    public ZonedDateTime getInsertionTime() {
        return insertionTime;
    }

    public void setInsertionTime(ZonedDateTime insertionTime) {
        this.insertionTime = insertionTime;
    }

    public Customer(){}
    public Customer(long id, String name, int age, ZonedDateTime time){
        //this.custId = id;
        this.name = name;
        this.age = age;
        this.insertionTime = time;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
