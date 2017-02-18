package com.configuration;

import com.CustomerBo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by vijay on 2/18/17.
 */
@Configuration
public class CustomerConfig {
    @Bean(name = "customerBo" )
   // @Scope(value = "prototype")
    public CustomerBo customerBo(){
        return new CustomerBo();
    }
}
