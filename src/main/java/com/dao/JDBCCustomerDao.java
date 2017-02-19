package com.dao;

import com.model.Customer;

import javax.sql.DataSource;
import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by vijay on 2/19/17.
 */
public class JDBCCustomerDao implements CustomerDao{

    private DataSource dataSource;
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;

    }

    @Override
    public void insert(Customer customer) {
        String sql = " INSERT INTO customer ( NAME, AGE, INSERT_TIME) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection()
        ){
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            //preparedStatement.setLong(1, customer.getCustId());
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.setTimestamp( 3, Timestamp.from(customer.getInsertionTime().toInstant()));
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {

        }

    }

    @Override
    public Customer findCustomerById(String name) {
        String sql = "SELECT * FROM customer WHERE NAME = ?";

        //Connection conn = null;

        try (Connection conn = dataSource.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            Customer customer = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                customer = new Customer(
                        rs.getLong("CUST_ID"),
                        rs.getString("NAME"),
                        rs.getInt("Age"),
                        ZonedDateTime.from(rs.getTimestamp("INSERT_TIME").toInstant().atZone(ZoneId.systemDefault()))
                );
            }
            rs.close();
            ps.close();
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
