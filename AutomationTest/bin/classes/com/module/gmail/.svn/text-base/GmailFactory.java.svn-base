package com.module.gmail;

import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class GmailFactory implements AbstractGmailFactory{

    public AcceptanceTestCase testCase;

    public GmailFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }

    @Override
    public IGmail create() {

        return new GmailModule(testCase);
    }

}
