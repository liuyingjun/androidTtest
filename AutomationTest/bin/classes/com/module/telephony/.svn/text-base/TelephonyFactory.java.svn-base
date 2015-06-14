
package com.module.telephony;

import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class TelephonyFactory implements AbstractTelephonyFactory{

    private static final String PACKAGE = "TELEPHONY_PACKAGE";

    public AcceptanceTestCase testCase;

    public TelephonyFactory(AcceptanceTestCase testCase){
        this.testCase = testCase;
    }
    public ITelephony create() throws PropertyNotFoundException{

        return new TelephoneCommonModule(this.testCase);

    }

}
