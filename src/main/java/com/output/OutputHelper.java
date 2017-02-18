package com.output;

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
}
