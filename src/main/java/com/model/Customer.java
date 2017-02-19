package com.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by vijay on 2/19/17.
 */
public class Customer {
    private long custId;
    private String name;
    private int age;

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

    private ZonedDateTime insertionTime ;

    public Customer(){}
    public Customer(long id, String name, int age, ZonedDateTime time){
        this.custId = id;
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
