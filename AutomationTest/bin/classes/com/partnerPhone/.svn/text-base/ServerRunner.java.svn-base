
package com.partnerPhone;

import junit.framework.TestSuite;

import com.android.uiautomator.testrunner.UiAutomatorInstrumentationTestRunner;
import com.precondition.PreconditionTest;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;

/**
 * this is the class for BatteryLifeBasicRunner
 *
 * @author
 * @version ICS,ANDROID 4.0
 * @since 2012.04.20
 *
 * @Phone NoZhongMi
 *
 */
public class ServerRunner extends UiAutomatorInstrumentationTestRunner {

    /**
     *
     * this is the main method of the getAllTests
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */

    @Override
    public TestSuite getAllTests() {

        TestSuite suite = new InstrumentationTestSuite(this);
        suite.addTestSuite(PreconditionTest.class);
        for (int i = 0; i < 1000; i++) {
            suite.addTestSuite(PartnerPhoneServer.class);
        }

        return suite;

    }

    /**
     *
     * this is the method for the getLoader
     *
     * @author
     * @version ICS,ANDROID 4.0
     * @since 2012.04.20
     *
     * @Phone NoZhongMi
     *
     */
    @Override
    public ClassLoader getLoader() {

        return AcceptanceTestCase.class.getClassLoader();

    }

}
