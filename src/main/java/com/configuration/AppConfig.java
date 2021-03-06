package com.configuration;

import com.HelloWorld;
import com.output.CsvOutputGenerator;
import com.output.IOutputGenerator;
import com.output.JSONOutputGenerator;
import com.output.OutputHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * Created by vijay on 2/18/17.
 */

@Configuration
@Import({CustomerConfig.class, SchedulerConfig.class})
public class AppConfig {

    @Bean(name = "helloWorld")
    public HelloWorld helloWorld(){
        return new HelloWorld();
    }

    @Bean(name = "jsonOutputGenerator")
    public IOutputGenerator csvOutputGenerator() {
        return new JSONOutputGenerator();
    }

    @Bean(name = "csvOutputGenerator")
    public IOutputGenerator jsonOutputGenerator() {
        return new CsvOutputGenerator();
    }

    @Bean(name = "outputHelper")
    public OutputHelper outputHelper(@Value("#{dataSource}") DataSource dataSource) {

        OutputHelper outputHelper =  new OutputHelper();
        outputHelper.setDataSource(dataSource);
        return outputHelper;
    }

}
