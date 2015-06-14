package com.module.settings;

import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class SettingsFactory implements AbstractSettingsFactory{

    private static final String PACKAGE = "SETTINGS_PACKAGE";

    public AcceptanceTestCase testCase;

    public SettingsFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }

    public ISetting getSettingsModule() throws PropertyNotFoundException{
        return new SettingsModule(this.testCase);
    }


}
