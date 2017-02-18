package com.configuration;

import com.SchedulerBo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vijay on 2/18/17.
 */
@Configuration
public class SchedulerConfig {
    @Bean (name = "schedulerBo")
    public SchedulerBo schedulerBo(){
        return new SchedulerBo();
    }
}
