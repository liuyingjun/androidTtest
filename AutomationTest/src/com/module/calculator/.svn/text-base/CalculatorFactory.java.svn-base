package com.module.calculator;

import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class CalculatorFactory implements AbstractCalculatorFactory {
    private static final String PACKAGE = "CALCULATOR_PACKAGE";

    public AcceptanceTestCase testCase;

    public CalculatorFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }

    public ICalculator create() {
        return new CalculatorModule(this.testCase);
    }

}
