
package com.test.rt.phase1;

import com.parser.cases.TestDataExtract;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class AutomaticDebug extends AcceptanceTestCase {
    private static final String TAG = "AgingTest";

    private static RTAutomaticModules tc = null;

    private static int[] loopList = new int[50];

    public static boolean hasLoadConfigFile = false;

    public static HashMap<String, String> sCurrParameter;

    int sCurrLoop = 1;

    protected void setUp() throws Exception {
        super.setUp();
        this.stayAwake();
        this.disableLockScreen();

        // if not run from InstrumentationTestRunner, the configure file will
        // not load, so load it here.
        if (!hasLoadConfigFile) {
            TestDataExtract.loadConfigFile(this, "RTConfig.xml");
            hasLoadConfigFile = true;
        }
        if (tc == null) {
        	tc = new RTAutomaticModules(this);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SuppressWarnings("static-access")
    public void test_000() throws IllegalArgumentException, ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Log.d(TAG, "==============Begin test_000==============");

        this.sCurrParameter = TestDataExtract.getParameters(0);
        sCurrLoop = ++loopList[0];
        Log.d(TAG, "^^^^^^^^^^^^^" + TestDataExtract.getCasename(0) + "|" + sCurrLoop + "^^^^^^^^^^^^^^");
        printCaseNameInResult(TestDataExtract.getCasename(0));
        printSnarioNameInResult(sCurrParameter.get("snarioname"));
        fieldMethods(TestDataExtract.getCasename(0), tc, sCurrParameter);

        Log.d(TAG, "==================End test_000==============");

    }


    /**
     * Print case module name in Instrumentation Test Result
     *
     * @param name: case module name
     */
    private void printCaseNameInResult(String name) {
        Bundle bundle = new Bundle();
        bundle.putString("casename", name);
        this.getInstrumentation().sendStatus(1, bundle);
    }

    private void printSnarioNameInResult(String name) {
        Bundle bundle = new Bundle();
        bundle.putString("snarioname", name);
        this.getInstrumentation().sendStatus(1, bundle);
    }

    private void fieldMethods(String methodName, RTAutomaticModules nts, HashMap<String, String> paras) throws ClassNotFoundException,
            NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        Class clazz = Class.forName("com.test.rt.phase1.RTAutomaticModules");
        Log.e("ubq", methodName);
        Method method = clazz.getDeclaredMethod(methodName, String.class, HashMap.class);
        Log.e("ubq", method.getName());
        method.invoke(nts, new Object[]{methodName, paras});

    }

}
