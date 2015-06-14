package com.module.messaging;

import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class MessagingFactory implements AbstractMessagingFactory{

    public AcceptanceTestCase testCase;

    public MessagingFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }

    public IMessaging create(){
        return new MessagingModule(this.testCase);
    }

}
