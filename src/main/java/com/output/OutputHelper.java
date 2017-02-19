package com.output;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by vijay on 2/18/17.
 */
public class OutputHelper {
    public IOutputGenerator getOutputGenerator() {
        return outputGenerator;
    }

    public void setOutputGenerator(IOutputGenerator outputGenerator) {
        this.outputGenerator = outputGenerator;
    }

    private IOutputGenerator outputGenerator;

    public void generateOutput() {
        outputGenerator.generateOutput();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;

    @PostConstruct
    public void init(){
        System.out.println("Datasource is : "+ dataSource.toString());
    }

}
