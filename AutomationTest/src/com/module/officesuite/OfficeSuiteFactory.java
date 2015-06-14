package com.module.officesuite;

import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class OfficeSuiteFactory {

    private static final String PACKAGE = "OFFICESUITE_PACKAGE";

    public static IOfficeSuite getOfficeSuiteModule(AcceptanceTestCase testCase)
            throws PropertyNotFoundException {
        return new OfficeSuiteModule(testCase);
    }
}
