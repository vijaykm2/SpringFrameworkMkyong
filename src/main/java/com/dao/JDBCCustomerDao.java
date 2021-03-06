package com.dao;

import com.model.Customer;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by vijay on 2/19/17.
 */

class CustomerRowMapper implements RowMapper
{
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer.CustomerBuilder().
        setName(rs.getString("NAME")).
        setAge(rs.getInt("AGE")).
        setInsertTime(ZonedDateTime.from(rs.getTimestamp("INSERT_TIME").toInstant().atZone(ZoneId.systemDefault()))).build();
        return customer;
    }

}
public class JDBCCustomerDao implements CustomerDao{

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;

    }


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Customer customer) {
        String sql = " INSERT INTO customer ( NAME, AGE, INSERT_TIME) VALUES (?, ?, ?)";

        if(this.jdbcTemplate == null){
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
            }
        }else{
            jdbcTemplate.update(sql, new Object[]{customer.getName(), customer.getAge(), Timestamp.from(customer.getInsertionTime().toInstant())});
        }

    }

    @Override
    public Customer findCustomerById(String name) {
        String sql = "SELECT * FROM customer WHERE NAME = ?";

        //Connection conn = null;

        if(this.jdbcTemplate == null){
            try (Connection conn = dataSource.getConnection()){
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                Customer customer = null;
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    customer = new Customer.CustomerBuilder()
                            .setName(rs.getString("NAME"))
                            .setAge(rs.getInt("Age"))
                            .setInsertTime(ZonedDateTime.from(rs.getTimestamp("INSERT_TIME").toInstant().atZone(ZoneId.systemDefault())))
                            .build();
                }
                rs.close();
                ps.close();
                return customer;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            List<Customer> c =jdbcTemplate.query(sql, new Object[]{name},
                    new CustomerRowMapper());;
            //Customer c  = (Customer) jdbcTemplate.queryForList(sql, new Object[]{name}, new CustomerRowMapper());
            return c.get(0);
        }
    }

    public void performBatchUpdate(List<Customer> customers){
        String sql = " INSERT INTO customer ( NAME, AGE, INSERT_TIME) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Customer customer = customers.get(i);
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setInt(2, customer.getAge());
                preparedStatement.setTimestamp( 3, Timestamp.from(customer.getInsertionTime().toInstant()));

            }

            @Override
            public int getBatchSize() {
                return customers.size();
            }
        });

    }

    @PostConstruct
    public void init(){
        String result = this.jdbcTemplate == null ? "jdbc template is set": "jdbc template is not set";
        System.out.println(result);
    }
}
