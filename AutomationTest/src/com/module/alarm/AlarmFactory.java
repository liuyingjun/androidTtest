
package com.module.alarm;

import com.parser.module.PropertyNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public class AlarmFactory {

    public static IAlarm getCameraModule(AcceptanceTestCase testCase) throws PropertyNotFoundException {
        return new AlarmModule(testCase);
    }

    public static IAlarm getAlarmModule(AcceptanceTestCase testCase) throws PropertyNotFoundException {
        // TODO Auto-generated method stub
        return new AlarmModule(testCase);
    }

}
