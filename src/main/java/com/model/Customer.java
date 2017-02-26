package com.model;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by vijay on 2/19/17.
 */
@Entity
@Table(name = "customer")
public final class Customer implements Comparable<Customer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUST_ID")
    private Long custId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private Integer age;

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


    private Customer(String name, Integer age, ZonedDateTime insertionTime){
        //this.custId = custId;
        this.name = name;
        this.age = age;
        this.insertionTime = insertionTime;
    }

    private Customer(){

    }

    public long getCustId() {
        return custId;
    }


    public String getName() {
        return name;
    }
    public Integer getAge() {
        return age;
    }

    @Override
    public int compareTo(Customer o) {
        if(this.equals(o)){
            return 0;
        }
        return this.getName().compareToIgnoreCase(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (custId != customer.custId) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (age != null ? !age.equals(customer.age) : customer.age != null) return false;
        return insertionTime != null ? insertionTime.equals(customer.insertionTime) : customer.insertionTime == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (custId ^ (custId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (insertionTime != null ? insertionTime.hashCode() : 0);
        return result;
    }

    public static class CustomerBuilder{
        String name;
        Integer age;
        ZonedDateTime insertTime;

        public CustomerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public CustomerBuilder setInsertTime(ZonedDateTime insertTime) {
            this.insertTime = insertTime;
            return this;
        }

        public Customer build(){
            return new Customer(name, age, insertTime);
        }


    }
}
