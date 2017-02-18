package com;

import com.configuration.AppConfig;
import com.output.IOutputGenerator;
import com.output.OutputHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args){
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("SpringBeans.xml");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
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
        cbo.printMsg();
    }
}