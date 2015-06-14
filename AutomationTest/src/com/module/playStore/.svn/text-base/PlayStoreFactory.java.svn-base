package com.module.playStore;

import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class PlayStoreFactory implements AbstractPlayStoreFactory{

    public AcceptanceTestCase testCase;
    public PlayStoreFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }

    public IPlayStore create(){
        return new PlayStore(this.testCase);
    }
}
