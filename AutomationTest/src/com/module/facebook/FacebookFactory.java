package com.module.facebook;

import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class FacebookFactory implements AbstractFacebookFactory {

    public AcceptanceTestCase testCase;

    public FacebookFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }
    @Override
    public IFacebook create() {
        // TODO Auto-generated method stub
        return new FacebookModule(this.testCase);
    }

}
