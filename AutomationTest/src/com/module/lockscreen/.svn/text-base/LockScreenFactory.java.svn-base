package com.module.lockscreen;

import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class LockScreenFactory implements AbstractLockScreenFactory{

    public AcceptanceTestCase testCase;

    public LockScreenFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }
    public ILockScreen create() {

        return new LockScreenModule(this.testCase);

    }

}
