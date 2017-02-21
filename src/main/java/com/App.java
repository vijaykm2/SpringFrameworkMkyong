package com;

import com.configuration.AppConfig;
import com.dao.CustomerDao;
import com.model.Customer;
import com.output.IOutputGenerator;
import com.output.OutputHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception{
        Instant start = Instant.now();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("SpringBeans.xml");
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Time to start application Context: "+ duration.toMillis());


        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
        helloWorld.printHello();
        helloWorld.setName("sdlksdlklksd");
        helloWorld.printHello();

        OutputHelper helper = (OutputHelper)ctx.getBean("outputHelper");
        IOutputGenerator csvGenerator = (IOutputGenerator)ctx.getBean("csvOutputGenerator");
        IOutputGenerator jsonGenerator = (IOutputGenerator)ctx.getBean("jsonOutputGenerator");
        helper.setOutputGenerator(csvGenerator);
        helper.generateOutput();
        helper.setOutputGenerator(jsonGenerator);
        helper.generateOutput();
        SchedulerBo sbo = (SchedulerBo)ctx.getBean("schedulerBo");
        sbo.printMsg();
        CustomerBo cbo = (CustomerBo)ctx.getBean("customerBo");
        cbo.setMessage("cbo 1");
        cbo.printMsg();
        CustomerBo cbo2 = (CustomerBo)ctx.getBean("customerBo");
        cbo2.printMsg();
        CustomerDao customerDao = (CustomerDao) ctx.getBean("hibernateCustomerDao");
        cbo.insert(new Customer(1, "Vijay", 32, ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("America/Chicago"))));
        cbo.insert(new Customer(1, "als", 32, ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("America/Chicago"))));
        Path p = Paths.get("/home/vijay/IdeaProjects/Java8WebApp/note");
        String contents = new String(Files.readAllBytes(new File("note").toPath()), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split(" "));
        Stream wordStream = words.stream().map(w -> w.toLowerCase()).distinct().limit(10000l).parallel();
         start = Instant.now();
         List<Customer> customers = new ArrayList<>();
        wordStream.forEach(word -> {
            String w = (String)word;
            ZonedDateTime insertTime =  ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("America/Chicago"));
            cbo.insert(new Customer(1l, w, w.length(), insertTime ));
            //customers.add(new Customer(1l, w, w.length(), insertTime ));
        });
        //customerDao.performBatchUpdate(customers);
         end =  Instant.now();
        System.out.println("Time to insert all words: "+ Duration.between(start, end).toMillis());
        Customer customer = customerDao.findCustomerById("vijay");
        System.out.println(customer.toString());

        ((ClassPathXmlApplicationContext)ctx).close();




    }
}