package com.configuration;

import com.CustomerBo;
import com.dao.CustomerDao;
import com.dao.JDBCCustomerDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by vijay on 2/18/17.
 */
@Configuration
public class CustomerConfig {
    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocation(new ClassPathResource("datasource.properties"));
        return propertyPlaceholderConfigurer;
    }
    @Bean(name="dataSource")
    public DataSource dataSource (@Value("${jdbc.driverClassName}") String driverClass,
                                  @Value("${jdbc.url}") String jdbcUrl,
                                  @Value("${jdbc.username}") String user,
                                  @Value("${jdbc.password}") String password ){
        BasicDataSource driverManagerDataSource = new BasicDataSource();
        driverManagerDataSource.setDriverClassName(driverClass);
        driverManagerDataSource.setUrl(jdbcUrl);
        driverManagerDataSource.setPassword(password);
        driverManagerDataSource.setUsername(user);
        System.out.println(driverManagerDataSource.getUrl() + ' '+ driverManagerDataSource.getPassword()+' '+ driverManagerDataSource.getUsername());
        return driverManagerDataSource;
    }


    @Bean
    public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor(){
        return new CommonAnnotationBeanPostProcessor();
    }
    @Bean
    public RequiredAnnotationBeanPostProcessor requiredAnnotationBeanPostProcessor(){
        return new RequiredAnnotationBeanPostProcessor();
    }
    @Bean(name = "customerBo" )
   // @Scope(value = "prototype")
    public CustomerBo customerBo(){
        return new CustomerBo();
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Value("#{dataSource}") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="jdbcCustomerDao")
    public CustomerDao customerDao(@Value("#{dataSource}") DataSource dataSource,
                                   @Value("#{jdbcTemplate}") JdbcTemplate jdbcTemplate){
        JDBCCustomerDao customerDao =new JDBCCustomerDao();
        customerDao.setDataSource(dataSource );
        customerDao.setJdbcTemplate(jdbcTemplate);
        return customerDao;

    }
}
