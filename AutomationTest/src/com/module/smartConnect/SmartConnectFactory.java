package com.module.smartConnect;

import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class SmartConnectFactory {
    public static ISmartConnect getSmartConnectModule(AcceptanceTestCase testCase) {
        // TODO Auto-generated method stub
        return new SmartConnectModule(testCase);
    }
}
