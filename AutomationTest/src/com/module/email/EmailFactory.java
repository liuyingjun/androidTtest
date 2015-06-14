package com.module.email;

import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class EmailFactory implements AbstractEmailFactory{

    public AcceptanceTestCase testCase;

    public EmailFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }
    public IEmail create() throws PropertyNotFoundException{

        return new EmailModule(this.testCase);

    }
}
