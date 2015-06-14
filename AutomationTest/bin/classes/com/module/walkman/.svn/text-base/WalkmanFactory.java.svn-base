package com.module.walkman;

import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;
import com.parser.module.PropertyNotFoundException;

public class WalkmanFactory implements AbstractWalkmanFactory{

    public AcceptanceTestCase testCase;

    public WalkmanFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }
    public IWalkman create() throws PropertyNotFoundException{

        return new WalkmanModule(this.testCase);

    }
}
