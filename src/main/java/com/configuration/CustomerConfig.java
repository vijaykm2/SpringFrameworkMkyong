package com.configuration;

import com.CustomerBo;
import com.dao.CustomerDao;
import com.dao.HibernateCustomerDao;
import com.dao.JDBCCustomerDao;
import com.model.Customer;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
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
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by vijay on 2/18/17.
 */
@Configuration
@EnableTransactionManagement
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
    public CustomerBo customerBo(@Value("#{hibernateCustomerDao}" ) CustomerDao customerDao){
        CustomerBo customerBo = new CustomerBo();
        customerBo.setCustomerDao(customerDao);
        return customerBo;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Value("#{dataSource}") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="jdbcCustomerDao")
    public CustomerDao jdbcCustomerDao(@Value("#{dataSource}") DataSource dataSource,
                                   @Value("#{jdbcTemplate}") JdbcTemplate jdbcTemplate){
        JDBCCustomerDao customerDao =new JDBCCustomerDao();
        customerDao.setDataSource(dataSource );
        customerDao.setJdbcTemplate(jdbcTemplate);
        return customerDao;

    }

    @Bean(name="sessionFactory")
    public SessionFactory sessionFactory(@Value("#{dataSource}") DataSource dataSource,
                                         @Value("${hibernate.show_sql}") String showSql,
                                         @Value("${hibernate.dialect}") String dialect,
                                         @Value("${hibernate.format_sql}") String formatSql){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        //sessionBuilder.setDataSource(dataSource);
        sessionBuilder.addAnnotatedClasses(Customer.class);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", dialect);
        hibernateProperties.put("hibernate.show_sql", showSql);
        hibernateProperties.put("hibernate.format_sql", formatSql);
        //sessionBuilder.a
        sessionBuilder.addProperties(hibernateProperties);
        SessionFactory factory = sessionBuilder.buildSessionFactory();
        return factory;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager hibernateTransactionManager(@Value("#{sessionFactory}") SessionFactory sessionFactory){
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory);
        return htm;

    }

    @Bean(name="hibernateCustomerDao")
    public CustomerDao hibernateCustomerDao(@Value("#{sessionFactory}")SessionFactory sessionFactory,
                                   @Value("#{transactionManager}")HibernateTransactionManager htm){
        HibernateCustomerDao customerDao = new
                HibernateCustomerDao();
        customerDao.setSessionFactory(sessionFactory);
        //customerDao.setTransactionManager(htm);
        return customerDao;
    }
}
